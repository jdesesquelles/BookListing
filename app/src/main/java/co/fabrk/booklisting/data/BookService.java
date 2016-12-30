package co.fabrk.booklisting.data;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.VisibleForTesting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observer;

import co.fabrk.booklisting.Constants;
import co.fabrk.booklisting.Utilities;
import co.fabrk.booklisting.model.GBook;

/**
 * Created by ebal on 18/12/16.
 */

public class BookService {

    private static final ObservableBookArrayList observableBookArrayList = new ObservableBookArrayList();

    private static String mStatus = Constants.ERROR_JSON_EMPTY_RESPONSE;

    private static void setStatus(String mStatus) {
        BookService.mStatus = mStatus;
    }

    public static String getStatus() {
        return mStatus;
    }

    /*********************************************************************/
    /**                                                                 **/
    /**                        Public Interface                         **/
    /**                                                                 **/
    /*********************************************************************/

    public static void getBookForQuery(String query, String page, String pageSize) {
        FetchBookList fetchBookListTask = new FetchBookList();
        fetchBookListTask.execute(query, page, pageSize);
    }

    /*********************************************************************/
    /**                                                                 **/
    /**                          Observable                             **/
    /**                                                                 **/
    /*********************************************************************/

    public static void registerObserver(Observer observer) {
        observableBookArrayList.addObserver(observer);
    }

    public static void removeObserver(Observer observer) {
        observableBookArrayList.deleteObserver(observer);
    }

    /*********************************************************************/
    /**                                                                 **/
    /**                     NetWork Call Async Task                     **/
    /**                                                                 **/
    /*********************************************************************/

    private static class FetchBookList extends AsyncTask<String, Void, ArrayList<GBook>>{

        @Override
        protected ArrayList<GBook> doInBackground(String... strings) {
            requestBookList(strings[0], strings[1], strings[2]);
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<GBook> gBooks) {
            // Notification should be done on the UI Thread to update views
            observableBookArrayList.notifyObservers();
        }
    }

    /*********************************************************************/
    /**                                                                 **/
    /**                     Requesting Google Book API                  **/
    /**                                                                 **/
    /*********************************************************************/

    private static synchronized void requestBookList(String query, String page, String pageSize) {
        ArrayList<GBook> bookArrayList;
        String url = BuildGoogleBookUrl(query, page, pageSize);
        String httpResponse = Utilities.getHttpResponse(url);
        // httpResponse contains the error message in case an exception is thrown
        if (Constants.ERROR_NETWORK_NO_NETWORK.equals(httpResponse) | Constants.ERROR_NETWORK_NO_RESPONSE.equals(httpResponse) | Constants.ERROR_NETWORK_FILE_NOT_FOUND.equals(httpResponse)) {
            setStatus(httpResponse);
            observableBookArrayList.setBookArrayList(null);
        } else {
            bookArrayList = getBookListFromJson(httpResponse);
            if (null != bookArrayList) {
                if (1 <= bookArrayList.size()) {
                    setStatus(Constants.STATUS_OK);
                }
                else setStatus(Constants.ERROR_JSON_EMPTY_RESPONSE);
            }
            observableBookArrayList.setBookArrayList(bookArrayList);
        }
    }

    private static String BuildGoogleBookUrl(String query, String page, String pageSize) {
        // https://www.googleapis.com/books/v1/volumes?q=ndk&maxResults=10&fields=items(volumeInfo/title,volumeInfo/authors)
        Uri builtUri;
        builtUri = Uri.parse(Constants.URL_BASE).buildUpon()
                .appendQueryParameter(Constants.URL_PARAM_QUERY, query)
                .appendQueryParameter(Constants.URL_PARAM_MAX_RESULT, pageSize)
                .appendQueryParameter(Constants.URL_PARAM_FIELDS, Constants.URL_VALUE_FIELDS)
                .appendQueryParameter(Constants.URL_PARAM_LANG_RESTRICT, Constants.URL_VALUE_LANG_RESTRICT)
                .appendQueryParameter(Constants.URL_PARAM_START_INDEX, String.valueOf(Integer.valueOf(page) * Integer.valueOf(pageSize)))
                .build();
        return builtUri.toString();
    }

    /*********************************************************************/
    /**                                                                 **/
    /**                     Parsing the Json response                   **/
    /**                                                                 **/
    /*********************************************************************/

    // Return an ArrayList of GBooks from a json raw String
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public static ArrayList<GBook> getBookListFromJson(String jsonStr) {
        if (jsonStr == null) {
            return null;
        }
        try {
            JSONObject booksJson = new JSONObject(jsonStr);
            Iterator<String> jsonKeySet = booksJson.keys();
            // For an empty response, setStatus to Empty and return
            if (!jsonKeySet.hasNext()) {
                setStatus(Constants.ERROR_JSON_EMPTY_RESPONSE);
                return null;
            }
            String jsonKey = jsonKeySet.next();
            // For an error response, setStatus to error and return
            if (Constants.JSON_BOOK_ERROR.equals(jsonKey)) {
                setStatus(Constants.ERROR_JSON_INVALID_REQUEST_PARAMETER);
                return null;
            }
            // For a valid response, parse the response
            if (Constants.JSON_BOOK_ITEMS.equals(jsonKey)) {
                JSONArray bookList = booksJson.getJSONArray(Constants.JSON_BOOK_ITEMS);
                ArrayList<GBook> bookArrayList = new ArrayList<>();
                for (int i = 0; i < bookList.length(); i++) {
                    GBook gBook = getBookFromJson(bookList.getJSONObject(i));
                    if (null != gBook) bookArrayList.add(gBook);
                }
                if (0 == bookArrayList.size()) {
                    setStatus(Constants.ERROR_JSON_EMPTY_RESPONSE);
                }
                return bookArrayList;
            }
            // Otherwise return malformed document error code
            else {
                setStatus(Constants.ERROR_JSON_UNEXPECTED_RESPONSE);
                return null;
            }
        } catch (JSONException e) {
            setStatus(Constants.ERROR_JSON_MALFORMED_RESPONSE);
            return null;
        }
    }

    // Return A GBook from a json item Object
    private static GBook getBookFromJson(JSONObject itemJson) {
        try {
            JSONObject volumeInfoJson = itemJson.getJSONObject(Constants.JSON_BOOK_VOLUME_INFO);
            return new GBook(getBookTitleFromJson(volumeInfoJson), getBookAuthorsFromJson(volumeInfoJson));
        } catch (JSONException e) {
            setStatus(Constants.ERROR_JSON_NO_ITEM);
            return null;
        }
    }

    // Return the Title from the json VolumeInfo Object
    private static String getBookTitleFromJson(JSONObject volumeInfoJson) {
        try {
            return volumeInfoJson.getString(Constants.JSON_BOOK_TITLE);
        } catch (JSONException e) {
            return Constants.ERROR_JSON_NO_TITLE;
        }
    }

    // Return an ArrayList of authors from the json VolumeInfo Object
    private static ArrayList<String> getBookAuthorsFromJson(JSONObject volumeInfoJson) {
        ArrayList<String> authorsArrayList = new ArrayList<>();
        try {
            JSONArray authorsList = volumeInfoJson.getJSONArray(Constants.JSON_BOOK_AUTHORS);
            for (int j = 0; j < authorsList.length(); j++) {
                authorsArrayList.add(authorsList.getString(j));
            }
        } catch (JSONException e) {
            if (0 == authorsArrayList.size()) {
                authorsArrayList.add(Constants.ERROR_JSON_NO_AUTHOR);
            }
        }
        return authorsArrayList;
    }

}
