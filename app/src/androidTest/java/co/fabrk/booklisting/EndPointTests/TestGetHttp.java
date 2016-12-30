package co.fabrk.booklisting.EndPointTests;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.fabrk.booklisting.Constants;
import co.fabrk.booklisting.TestUtilities;
import co.fabrk.booklisting.Utilities;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestGetHttp {

    static Context context = InstrumentationRegistry.getTargetContext();
    // https://www.googleapis.com/books/v1/volumes?q=ndk&maxResults=10&fields=items(volumeInfo/title,volumeInfo/authors)


    /**
     * *  Testing Error cases
     **/

    @Test
    public void getHttpNoNetworkTest() {
        // When No network Connection: getHttpResponse should return ERROR_NETWORK_NO_NETWORK
        if (TestUtilities.isNetworkDisconnected()) {
            String MethodResult = Utilities.getHttpResponse("https://www.googleapisw.com/books/v1/volumes?q=ndk&maxResults=10");
            Assert.assertEquals(Constants.ERROR_NETWORK_NO_NETWORK, MethodResult);
        } else assertTrue(true);
    }

    @Test
    public void getHttpNoServerTest() {
        // when server is unknown: getHttpResponse should return ERROR_NETWORK_NO_RESPONSE
        String MethodResult = Utilities.getHttpResponse("https://www.googleapisw.com/books/v1/volumes?q=ndk&maxResults=10");
        assertEquals(Constants.ERROR_NETWORK_NO_RESPONSE, MethodResult);
    }

    @Test
    public void getHttpNotFoundTest() {
        String MethodResult = Utilities.getHttpResponse("https://www.googleapis.com/books/v1/volumesxx?q=ndk&maxResults=10");
        assertEquals(Constants.ERROR_NETWORK_FILE_NOT_FOUND, MethodResult);
    }


}
