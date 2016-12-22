package co.fabrk.booklisting;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import co.fabrk.booklisting.model.GBook;

/**
 * Created by ebal on 21/12/16.
 */
class BookPresenter implements Observer, BookContract.SearchBookActionListener {

    private BookView mBookView;
//    private BookListAdapter bookListAdapter;
    int mCurrentPage = 0;
    int mPageSize = 10;


    public BookPresenter(BookListActivity bookListActivity, View view, LayoutInflater layoutInflater) {
        BookService.registerObserver(this);
        mBookView = new BookView(view, layoutInflater, this);
//        bookListAdapter = new BookListAdapter(layoutInflater);
//        mBookView.books_list_view.setAdapter(bookListAdapter);
//        mBookView.searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadBookList(mBookView.search_books_edit_text_search.getText().toString());
//            }
//        });
    }

    /**
     * User Action Listener interface
     */

    @Override
    public void loadBookList(String query) {
        if (!query.equals("")) {
            BookService.getBookForQuery(query, String.valueOf(mCurrentPage), String.valueOf(mPageSize));
        }
    }

    @Override
    public Bundle saveInstanceState(Bundle outState) {
        BookService.removeObserver(this);
        outState.putInt(Constants.SAVE_STATE_SCROLL_POSITION, mBookView.books_list_view.getScrollY());
        outState.putInt(Constants.SAVE_STATE_KEY_CURRENT_PAGE, mCurrentPage);
        outState.putInt(Constants.SAVE_STATE_KEY_PAGE_SIZE, mPageSize);
        mBookView.saveInstanceState(outState);
//        outState.putParcelableArrayList(Constants.SAVE_STATE_KEY_BOOK_ARRAY_LIST, bookListAdapter.mBookArrayList);
        return outState;
    }

    @Override
    public void restoreInstanceState(Bundle inState) {
        mCurrentPage = inState.getInt(Constants.SAVE_STATE_KEY_CURRENT_PAGE);
        mPageSize = inState.getInt(Constants.SAVE_STATE_KEY_CURRENT_PAGE);
//        mBookView.books_list_view.scrollListBy(inState.getInt(Constants.SAVE_STATE_SCROLL_POSITION));
        BookService.registerObserver(this);
        mBookView.restoreInstanceState(inState);
//        ArrayList<GBook> bookArrayList = inState.getParcelableArrayList(Constants.SAVE_STATE_KEY_BOOK_ARRAY_LIST);
//        mBookView.updateBookListView(bookArrayList);
    }


    /**
     * Observer call back method.
     * Object contains the status in a String object
     */

    @Override
    public void update(Observable observable, Object o) {
        String message;
        if (null != observable) {
            String status = ((BookService.ObservableBookArrayList) observable).getmStatus();
            if (Constants.STATUS_OK == status) {
                ArrayList<GBook> bookArrayList = ((BookService.ObservableBookArrayList) observable).getBookArrayList();
                mBookView.updateBookListView(bookArrayList);
//                bookListAdapter.swapData(bookArrayList);
            } else {
                if (Constants.ERROR_NETWORK_NO_NETWORK == status) {
                    message = Constants.MESSAGE_HOST_UNREACHABLE;
                } else if (Constants.ERROR_NETWORK_NO_RESPONSE == status) {
                    message = Constants.MESSAGE_HOST_UNREACHABLE;
                } else if (Constants.ERROR_NETWORK_FILE_NOT_FOUND == status) {
                    message = Constants.MESSAGE_FILE_NOT_FOUND;
                } else if (Constants.ERROR_JSON_NO_ITEM == status) {
                    message = Constants.MESSAGE_NO_ITEM_FOUND;
                } else message = Constants.MESSAGE_UNKNOWN_ERROR;
                Snackbar.make(mBookView.mRootView, message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }

}
