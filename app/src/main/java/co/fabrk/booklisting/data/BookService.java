package co.fabrk.booklisting.data;

import android.net.Uri;
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
 * Created by ebal on 18/12/16.
 */

public class BookService {
    private static final String LOG_TAG = "BookService";

    // Pagination: Max Result, startIndex
    // TODO: 18/12/16 export into dimen
    static final int pageSize = 10;
//    static int currentPage = 0;
    static int startIndex = 0;

    private static final String URL_BASE =
            "https://www.googleapis.com/books/v1/volumes?";
    private static final String URL_PARAM_QUERY = "q";
    //https://www.googleapis.com/books/v1/volumes?q=ndk&maxResults=10&fields=items(volumeInfo/title,volumeInfo/authors)
    // Field Selection
    private static final String URL_PARAM_FIELDS = "fields";
    private static final String URL_VALUE_FIELDS = String.valueOf("items(volumeInfo/title,volumeInfo/authors)");

    private static final String URL_PARAM_MAX_RESULT = "maxResults";
    private static final String URL_VALUE_MAX_RESULT = String.valueOf(pageSize);
    private static final String URL_PARAM_START_INDEX = "startIndex";
    private static final String URL_VALUE_START_INDEX = String.valueOf(startIndex);

    //    langRestrict="en" or "fr" (two-letter ISO-639-1 code).
    private static final String URL_PARAM_LANG_RESTRICT = "langRestrict";
    private static final String URL_VALUE_LANG_RESTRICT = "en";
    //    filter=partial, full, free-ebooks, paid-ebooks, ebooks

    public static final String BOOK_JSON_ITEMS = "items";
    public static final String BOOK_JSON_VOLUME_INFO = "volumeInfo";
    public static final String BOOK_JSON_TITLE = "title";
    public static final String BOOK_JSON_AUTHORS = "authors";

    private static String NO_AUTHOR = "No author found";
    private static String NO_TITLE = "Title not found";

    public static ArrayList<GBook> getNextPage(String query, String sCurrentPage) {
        Integer currentPage = Integer.valueOf(sCurrentPage);
        currentPage++;
        startIndex = startIndex + pageSize * (currentPage - 1);
        return getBookForQuery(query);
    }

    private static ArrayList<GBook> getBookForQuery(String query) {
        String url = BuildUrl(query);
        Log.v(LOG_TAG, "Query Url :" + url.toString());
        String json = getHttpResponse(url);
        return getBookList(json);
    }

    private static String BuildUrl(String query) {
        //https://www.googleapis.com/books/v1/volumes?q=ndk&maxResults=10&fields=items(volumeInfo/title,volumeInfo/authors)
        Uri builtUri;
        builtUri = Uri.parse(URL_BASE).buildUpon()
                .appendQueryParameter(URL_PARAM_QUERY, query)
                .appendQueryParameter(URL_PARAM_MAX_RESULT, URL_VALUE_MAX_RESULT)
                .appendQueryParameter(URL_PARAM_FIELDS, URL_VALUE_FIELDS)
                .appendQueryParameter(URL_PARAM_LANG_RESTRICT, URL_VALUE_LANG_RESTRICT)
                .appendQueryParameter(URL_PARAM_START_INDEX, URL_VALUE_START_INDEX)
                .build();
        return builtUri.toString();
    }

    private static String getHttpResponse(String stringUrl) {
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

    private static ArrayList<GBook> getBookList(String jsonStr) {
        if (jsonStr == null) {
            return null;
        }
        try {
            JSONObject booksJson = new JSONObject(jsonStr);
            JSONArray bookList = booksJson.getJSONArray(BOOK_JSON_ITEMS);
            ArrayList<GBook> bookArrayList = new ArrayList<GBook>();
            for (int i = 0; i < bookList.length(); i++) {
//                JSONObject itemJson = bookList.getJSONObject(i);
//                JSONObject volumeInfoJson = itemJson.getJSONObject(BOOK_JSON_VOLUME_INFO);
                bookArrayList.add(getBook(bookList.getJSONObject(i)));
            }
            return bookArrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static GBook getBook(JSONObject itemJson) {
        try {
            JSONObject volumeInfoJson = itemJson.getJSONObject(BOOK_JSON_VOLUME_INFO);
            return new GBook(getBookTitle(volumeInfoJson), getBookAuthors(volumeInfoJson));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getBookTitle(JSONObject volumeInfoJson) {
        try {
            Log.e("jerem", "ParseGoogleBookJSON: " + volumeInfoJson.getString(BOOK_JSON_TITLE));
            return volumeInfoJson.getString(BOOK_JSON_TITLE);
        } catch (JSONException e) {
            e.printStackTrace();
            return NO_TITLE;
        }
    }

    private static ArrayList<String> getBookAuthors(JSONObject volumeInfoJson) {
        ArrayList<String> authorsArrayList = new ArrayList<String>();
        try {
            JSONArray authorsList = volumeInfoJson.getJSONArray(BOOK_JSON_AUTHORS);
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
        return authorsArrayList;
    }
}
