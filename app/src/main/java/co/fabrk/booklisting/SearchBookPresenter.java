package co.fabrk.booklisting;

import android.os.Bundle;

import java.util.ArrayList;

import co.fabrk.booklisting.model.GBook;

/**
 * Created by ebal on 21/12/16.
 */
interface SearchBookPresenter {
    void loadBookList(String query);
    Bundle saveInstanceState(Bundle outState);
    void restoreInstanceState(Bundle inState);
}
