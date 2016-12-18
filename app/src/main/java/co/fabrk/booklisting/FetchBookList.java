package co.fabrk.booklisting;

import android.os.AsyncTask;
import java.util.ArrayList;
import co.fabrk.booklisting.data.BookService;
import co.fabrk.booklisting.model.GBook;


/**
 * Created by ebal on 10/09/15.
 */
public class FetchBookList extends AsyncTask<String, Void, ArrayList<GBook>> {

    public interface BookListCaller {
        void updateBookList(ArrayList<GBook> bookArrayList);
    }

    private BookListCaller mCaller;

    public FetchBookList(BookListCaller listener) {
        mCaller = listener;
    }

    @Override
    protected void onPostExecute(ArrayList<GBook> arrayListBooks) {
        if (null == arrayListBooks) {
            return;
        } else mCaller.updateBookList(arrayListBooks);

    }

    @Override
    protected ArrayList<GBook> doInBackground(String... params) {
            return BookService.getNextPage(params[0], params[1], params[2]);
    }


}
