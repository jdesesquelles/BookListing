package co.fabrk.booklisting;

import android.os.AsyncTask;

import java.util.ArrayList;

import co.fabrk.booklisting.data.BookService;
import co.fabrk.booklisting.model.GBook;


/**
 * Created by ebal on 10/09/15.
 */
public class FetchBookList extends AsyncTask<String, Void, ArrayList<GBook>> {

    public interface BookListCaller {
        void updateBookList(ArrayList<GBook> bookArrayList);
    }

    private BookListCaller mCaller;

    public FetchBookList(BookListCaller listener) {
        mCaller = listener;
    }

    @Override
    protected void onPostExecute(ArrayList<GBook> arrayListBooks) {
        if (null == arrayListBooks) {
            return;
        } else mCaller.updateBookList(arrayListBooks);

    }

    @Override
    protected ArrayList<GBook> doInBackground(String... params) {
//            Log.e(LOG_TAG, "doInBackground: NO JSON Exception : " + getBookForQuery(params[0]).size());
            return BookService.getNextPage(params[0], params[1]);
    }

//    public ArrayList<GBook> getNextPage(String query) {
//        currentPage ++;
//        startIndex = startIndex + pageSize * (currentPage - 1);
//        return getBookForQuery(query);
//    }

//    private ArrayList<GBook> getBookForQuery(String query) {
//        String url = BuildUrl(query);
//        Log.v(LOG_TAG, "Query Url :" + url.toString());
//        String json = getHttpResponse(url);
//        return getBookList(json);
//    }
//
//    private String BuildUrl(String query) {
//        //https://www.googleapis.com/books/v1/volumes?q=ndk&maxResults=10&fields=items(volumeInfo/title,volumeInfo/authors)
//        Uri builtUri;
//        builtUri = Uri.parse(URL_BASE).buildUpon()
//                .appendQueryParameter(URL_PARAM_QUERY, query)
//                .appendQueryParameter(URL_PARAM_MAX_RESULT, URL_VALUE_MAX_RESULT)
//                .appendQueryParameter(URL_PARAM_FIELDS, URL_VALUE_FIELDS)
//                .appendQueryParameter(URL_PARAM_LANG_RESTRICT, URL_VALUE_LANG_RESTRICT)
//                .appendQueryParameter(URL_PARAM_START_INDEX, URL_VALUE_START_INDEX)
//                .build();
//        return builtUri.toString();
//    }
//
//    private String getHttpResponse(String stringUrl) {
//        // String Url as an input, JSON String as the output
//        String connectionMethod = "GET";
//        HttpURLConnection urlConnection = null;
//        BufferedReader reader = null;
//        String response = null;
//        try {
//            URL url = new URL(stringUrl);
//            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod(connectionMethod);
//            urlConnection.connect();
//            InputStream inputStream = urlConnection.getInputStream();
//            StringBuffer buffer = new StringBuffer();
//            if (inputStream == null) {
//                return null;
//            }
//            reader = new BufferedReader(new InputStreamReader(inputStream));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line + "\n");
//            }
//            if (buffer.length() == 0) {
//                return null;
//            }
//            response = buffer.toString();
//        } catch (IOException e) {
//            return null;
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (final IOException e) {
//                }
//            }
//            return response;
//        }
//    }
//
//    private ArrayList<GBook> getBookList(String jsonStr) {
//        if (jsonStr == null) {
//            return null;
//        }
//        try {
//            JSONObject booksJson = new JSONObject(jsonStr);
//            JSONArray bookList = booksJson.getJSONArray(BOOK_JSON_ITEMS);
//            ArrayList<GBook> bookArrayList = new ArrayList<GBook>();
//            for (int i = 0; i < bookList.length(); i++) {
////                JSONObject itemJson = bookList.getJSONObject(i);
////                JSONObject volumeInfoJson = itemJson.getJSONObject(BOOK_JSON_VOLUME_INFO);
//                bookArrayList.add(getBook(bookList.getJSONObject(i)));
//            }
//            return bookArrayList;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private GBook getBook(JSONObject itemJson) {
//        try {
//            JSONObject volumeInfoJson = itemJson.getJSONObject(BOOK_JSON_VOLUME_INFO);
//            return new GBook(getBookTitle(volumeInfoJson), getBookAuthors(volumeInfoJson));
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private String getBookTitle(JSONObject volumeInfoJson) {
//        try {
//            Log.e("jerem", "ParseGoogleBookJSON: " + volumeInfoJson.getString(BOOK_JSON_TITLE));
//            return volumeInfoJson.getString(BOOK_JSON_TITLE);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return NO_TITLE;
//        }
//    }
//
//    private ArrayList<String> getBookAuthors(JSONObject volumeInfoJson) {
//        ArrayList<String> authorsArrayList = new ArrayList<String>();
//        try {
//            JSONArray authorsList = volumeInfoJson.getJSONArray(BOOK_JSON_AUTHORS);
//            for (int j = 0; j < authorsList.length(); j++) {
//                Log.e("jerem", "ParseGoogleBookJSON: " + authorsList.getString(j));
//                authorsArrayList.add(authorsList.getString(j));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//            if (0 == authorsArrayList.size()) {
//                authorsArrayList.add(NO_AUTHOR);
//            }
//        }
//        return authorsArrayList;
//    }

}
