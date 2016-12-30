package co.fabrk.booklisting.data;

import java.util.ArrayList;
import java.util.Observable;

import co.fabrk.booklisting.model.GBook;

/*********************************************************************/

public class ObservableBookArrayList extends Observable {

    private ArrayList<GBook> mBookArrayList = new ArrayList<>();

    public void setBookArrayList(ArrayList<GBook> bookArrayList) {
        mBookArrayList = bookArrayList;
        setChanged();
    }

    public ArrayList<GBook> getBookArrayList() {
        return mBookArrayList;
    }

}
