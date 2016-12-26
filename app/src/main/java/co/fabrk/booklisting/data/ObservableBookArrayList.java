package co.fabrk.booklisting.data;

import java.util.ArrayList;
import java.util.Observable;

import co.fabrk.booklisting.Constants;
import co.fabrk.booklisting.model.GBook;

/*********************************************************************/

public class ObservableBookArrayList extends Observable {

    ArrayList<GBook> mBookArrayList = new ArrayList<>();

    public void setBookArrayList(ArrayList<GBook> bookArrayList) {
        mBookArrayList = bookArrayList;
        setChanged();
    }

    public ArrayList<GBook> getBookArrayList() {
        return mBookArrayList;
    }

}
