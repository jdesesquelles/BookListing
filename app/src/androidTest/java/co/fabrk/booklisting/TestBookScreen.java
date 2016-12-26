package co.fabrk.booklisting;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import co.fabrk.booklisting.booksearch.BookListActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;


/**
 * Created by ebal on 24/12/16.
 */

@RunWith(AndroidJUnit4.class)
public class TestBookScreen {

    static Context context = InstrumentationRegistry.getTargetContext();

    @Rule
    public ActivityTestRule<BookListActivity> mBookListActivityTestRule =
            new ActivityTestRule<>(BookListActivity.class);

    @Test
    public void clickSearchButton_DisplayMessageOnStartUi(){
        // Check if the add note screen is displayed
        onView(withId(R.id.books_recycler_view)).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.GONE))));
        onView(withId(R.id.empty_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.error_message)).check(matches(withText(Constants.MESSAGE_ENTER_TEXT)));
    }

    @Test
    public void clickSearchButton_DisplayUpdatedListUi(){
        String query = "Android";
        // Click on the add note button
        onView(withId(R.id.search_books_edit_text_search)).perform(typeText(query), closeSoftKeyboard());
        onView(withId(R.id.search_button)).perform(click());

        // Check if the add note screen is displayed
        onView(withId(R.id.books_recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.empty_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @Test
    public void clickSearchButton_ErrorMessageUi(){
        String query = "";
        // Click on the add note button
        onView(withId(R.id.search_books_edit_text_search)).perform(typeText(query), closeSoftKeyboard());
        onView(withId(R.id.search_button)).perform(click());

        // Check if the add note screen is displayed
        onView(withId(R.id.books_recycler_view)).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.GONE))));
        onView(withId(R.id.empty_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.error_message)).check(matches(withText(Constants.MESSAGE_ENTER_TEXT)));
    }

    @Test
    public void clickSearchButton_NoServerError() {
        String query = "Android";
        // Click on the add note button
        Constants.URL_BASE = "http://noserver";
        onView(withId(R.id.search_books_edit_text_search)).perform(typeText(query), closeSoftKeyboard());
        onView(withId(R.id.search_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.search_button)).perform(click());

        // Check if the add note screen is displayed
        onView(withId(R.id.books_recycler_view)).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.GONE))));
        onView(withId(R.id.empty_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.error_message)).check(matches(withText(Constants.MESSAGE_HOST_UNREACHABLE)));
    }
}
