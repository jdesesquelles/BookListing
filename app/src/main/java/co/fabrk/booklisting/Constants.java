package co.fabrk.booklisting;

/**
 * Constants class containing public static value accessible from everywhere in the code
 * to minimize context references
 */

public class Constants {

    public static String HTTP_GET;
    public static final String NULL_STRING = "";

    // Google Books API Url constants
    public static String URL_BASE;
    public static String URL_PARAM_QUERY;
    //  Field Selection
    public static String URL_PARAM_FIELDS;
    public static String URL_VALUE_FIELDS;
    //  paging
    public static String URL_PARAM_MAX_RESULT;
    public static String URL_PARAM_START_INDEX;
    //  langRestrict="en" or "fr" (two-letter ISO-639-1 code).
    public static String URL_PARAM_LANG_RESTRICT;
    public static String URL_VALUE_LANG_RESTRICT;

    // JSON Objects Name
    public static String JSON_BOOK_ITEMS;
    public static String JSON_BOOK_VOLUME_INFO;
    public static String JSON_BOOK_TITLE;
    public static String JSON_BOOK_AUTHORS;
    public static String JSON_BOOK_ERROR;

    // Error Messages
    public static String ERROR_JSON_NO_AUTHOR;
    public static String ERROR_JSON_NO_TITLE;
    public static String ERROR_JSON_NO_ITEM;
    public static String ERROR_JSON_INVALID_REQUEST_PARAMETER;
    public static String ERROR_JSON_EMPTY_RESPONSE;
    public static String ERROR_JSON_UNEXPECTED_RESPONSE;
    public static String ERROR_JSON_MALFORMED_RESPONSE;

    // BookService state variable values
    public static String STATUS_OK;
    public static String ERROR_NETWORK_NO_NETWORK;
    public static String ERROR_NETWORK_NO_RESPONSE;
    public static String ERROR_NETWORK_FILE_NOT_FOUND;
    public static String ERROR_NETWORK_BAD_REQUEST;

    // User Messages
    public static String MESSAGE_ENTER_TEXT;
    public static String MESSAGE_NETWORK_DISCONNECTED;
    public static String MESSAGE_HOST_UNREACHABLE;

    // Instance State Value Keys
    public static String SAVE_STATE_KEY_CURRENT_PAGE;
    public static String SAVE_STATE_KEY_PAGE_SIZE;
    public static String SAVE_STATE_KEY_BOOK_ARRAY_LIST;
    public static String SAVE_STATE_KEY_SCROLL_POSITION;
    public static String SAVE_STATE_KEY_USER_MESSAGE;
    public static String SAVE_STATE_KEY_USER_MESSAGE_VISIBILITY;
    public static String SAVE_STATE_KEY_LISTVIEW_VISIBILITY;

}
