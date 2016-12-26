package co.fabrk.booklisting.data;

import java.util.Observer;
import co.fabrk.booklisting.Constants;

/**
 * Created by ebal on 18/12/16.
 */

public class BookService {

    private static ObservableBookArrayList observableBookArrayList = new ObservableBookArrayList();
    private String mStatus = Constants.STATUS_OK;

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
        mStatus = mStatus;
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
    }


}
