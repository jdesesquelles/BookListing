package co.fabrk.booklisting;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.HandlerThread;
import android.support.test.InstrumentationRegistry;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import co.fabrk.booklisting.data.BookService;
import co.fabrk.booklisting.data.ObservableBookArrayList;
import co.fabrk.booklisting.model.GBook;
import co.fabrk.booklisting.utils.PollingCheck;

/**
 * Created by ebal on 20/12/16.
 */

public class TestUtilities {

    static public  Boolean isNetworkConnected() {
        Context context = InstrumentationRegistry.getTargetContext();
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    static public  Boolean isNetworkDisconnected() {
        return !isNetworkConnected();
    }

    public static class TestObserver implements Observer {
        ArrayList<GBook> bookArrayList = new ArrayList<>();
        String mStatus;
        boolean mContentChanged;
        final HandlerThread mHT;

        public ArrayList<GBook> getBookArrayList() {
            return bookArrayList;
        }

        public String getmStatus() {
            return mStatus;
        }

        @Override
        public void update(Observable observable, Object o) {
            bookArrayList = ((ObservableBookArrayList) observable).getBookArrayList();
            mStatus  = BookService.getStatus();
            mContentChanged = true;
        }

        public static TestObserver getTestContentObserver() {
            HandlerThread ht = new HandlerThread("ObserverThread");
            ht.start();
            return new TestObserver(ht);
        }

        public TestObserver(HandlerThread ht) {
            mHT = ht;
        }

        public void waitForNotificationOrFail() {
            // Note: The PollingCheck class is taken from the Android CTS (Compatibility Test Suite).
            // It's useful to look at the Android CTS source for ideas on how to test your Android
            // applications.  The reason that PollingCheck works is that, by default, the JUnit
            // testing framework is not running on the main Android application thread.
            new PollingCheck(5000) {
                @Override
                protected boolean check() {
                    return mContentChanged;
                }
            }.run();
            mHT.quit();
        }
    }

}
