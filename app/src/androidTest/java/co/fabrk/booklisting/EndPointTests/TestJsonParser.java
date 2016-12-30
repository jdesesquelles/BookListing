package co.fabrk.booklisting.EndPointTests;

import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;

import co.fabrk.booklisting.Constants;
import co.fabrk.booklisting.TestConstants;
import co.fabrk.booklisting.data.BookService;
import co.fabrk.booklisting.model.GBook;

import static org.junit.Assert.*;


/**
 * Created by ebal on 27/12/16.
 */
@RunWith(AndroidJUnit4.class)
public class TestJsonParser {


    @Test
    public void JsonValid_returnArrayListBook() {
        // Entry 1
        String author1 = "Onur Cinar";
        String title1 = "Pro Android C++ with the NDK";

        // Entry 2
        String[] author2 = {"Eugène", "Georges", "Henri", "Céleste baron Stoffel"};
        String title2 = "Histoire de Jules César guerre civile: Du passage du Rubicon à la bataille";

        ArrayList<GBook> returnedBookArrayList;
        returnedBookArrayList = BookService.getBookListFromJson(TestConstants.JSON_VALID_RESPONSE);

        assertTrue(title1.equals(returnedBookArrayList.get(0).getTitle()));
        assertTrue(title2.equals(returnedBookArrayList.get(1).getTitle()));
        for (int i = 0; i < returnedBookArrayList.get(0).getAuthors().size(); i++) {
            assertTrue(author1.equals(returnedBookArrayList.get(0).getAuthors().get(i)));
        }
        for (int i = 0; i < returnedBookArrayList.get(1).getAuthors().size(); i++) {
            assertTrue(author2[i].equals(returnedBookArrayList.get(1).getAuthors().get(i)));
        }
    }

    @Test
    public void JsonErrorResponse_returnInvalideRequestErrorCode() {
        BookService.getBookListFromJson(TestConstants.JSON_ERROR_RESPONSE);
        assertTrue(BookService.getStatus().equals(Constants.ERROR_JSON_INVALID_REQUEST_PARAMETER));
    }

    @Test
    public void JsonEmptyResponse_returnEmptyErrorCode() {
        BookService.getBookListFromJson(TestConstants.JSON_EMPTY_RESPONSE);
        assertTrue(BookService.getStatus().equals(Constants.ERROR_JSON_EMPTY_RESPONSE));
    }

    @Test
    public void jsonUnexpectedResponse_returnUnexpectedStatusCode() {
        BookService.getBookListFromJson(TestConstants.JSON_UNEXPECTED_RESPONSE);
        assertTrue(BookService.getStatus().equals(Constants.ERROR_JSON_UNEXPECTED_RESPONSE));
    }


    @Test
    public void JsonErrorNoTitle_returnNoTitleValue() {
        String title = Constants.ERROR_JSON_NO_TITLE;
        String author = "Onur Cinar";

        ArrayList<GBook> returnedBookArrayList;
        returnedBookArrayList = BookService.getBookListFromJson(TestConstants.JSON_NO_TITLE_RESPONSE);

        assertTrue(title.equals(returnedBookArrayList.get(0).getTitle()));
        for (int i = 0; i < returnedBookArrayList.get(0).getAuthors().size(); i++) {
            assertTrue(author.equals(returnedBookArrayList.get(0).getAuthors().get(i)));
        }
        assertTrue(2 == returnedBookArrayList.size());
    }

    @Test
    public void JsonErrorNoAuthor_returnNoAuthor() {
        String title = "Pro Android C++ with the NDK";
        String author = Constants.ERROR_JSON_NO_AUTHOR;

        ArrayList<GBook> returnedBookArrayList;
        returnedBookArrayList = BookService.getBookListFromJson(TestConstants.JSON_NO_AUTHOR_RESPONSE);

        assertTrue(title.equals(returnedBookArrayList.get(0).getTitle()));
        assertTrue(author.equals(returnedBookArrayList.get(0).getAuthors().get(0)));
        assertTrue(2 == returnedBookArrayList.size());
    }

    @Test
    public void JsonErrorNoVolumeInfo_returnEmptyErrorCode() {
        BookService.getBookListFromJson(TestConstants.JSON_NO_VOLUME_INFO_RESPONSE);
        assertTrue(BookService.getStatus().equals(Constants.ERROR_JSON_EMPTY_RESPONSE));
    }

    @Test
    public void JsonMalformed_returnMalformedErrorCode() {
        BookService.getBookListFromJson(TestConstants.JSON_MALFORMED_RESPONSE);
        assertTrue(BookService.getStatus().equals(Constants.ERROR_JSON_MALFORMED_RESPONSE));
    }

}
