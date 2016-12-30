package co.fabrk.booklisting;

import android.app.Application;

public class BookListApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SetConstantsFromXmlResources();
    }

    private void SetConstantsFromXmlResources() {
        Constants.HTTP_GET = getString(R.string.HTTP_GET);
        Constants.URL_BASE = getString(R.string.URL_BASE);
        Constants.URL_PARAM_QUERY = getString(R.string.URL_PARAM_QUERY);
        Constants.URL_PARAM_FIELDS = getString(R.string.URL_PARAM_FIELDS);
        Constants.URL_VALUE_FIELDS = getString(R.string.URL_VALUE_FIELDS);
        Constants.URL_PARAM_MAX_RESULT = getString(R.string.URL_PARAM_MAX_RESULT);
        Constants.URL_PARAM_START_INDEX = getString(R.string.URL_PARAM_START_INDEX);
        Constants.URL_PARAM_LANG_RESTRICT = getString(R.string.URL_PARAM_LANG_RESTRICT);
        Constants.URL_VALUE_LANG_RESTRICT = getString(R.string.URL_VALUE_LANG_RESTRICT);
        Constants.JSON_BOOK_ITEMS = getString(R.string.JSON_BOOK_ITEMS);
        Constants.JSON_BOOK_VOLUME_INFO = getString(R.string.JSON_BOOK_VOLUME_INFO);
        Constants.JSON_BOOK_TITLE = getString(R.string.JSON_BOOK_TITLE);
        Constants.JSON_BOOK_AUTHORS = getString(R.string.JSON_BOOK_AUTHORS);
        Constants.JSON_BOOK_ERROR = getString(R.string.JSON_BOOK_ERROR);

        Constants.STATUS_OK = getString(R.string.STATUS_OK);

        // Error Message
        Constants.ERROR_JSON_NO_AUTHOR = getString(R.string.ERROR_JSON_NO_AUTHOR);
        Constants.ERROR_JSON_NO_TITLE = getString(R.string.ERROR_JSON_NO_TITLE);
        Constants.ERROR_NETWORK_NO_NETWORK = getString(R.string.ERROR_NETWORK_NO_NETWORK);
        Constants.ERROR_NETWORK_NO_RESPONSE = getString(R.string.ERROR_NETWORK_NO_RESPONSE);
        Constants.ERROR_NETWORK_FILE_NOT_FOUND = getString(R.string.ERROR_NETWORK_FILE_NOT_FOUND);
        Constants.ERROR_NETWORK_BAD_REQUEST = getString(R.string.ERROR_NETWORK_BAD_REQUEST);
        // Error Messages
        Constants.ERROR_JSON_NO_ITEM = getString(R.string.ERROR_JSON_NO_ITEM);
        Constants.ERROR_JSON_INVALID_REQUEST_PARAMETER = getString(R.string.ERROR_JSON_INVALID_REQUEST_PARAMETER);
        Constants.ERROR_JSON_EMPTY_RESPONSE = getString(R.string.ERROR_JSON_EMPTY_RESPONSE);
        Constants.ERROR_JSON_UNEXPECTED_RESPONSE = getString(R.string.ERROR_JSON_UNEXPECTED_RESPONSE);
        Constants.ERROR_JSON_MALFORMED_RESPONSE = getString(R.string.ERROR_JSON_MALFORMED_RESPONSE);

        // User Messages
        Constants.MESSAGE_ENTER_TEXT = getString(R.string.MESSAGE_ENTER_TEXT);
        Constants.MESSAGE_NETWORK_DISCONNECTED = getString(R.string.MESSAGE_NETWORK_DISCONNECTED);
        Constants.MESSAGE_HOST_UNREACHABLE = getString(R.string.MESSAGE_HOST_UNREACHABLE);

        Constants.SAVE_STATE_KEY_CURRENT_PAGE = getString(R.string.SAVE_STATE_KEY_CURRENT_PAGE);
        Constants.SAVE_STATE_KEY_PAGE_SIZE = getString(R.string.SAVE_STATE_KEY_PAGE_SIZE);
        Constants.SAVE_STATE_KEY_BOOK_ARRAY_LIST = getString(R.string.SAVE_STATE_KEY_BOOK_ARRAY_LIST);
        Constants.SAVE_STATE_KEY_SCROLL_POSITION = getString(R.string.SAVE_STATE_KEY_SCROLL_POSITION);
        Constants.SAVE_STATE_KEY_USER_MESSAGE = getString(R.string.SAVE_STATE_KEY_USER_MESSAGE);
        Constants.SAVE_STATE_KEY_USER_MESSAGE_VISIBILITY = getString(R.string.SAVE_STATE_KEY_USER_MESSAGE_VISIBILITY);
        Constants.SAVE_STATE_KEY_LISTVIEW_VISIBILITY = getString(R.string.SAVE_STATE_KEY_LISTVIEW_VISIBILITY);
    }

}
