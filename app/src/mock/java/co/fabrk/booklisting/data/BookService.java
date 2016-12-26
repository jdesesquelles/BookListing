package co.fabrk.booklisting.data;

import java.util.Observable;

import android.net.Uri;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Observer;

import co.fabrk.booklisting.Constants;
import co.fabrk.booklisting.Utilities;
import co.fabrk.booklisting.data.BookServiceApi;
import co.fabrk.booklisting.data.ObservableBookArrayList;
import co.fabrk.booklisting.model.GBook;

/**
 * Created by ebal on 18/12/16.
 */

public class BookService {
//    implements} BookServiceApi {

    private static ObservableBookArrayList observableBookArrayList = new ObservableBookArrayList();
    private static String mStatus = Constants.STATUS_OK;


    public static String getmStatus() {
        return mStatus;
    }

    /*********************************************************************/
    /**                                                                 **/
    /**                        Public Interface                         **/
    /**                                                                 **/
    /*********************************************************************/

    public static void getBookForQuery(String query, String page, String pageSize) {
        requestBookList(query, page, pageSize);
        observableBookArrayList.notifyObservers();
    }

    /*********************************************************************/
    /**                                                                 **/
    /**                          Observable                             **/
    /**                                                                 **/

    public static void registerObserver(Observer observer) {
        observableBookArrayList.addObserver(observer);
    }

    public static void removeObserver(Observer observer) {
        observableBookArrayList.deleteObserver(observer);
    }


    /*********************************************************************/
    /**                                                                 **/
    /**                     Parsing the Json response                   **/
    /**                                                                 **/
    /*********************************************************************/

    private static void requestBookList(String query, String page, String pageSize) {
//        String url = BuildGoogleBookUrl(query, page, pageSize);
//        String httpResponse = Utilities.getHttpResponse(url);
//        ArrayList<GBook> bookArrayList = null;
//        if (Constants.ERROR_NETWORK_NO_NETWORK == httpResponse |
//                Constants.ERROR_NETWORK_NO_RESPONSE == httpResponse |
//                Constants.ERROR_NETWORK_FILE_NOT_FOUND == httpResponse) {
//            mStatus = httpResponse;
//            bookArrayList = null;
//        } else {
//            bookArrayList = getBookListFromJson(httpResponse);
//        }
//        observableBookArrayList.setBookArrayList(bookArrayList, mStatus);
//        // mStatus set to OK by default
//        mStatus = Constants.STATUS_OK;
    }

//    private static String BuildGoogleBookUrl(String query, String page, String pageSize) {
//        // https://www.googleapis.com/books/v1/volumes?q=ndk&maxResults=10&fields=items(volumeInfo/title,volumeInfo/authors)
//        Uri builtUri;
//        builtUri = Uri.parse(Constants.URL_BASE).buildUpon()
//                .appendQueryParameter(Constants.URL_PARAM_QUERY, query)
//                .appendQueryParameter(Constants.URL_PARAM_MAX_RESULT, pageSize)
//                .appendQueryParameter(Constants.URL_PARAM_FIELDS, Constants.URL_VALUE_FIELDS)
//                .appendQueryParameter(Constants.URL_PARAM_LANG_RESTRICT, Constants.URL_VALUE_LANG_RESTRICT)
//                .appendQueryParameter(Constants.URL_PARAM_START_INDEX, String.valueOf(Integer.valueOf(page) * Integer.valueOf(pageSize)))
//                .build();
//        return builtUri.toString();
//    }
//
//    /*********************************************************************/
//    /**                                                                 **/
//    /**                     Parsing the Json response                   **/
//    /**                                                                 **/
//    /*********************************************************************/
//
//    private static ArrayList<GBook> getBookListFromJson(String jsonStr) {
//        if (jsonStr == null) {
//            return null;
//        }
//        try {
//            JSONObject booksJson = new JSONObject(jsonStr);
//            JSONArray bookList = booksJson.getJSONArray(Constants.JSON_BOOK_ITEMS);
//            ArrayList<GBook> bookArrayList = new ArrayList<GBook>();
//            for (int i = 0; i < bookList.length(); i++) {
//                GBook gBook = getBookFromJson(bookList.getJSONObject(i));
//                if (null != gBook) bookArrayList.add(gBook);
//            }
//            return bookArrayList;
//        } catch (JSONException e) {
//            mStatus = Constants.ERROR_JSON_NO_ITEM;
//            return null;
//        }
//    }
//
//    private static GBook getBookFromJson(JSONObject itemJson) {
//        try {
//            JSONObject volumeInfoJson = itemJson.getJSONObject(Constants.JSON_BOOK_VOLUME_INFO);
//            return new GBook(getBookTitleFromJson(volumeInfoJson), getBookAuthorsFromJson(volumeInfoJson));
//        } catch (JSONException e) {
//            mStatus = Constants.ERROR_JSON_NO_ITEM;
//            return null;
//        }
//    }
//
//    private static String getBookTitleFromJson(JSONObject volumeInfoJson) {
//        try {
//            return volumeInfoJson.getString(Constants.JSON_BOOK_TITLE);
//        } catch (JSONException e) {
//            return Constants.ERROR_JSON_NO_TITLE;
//        }
//    }
//
//    private static ArrayList<String> getBookAuthorsFromJson(JSONObject volumeInfoJson) {
//        ArrayList<String> authorsArrayList = new ArrayList<String>();
//        try {
//            JSONArray authorsList = volumeInfoJson.getJSONArray(Constants.JSON_BOOK_AUTHORS);
//            for (int j = 0; j < authorsList.length(); j++) {
//                authorsArrayList.add(authorsList.getString(j));
//            }
//        } catch (JSONException e) {
//            if (0 == authorsArrayList.size()) {
//                authorsArrayList.add(Constants.ERROR_JSON_NO_AUTHOR);
//            }
//        }
//        return authorsArrayList;
//    }

}