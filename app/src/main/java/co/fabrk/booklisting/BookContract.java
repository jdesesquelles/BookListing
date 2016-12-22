package co.fabrk.booklisting;

import android.os.Bundle;
import java.util.ArrayList;
import co.fabrk.booklisting.model.GBook;

/**
 * This specifies the contract between the view and the mPresenter.
 */

public interface BookContract {

    interface View{
        public void updateBookListView(ArrayList<GBook> bookArrayList);
        public Bundle saveInstanceState(Bundle outState);
        public void restoreInstanceState(Bundle inState);
        }


    interface SearchBookActionListener {
        void loadBookList(String query);
        Bundle saveInstanceState(Bundle outState);
        void restoreInstanceState(Bundle inState);
    }
}
