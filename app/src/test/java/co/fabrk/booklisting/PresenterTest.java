package co.fabrk.booklisting;

import android.content.Context;
import android.net.ConnectivityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import co.fabrk.booklisting.booksearch.BookContract;
import co.fabrk.booklisting.booksearch.BookPresenter;
import co.fabrk.booklisting.data.BookService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PresenterTest {

    BookPresenter mBookPresenter;

    @Mock
    ConnectivityManager mConnectivityManager;

    @Mock
    BookService mBookService;

    @Mock
    BookContract.View mBookView;

    @Before
    public void setupABookPresenter() {
        MockitoAnnotations.initMocks(this);
        mBookPresenter = new BookPresenter(mBookView, null);
    }

    @Test
    public void clickOnSearch_LoadBookList() {
        String query = "test - clickOnSearch_LoadBookList";
        String page = "0";
        String pageSize = "10";
        mBookPresenter.loadBookList(query);
        verify(mBookService).getBookForQuery(query, page, pageSize);
    }

    @Test
    public void clickOnSearch_emptyTextErrorUi() {
        mBookPresenter.loadBookList(Constants.NULL_STRING);
        verify(mBookView).showErrorMessage(Constants.MESSAGE_ENTER_TEXT);
    }

}