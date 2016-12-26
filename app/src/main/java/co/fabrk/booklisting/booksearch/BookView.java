package co.fabrk.booklisting.booksearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    private EditText search_books_edit_text_search;
    private ListView books_list_view;
    private ImageView empty_view;
    private TextView error_message;
    private BookListAdapter bookListAdapter;
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
                String query = search_books_edit_text_search.getText().toString();
                if (Constants.NULL_STRING.equals(query)) {
                    showErrorMessage(Constants.MESSAGE_ENTER_TEXT);
                } else {
                    mListener.loadBookList(search_books_edit_text_search.getText().toString());
                }
            }
        });

        // Setting the ListView
        books_list_view = (ListView) rootView.findViewById(R.id.books_recycler_view);
        empty_view = (ImageView) rootView.findViewById(R.id.empty_view);
        books_list_view.setEmptyView(empty_view);
        bookListAdapter = new BookListAdapter(layoutInflater);
        books_list_view.setAdapter(bookListAdapter);
    }

    @Override
    public void updateBookListView(ArrayList<GBook> bookArrayList) {
        error_message.setVisibility(View.GONE);
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

    @Override
    public void showErrorMessage(String message) {
        books_list_view.setVisibility(View.GONE);
        empty_view.setVisibility(View.VISIBLE);
        error_message.setVisibility(View.VISIBLE);
        error_message.setText(message);
    }
}
