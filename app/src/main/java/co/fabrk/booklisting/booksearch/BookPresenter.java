package co.fabrk.booklisting.booksearch;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import co.fabrk.booklisting.Constants;
import co.fabrk.booklisting.Utilities;
import co.fabrk.booklisting.data.BookService;
import co.fabrk.booklisting.model.GBook;
import co.fabrk.booklisting.data.ObservableBookArrayList;

/**
 * Created by ebal on 21/12/16.
 */
public class BookPresenter implements Observer, BookContract.SearchBookActionListener {

    private BookContract.View mBookView;
    private int mCurrentPage = 0;
    private int mPageSize = 10;
    private ConnectivityManager mConnectivityManager;


    public BookPresenter(BookContract.View bookView, ConnectivityManager connectivityManager) {
        BookService.registerObserver(this);
        mBookView = bookView;
        mConnectivityManager = connectivityManager;
        mBookView.showErrorMessage(Constants.MESSAGE_ENTER_TEXT);
    }

    /**
     * User Action Listener interface
     */

    @Override
    public void loadBookList(String query) {
        if (query.equals(Constants.NULL_STRING)) {
            mBookView.showErrorMessage(Constants.MESSAGE_ENTER_TEXT);
        } else {
            BookService.getBookForQuery(query, String.valueOf(mCurrentPage), String.valueOf(mPageSize));
        }
    }

    @Override
    public Bundle saveInstanceState(Bundle outState) {
        BookService.removeObserver(this);
        outState.putInt(Constants.SAVE_STATE_KEY_CURRENT_PAGE, mCurrentPage);
        outState.putInt(Constants.SAVE_STATE_KEY_PAGE_SIZE, mPageSize);
        mBookView.saveInstanceState(outState);
        return outState;
    }

    @Override
    public void restoreInstanceState(Bundle inState) {
        mCurrentPage = inState.getInt(Constants.SAVE_STATE_KEY_CURRENT_PAGE);
        mPageSize = inState.getInt(Constants.SAVE_STATE_KEY_CURRENT_PAGE);
        BookService.registerObserver(this);
        mBookView.restoreInstanceState(inState);
    }


    /**
     * Observer call back method.
     * Object contains the status in a String object
     */

    @Override
    public void update(Observable observable, Object o) {
        String status = BookService.getStatus();
        if (status.equals(Constants.STATUS_OK)) {
            if (null != observable) {
                ArrayList<GBook> bookArrayList = ((ObservableBookArrayList) observable).getBookArrayList();
                mBookView.updateBookListView(bookArrayList);
            }
        } else {
            proceedErrorMessage(status);
        }
    }

    private void proceedErrorMessage(String message) {
        Boolean isNetworkConnected = Utilities.isNetworkConnected(mConnectivityManager);
        if (isNetworkConnected) {
            if (message.equals(Constants.ERROR_NETWORK_NO_NETWORK)) {
                message = Constants.MESSAGE_HOST_UNREACHABLE;
            }
        }
        mBookView.showErrorMessage(message);

    }
}

