package co.fabrk.booklisting.booksearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import co.fabrk.booklisting.Constants;
import co.fabrk.booklisting.R;
import co.fabrk.booklisting.model.GBook;

/**
 * View holder
 */

class BookView implements BookContract.View {
    private final EditText search_books_edit_text_search;
    private final ListView books_list_view;
    private final FrameLayout empty_view;
    private final TextView error_message;
    private final BookListAdapter bookListAdapter;
    private BookContract.SearchBookActionListener mListener;

    public void setActionListener(BookContract.SearchBookActionListener listener) {
        mListener = listener;
    }

    BookView(View rootView, LayoutInflater layoutInflater) {
        ImageButton search_button;


        search_books_edit_text_search = (EditText) rootView.findViewById(R.id.search_books_edit_text_search);
        error_message = (TextView) rootView.findViewById(R.id.error_message);

        // Setting the user Actions
        search_button = (ImageButton) rootView.findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constants.NULL_STRING.equals(search_books_edit_text_search.getText().toString())) {
                    showMessage(Constants.MESSAGE_ENTER_TEXT);
                } else {
                    mListener.loadBookList(search_books_edit_text_search.getText().toString());
                }
            }
        });

        search_books_edit_text_search.setShowSoftInputOnFocus(true);

        // Setting the ListView
        books_list_view = (ListView) rootView.findViewById(R.id.books_recycler_view);
        empty_view = (FrameLayout) rootView.findViewById(R.id.empty_view);
        books_list_view.setEmptyView(empty_view);
        bookListAdapter = new BookListAdapter(layoutInflater);
        books_list_view.setAdapter(bookListAdapter);
    }

    @Override
    public void updateBookListView(ArrayList<GBook> bookArrayList) {
        bookListAdapter.swapData(bookArrayList);
    }

    @Override
    public Bundle saveInstanceState(Bundle outState) {
        outState.putInt(Constants.SAVE_STATE_KEY_SCROLL_POSITION, books_list_view.getScrollY());
        outState.putParcelableArrayList(Constants.SAVE_STATE_KEY_BOOK_ARRAY_LIST, bookListAdapter.mBookArrayList);
        outState.putString(Constants.SAVE_STATE_KEY_USER_MESSAGE, error_message.getText().toString());
        outState.putInt(Constants.SAVE_STATE_KEY_USER_MESSAGE_VISIBILITY, empty_view.getVisibility());
        outState.putInt(Constants.SAVE_STATE_KEY_LISTVIEW_VISIBILITY, books_list_view.getVisibility());
        return outState;
    }

    @Override
    public void restoreInstanceState(Bundle inState) {
        if (inState.getInt(Constants.SAVE_STATE_KEY_USER_MESSAGE_VISIBILITY) == View.VISIBLE) {
                showMessage(inState.getString(Constants.SAVE_STATE_KEY_USER_MESSAGE));
        } else {
            inState.getInt(Constants.SAVE_STATE_KEY_LISTVIEW_VISIBILITY, books_list_view.getVisibility());
            books_list_view.scrollListBy(inState.getInt(Constants.SAVE_STATE_KEY_SCROLL_POSITION));
            ArrayList<GBook> bookArrayList = inState.getParcelableArrayList(Constants.SAVE_STATE_KEY_BOOK_ARRAY_LIST);
            updateBookListView(bookArrayList);
        }
    }

    @Override
    public void showMessage(String message) {
        bookListAdapter.swapData(null);
        error_message.setText(message);
    }
}
