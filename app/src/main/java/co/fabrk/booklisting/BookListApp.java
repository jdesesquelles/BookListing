package co.fabrk.booklisting;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

public class BookListApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initConstantsFromXmlResources();

    }

    private void initConstantsFromXmlResources() {
        Constants.HTTP_GET = getString(R.string.HTTP_GET);
        Constants.URL_BASE = getString(R.string.URL_BASE);
        Constants.URL_PARAM_QUERY = getString(R.string.URL_PARAM_QUERY);
        Constants.URL_PARAM_FIELDS = getString(R.string.URL_PARAM_FIELDS);
        Constants.URL_VALUE_FIELDS = getString(R.string.URL_VALUE_FIELDS);
        Constants.URL_PARAM_MAX_RESULT = getString(R.string.URL_PARAM_MAX_RESULT);
        Constants.URL_PARAM_START_INDEX = getString(R.string.URL_PARAM_START_INDEX);
        Constants.URL_PARAM_LANG_RESTRICT = getString(R.string.URL_PARAM_LANG_RESTRICT);
        Constants.URL_VALUE_LANG_RESTRICT = getString(R.string.URL_VALUE_LANG_RESTRICT);
        Constants.JSON_BOOK_ITEMS = getString(R.string.BOOK_JSON_ITEMS);
        Constants.JSON_BOOK_VOLUME_INFO = getString(R.string.BOOK_JSON_VOLUME_INFO);
        Constants.JSON_BOOK_TITLE = getString(R.string.BOOK_JSON_TITLE);
        Constants.JSON_BOOK_AUTHORS = getString(R.string.BOOK_JSON_AUTHORS);
        Constants.ERROR_JSON_NO_AUTHOR = getString(R.string.ERROR_JSON_NO_AUTHOR);
        Constants.ERROR_JSON_NO_TITLE = getString(R.string.ERROR_JSON_NO_TITLE);
        Constants.ERROR_NETWORK_NO_NETWORK = getString(R.string.ERROR_NETWORK_NO_NETWORK);
        Constants.ERROR_NETWORK_NO_RESPONSE = getString(R.string.ERROR_NETWORK_NO_SERVER);
        Constants.ERROR_NETWORK_FILE_NOT_FOUND = getString(R.string.ERROR_NETWORK_FILE_NOT_FOUND);
        Constants.ERROR_NETWORK_BAD_REQUEST = getString(R.string.ERROR_NETWORK_BAD_REQUEST);
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        return Utilities.isNetworkConnected(cm);

    }
}
