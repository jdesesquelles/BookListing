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

        if (null != savedInstanceState) {
            onRestoreInstanceState(savedInstanceState);
        }
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
}
