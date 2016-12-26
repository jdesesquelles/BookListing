package co.fabrk.booklisting.booksearch;

import android.os.Bundle;
import java.util.ArrayList;
import co.fabrk.booklisting.model.GBook;

/**
 * This specifies the contract between the view and the mPresenter.
 */

public interface BookContract {

    interface View{
        void updateBookListView(ArrayList<GBook> bookArrayList);
        Bundle saveInstanceState(Bundle outState);
        void restoreInstanceState(Bundle inState);
        void showErrorMessage(String message);
        void setActionListener(SearchBookActionListener listener);

    }


    interface SearchBookActionListener {
        void loadBookList(String query);
        Bundle saveInstanceState(Bundle outState);
        void restoreInstanceState(Bundle inState);
    }
}
