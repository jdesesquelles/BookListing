package co.fabrk.booklisting;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import co.fabrk.booklisting.model.GBook;

/**
 * Created by ebal on 10/09/15.
 */
public class FetchBookList extends AsyncTask<String, Void, ArrayList<GBook>> {

//    https://www.googleapis.com/books/v1/volumes?q=android&maxResults=1

    public interface BookListListener {
        public void updateBookArrayList(ArrayList<GBook> bookArrayList);
    }

    private String NO_AUTHOR = "No author found";
    private String NO_TITLE = "Title not found";


    private boolean DEBUG = true;
    private final String LOG_TAG = FetchBookList.class.getSimpleName();
    private final String URL_BASE =
            "https://www.googleapis.com/books/v1/volumes?";
    private final String URL_PARAM_QUERY = "q";
    //https://www.googleapis.com/books/v1/volumes?q=ndk&maxResults=10&fields=items(volumeInfo/title,volumeInfo/authors)
    // Field Selection
    private final String URL_PARAM_FIELDS = "fields";
    private final String URL_VALUE_FIELDS = String.valueOf("items(volumeInfo/title,volumeInfo/authors)");

    // Pagination
    int pageSize = 10;
    int currentPage = 0;
    int startIndex = 0; // currentPage *
    // Max Result
    private final String URL_PARAM_MAX_RESULT = "maxResults";
    private final String URL_VALUE_MAX_RESULT = String.valueOf(pageSize);
    //startIndex
    private final String URL_PARAM_START_INDEX = "startIndex";
    private final String URL_VALUE_START_INDEX = String.valueOf(startIndex);

    //    filter=partial, full, free-ebooks, paid-ebooks, ebooks
    //    langRestrict="en" or "fr" (two-letter ISO-639-1 code).
    // Language Restriction
    private final String URL_PARAM_LANG_RESTRICT = "langRestrict";
    private final String URL_VALUE_LANG_RESTRICT = "en";

// minus keywords: Exclusion List

    private BookListListener mListener;

    public FetchBookList(BookListListener listener) {
        mListener = listener;
    }

    @Override
    protected void onPostExecute(ArrayList<GBook> arrayListBooks) {
//        super.onPostExecute(arrayListBooks);
        if (null == arrayListBooks) {
            return;
        } else mListener.updateBookArrayList(arrayListBooks);

    }

    @Override
    protected ArrayList<GBook> doInBackground(String... params) {
        // We fetch both popular and highest rated movie list upfront.
        ArrayList<GBook> bookArrayList;
        try {
            bookArrayList = getBookForQuery(params[0]);
            Log.e(LOG_TAG, "doInBackground: NO JSON Exception : " + bookArrayList.size());
            return bookArrayList;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "doInBackground: JSON Exception");
        }
        return null;
    }


    private ArrayList<GBook> getBookForQuery(String query) throws JSONException {
        String url = BuildUrl(query);
        Log.v(LOG_TAG, "Query Url :" + url.toString());
        String json = getHttpResponse(url);
        return ParseGoogleBookJSON(json);
    }

    private String BuildUrl(String query) {
        //https://www.googleapis.com/books/v1/volumes?q=ndk&maxResults=10&fields=items(volumeInfo/title,volumeInfo/authors)
        Uri builtUri;
        builtUri = Uri.parse(URL_BASE).buildUpon()
                .appendQueryParameter(URL_PARAM_QUERY, query)
                .appendQueryParameter(URL_PARAM_MAX_RESULT, URL_VALUE_MAX_RESULT)
                .build();
        return builtUri.toString();
    }

//    .appendQueryParameter(URL_PARAM_FIELDS, URL_VALUE_FIELDS)
//    .appendQueryParameter(URL_PARAM_LANG_RESTRICT, URL_VALUE_LANG_RESTRICT)

    // private String getHttpResponse(String stringUrl) {
    private String getHttpResponse(String stringUrl) {
        // String Url as an input, JSON String as the output
        String connectionMethod = "GET";
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String response = null;
        try {
            URL url = new URL(stringUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(connectionMethod);
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            response = buffer.toString();
        } catch (IOException e) {
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
            return response;
        }
    }

    private ArrayList<GBook> ParseGoogleBookJSON(String jsonStr) throws JSONException {
        // Field names in JSON
        final String BOOK_ITEMS = "items";
        final String BOOK_VOLUME_INFO = "volumeInfo";
        final String BOOK_TITLE = "title";
        final String BOOK_AUTHORS = "authors";

        if (jsonStr == null) {
            return null;
        }

        JSONObject booksJson = new JSONObject(jsonStr);
//        booksJson.
        JSONArray bookList = booksJson.getJSONArray(BOOK_ITEMS);
        ArrayList<GBook> bookArrayList = new ArrayList<GBook>();

        for (int i = 0; i < bookList.length(); i++) {
            JSONObject itemJson = bookList.getJSONObject(i);
            JSONObject volumeInfoJson = itemJson.getJSONObject(BOOK_VOLUME_INFO);
            String title;
            try {
                title = volumeInfoJson.getString(BOOK_TITLE);
            Log.e("jerem", "ParseGoogleBookJSON: " + title);
            } catch (JSONException e) {
                e.printStackTrace();
               title = NO_TITLE;
                }
            ArrayList<String> authorsArrayList = new ArrayList<String>();
            try {
                JSONArray authorsList = volumeInfoJson.getJSONArray(BOOK_AUTHORS);
                for (int j = 0; j < authorsList.length(); j++) {
                    Log.e("jerem", "ParseGoogleBookJSON: " + authorsList.getString(j));
                    authorsArrayList.add(authorsList.getString(j));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                if (0 == authorsArrayList.size()) {
                    authorsArrayList.add(NO_AUTHOR);
                }
            }

            GBook book = new GBook(title, authorsArrayList);
            bookArrayList.add(book);
        }
        return bookArrayList;
    }

}
