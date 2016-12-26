package co.fabrk.booklisting.data;

import java.util.ArrayList;
import java.util.Observable;

import co.fabrk.booklisting.Constants;
import co.fabrk.booklisting.model.GBook;

/*********************************************************************/

public class ObservableBookArrayList extends Observable {

    ArrayList<GBook> mBookArrayList = new ArrayList<>();

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    String mStatus = Constants.STATUS_OK;

    public void setBookArrayList(ArrayList<GBook> bookArrayList) {
        mBookArrayList = bookArrayList;
        setChanged();
    }

    public String getStatus() {
        return mStatus;
    }





    public ArrayList<GBook> getBookArrayList() {
        return mBookArrayList;
    }

}
