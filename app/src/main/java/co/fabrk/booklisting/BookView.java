package co.fabrk.booklisting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import co.fabrk.booklisting.model.GBook;

/**
 * View holder
 */

class BookView implements BookContract.View {
    View mRootView;
    EditText search_books_edit_text_search;
    ImageView emptyView;
    ImageButton searchButton;
    ListView books_list_view;
    private BookListAdapter bookListAdapter;
    BookContract.SearchBookActionListener mListener;


    public BookView(View rootView, LayoutInflater layoutInflater, BookContract.SearchBookActionListener listener) {
        mListener = listener;

        mRootView = rootView;
        search_books_edit_text_search = (EditText) rootView.findViewById(R.id.search_books_edit_text_search);

        // Setting the user Actions
        searchButton = (ImageButton) rootView.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.loadBookList(search_books_edit_text_search.getText().toString());
            }
        });

        // Setting the ListView
        books_list_view = (ListView) rootView.findViewById(R.id.books_recycler_view);
        emptyView = (ImageView) rootView.findViewById(R.id.empty_view);
        books_list_view.setEmptyView(emptyView);
        bookListAdapter = new BookListAdapter(layoutInflater);
        books_list_view.setAdapter(bookListAdapter);
    }

    @Override
    public void updateBookListView(ArrayList<GBook> bookArrayList) {
                bookListAdapter.swapData(bookArrayList);
    }

    @Override
    public Bundle saveInstanceState(Bundle outState) {
        outState.putInt(Constants.SAVE_STATE_SCROLL_POSITION, books_list_view.getScrollY());
        outState.putParcelableArrayList(Constants.SAVE_STATE_KEY_BOOK_ARRAY_LIST, bookListAdapter.mBookArrayList);
        return outState;
    }

    @Override
    public void restoreInstanceState(Bundle inState) {
        books_list_view.scrollListBy(inState.getInt(Constants.SAVE_STATE_SCROLL_POSITION));
        ArrayList<GBook> bookArrayList = inState.getParcelableArrayList(Constants.SAVE_STATE_KEY_BOOK_ARRAY_LIST);
        updateBookListView(bookArrayList);
    }

}
