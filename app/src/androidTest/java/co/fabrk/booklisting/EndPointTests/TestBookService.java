package co.fabrk.booklisting.EndPointTests;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import co.fabrk.booklisting.Constants;
import co.fabrk.booklisting.TestUtilities;
import co.fabrk.booklisting.data.BookService;
import co.fabrk.booklisting.model.GBook;

import static org.junit.Assert.*;

/**
 * Created by ebal on 20/12/16.
 */

@RunWith(AndroidJUnit4.class)
public class TestBookService {

    @Test
    public void getBookForQuery() {
        String query = "google";
        String page =  String.valueOf(0);
        String pageSize = String.valueOf(10);
        TestUtilities.TestObserver observable = TestUtilities.TestObserver.getTestContentObserver();
        BookService.registerObserver(observable);
        BookService.getBookForQuery(query, page, pageSize);
        observable.waitForNotificationOrFail();

        ArrayList<GBook> bookArrayList = observable.getBookArrayList();
        assertNotNull(bookArrayList);
        assertTrue(bookArrayList.size() >= 1);

        String status = observable.getmStatus();
        assertTrue(Constants.STATUS_OK.equals(status));

        BookService.removeObserver(observable);
    }

}
