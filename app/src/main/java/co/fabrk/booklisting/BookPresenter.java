package co.fabrk.booklisting;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import co.fabrk.booklisting.model.GBook;

/**
 * Created by ebal on 21/12/16.
 */
public class BookPresenter implements Observer, SearchBookPresenter, FetchBookList.BookListCaller {

    private BookListActivity bookListActivity;
    private BookView mBookView;
    private BookListAdapter bookListAdapter;
    int mCurrentPage = 0;
    int mPageSize = 10;

    @Override
    public void update(Observable observable, Object o) {
        String message;
        if (null != observable) {
            String status = ((BookService.ObservableBookArrayList) observable).getmStatus();
            if (Constants.STATUS_OK == status) {
                ArrayList<GBook> bookArrayList = ((BookService.ObservableBookArrayList) observable).getBookArrayList();
                bookListAdapter.swapData(bookArrayList);
            } else {
                if (Constants.ERROR_NETWORK_NO_NETWORK == status) {
                    message = Constants.MESSAGE_HOST_UNREACHABLE;

                } else if (Constants.ERROR_JSON_NO_ITEM == status) {
                    message = Constants.MESSAGE_NO_ITEM_FOUND;
                } else message = Constants.MESSAGE_UNKNOWN_ERROR;
                Snackbar.make(mBookView.mRootView, message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }

    public BookPresenter(BookListActivity bookListActivity, View view, LayoutInflater layoutInflater) {
        this.bookListActivity = bookListActivity;
        BookService.registerObserver(this);
        mBookView = new BookView(view);
        bookListAdapter = new BookListAdapter(layoutInflater);
        mBookView.books_list_view.setAdapter(bookListAdapter);
        mBookView.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBookList(mBookView.search_books_edit_text_search.getText().toString());
            }
        });
    }

    public Bundle saveInstanceState(Bundle outState) {
        BookService.removeObserver(this);
        outState.putInt(Constants.SAVE_STATE_SCROLL_POSITION, mBookView.books_list_view.getScrollY());
        outState.putInt(Constants.SAVE_STATE_KEY_CURRENT_PAGE, mCurrentPage);
        outState.putInt(Constants.SAVE_STATE_KEY_PAGE_SIZE, mPageSize);
        outState.putParcelableArrayList(Constants.SAVE_STATE_KEY_BOOK_ARRAY_LIST, bookListAdapter.mBookArrayList);
        return outState;
    }

    public void restoreInstanceState(Bundle inState) {
        mCurrentPage = inState.getInt(Constants.SAVE_STATE_KEY_CURRENT_PAGE);
        mPageSize = inState.getInt(Constants.SAVE_STATE_KEY_CURRENT_PAGE);
        mBookView.books_list_view.scrollListBy(inState.getInt(Constants.SAVE_STATE_SCROLL_POSITION));
        BookService.registerObserver(this);
        ArrayList<GBook> bookArrayList = inState.getParcelableArrayList(Constants.SAVE_STATE_KEY_BOOK_ARRAY_LIST);
        bookListAdapter.swapData(bookArrayList);
    }

    @Override
    public void loadBookList(String query) {
        if (!query.equals("")) {
            BookService.getBookForQuery(query, String.valueOf(mCurrentPage), String.valueOf(mPageSize));
        }
    }

    @Override
    public void updateBookList(ArrayList<GBook> bookArrayList) {
        bookListAdapter.swapData(bookArrayList);
    }

    private class BookView {
        View mRootView;
        EditText search_books_edit_text_search;
        ImageView emptyView;
        ImageButton searchButton;
        ListView books_list_view;

        public BookView(View rootView) {
            this.mRootView = rootView;
            search_books_edit_text_search = (EditText) rootView.findViewById(R.id.search_books_edit_text_search);
            searchButton = (ImageButton) rootView.findViewById(R.id.searchButton);
            emptyView = (ImageView) rootView.findViewById(R.id.empty_view);
            books_list_view = (ListView) rootView.findViewById(R.id.books_recycler_view);
            books_list_view.setEmptyView(emptyView);
        }
    }

}