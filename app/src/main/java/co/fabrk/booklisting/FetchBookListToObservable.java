package co.fabrk.booklisting;

import android.os.AsyncTask;

import java.util.ArrayList;

import co.fabrk.booklisting.model.GBook;


/**
 * Created by ebal on 10/09/15.
 */
public class FetchBookListToObservable extends AsyncTask<String, Void, ArrayList<GBook>> {



    @Override
    protected void onPostExecute(ArrayList<GBook> arrayListBooks) {
            return;
    }

    @Override
    protected ArrayList<GBook> doInBackground(String... params) {
            return null; //BookService.getBookForQuery(params[0], params[1], params[2]);
    }


}
