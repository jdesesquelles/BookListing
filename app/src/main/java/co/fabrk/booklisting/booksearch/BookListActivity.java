package co.fabrk.booklisting.booksearch;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;

import co.fabrk.booklisting.R;
import co.fabrk.booklisting.booksearch.BookContract;
import co.fabrk.booklisting.booksearch.BookPresenter;
import co.fabrk.booklisting.booksearch.BookView;

public class BookListActivity extends AppCompatActivity {

    BookView mBookView;
    BookContract.SearchBookActionListener mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_books);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBookView = new BookView(findViewById(R.id.root_view), LayoutInflater.from(this));
        mPresenter = new BookPresenter(mBookView, (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE));
        mBookView.setActionListener(mPresenter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = mPresenter.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPresenter.restoreInstanceState(savedInstanceState);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_search_books, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
