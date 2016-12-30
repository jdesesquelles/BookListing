package co.fabrk.booklisting.EndUserTests;

import android.content.pm.ActivityInfo;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.fabrk.booklisting.Constants;
import co.fabrk.booklisting.R;
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

/**
 * Created by ebal on 24/12/16.
 */

@RunWith(AndroidJUnit4.class)
public class TestBookScreen {

    @Rule
    public ActivityTestRule<BookListActivity> mBookListActivityTestRule =
            new ActivityTestRule<>(BookListActivity.class);

    @Test
    public void clickSearchButton_DisplayNoResultFoundMessageUi(){
        get("xdfgbhcujendisorknc");
        checkUserMessage(Constants.ERROR_JSON_EMPTY_RESPONSE);
    }

    @Test
    public void clickSearchButton_DisplayUpdatedListUi(){
        get("Android");
        checkListView();
    }

    @Test
    public void clickSearchButton_DisplayMessageOnStartUi(){
        // Check if the add note screen is displayed
//        onView(withId(R.id.books_recycler_view)).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.GONE))));
//        onView(withId(R.id.empty_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
//        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
//        onView(withId(R.id.error_message)).check(matches(withText(Constants.MESSAGE_ENTER_TEXT)));
        checkUserMessage(Constants.MESSAGE_ENTER_TEXT);

    }

    @Test
    public void clickSearchButton_ErrorMessageUi(){
        get("");
//        String query = "";
//        // Click on the add note button
//        onView(withId(R.id.search_books_edit_text_search)).perform(typeText(query), closeSoftKeyboard());
//        onView(withId(R.id.search_button)).perform(click());

        // Check if the add note screen is displayed
//        onView(withId(R.id.books_recycler_view)).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.GONE))));
//        onView(withId(R.id.empty_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
//        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
//        onView(withId(R.id.error_message)).check(matches(withText(Constants.MESSAGE_ENTER_TEXT)));
        checkUserMessage(Constants.MESSAGE_ENTER_TEXT);

    }

    @Test
    public void clickSearchButton_NoServerError() {
        String query = "Android";
        Constants.URL_BASE = "http://noserver";
        onView(ViewMatchers.withId(R.id.search_books_edit_text_search)).perform(typeText(query), closeSoftKeyboard());
        onView(withId(R.id.search_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.search_button)).perform(click());

        // Check if the host unreachable message is displayed
        onView(withId(R.id.books_recycler_view)).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.GONE))));
        onView(withId(R.id.empty_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.error_message)).check(matches(withText(Constants.ERROR_NETWORK_NO_RESPONSE)));

        Constants.URL_BASE = "https://www.googleapis.com/books/v1/volumes?";
    }

    @Test
    public void onSave_restoreCorrectly() {
        mBookListActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        get("Android");
        checkListView();
        mBookListActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        checkListView();
        mBookListActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        checkListView();
    }

    private void get(String query) {
        onView(withId(R.id.search_books_edit_text_search)).perform(typeText(query), closeSoftKeyboard());
        onView(withId(R.id.search_button)).perform(click());
    }

    private void checkUserMessage(String message) {
        onView(withId(R.id.books_recycler_view)).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.GONE))));
        onView(withId(R.id.empty_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.error_message)).check(matches(withText(message)));
    }

    private void checkListView() {
        onView(withId(R.id.books_recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.empty_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

}
