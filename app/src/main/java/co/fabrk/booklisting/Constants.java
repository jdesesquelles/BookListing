package co.fabrk.booklisting;

/**
 * Constants class containing public static value accessible from everywhere in the code
 * to minimize context reference
 */

public class Constants {
    public static String HTTP_GET;
    public static String URL_BASE =
            "https://www.googleapis.com/books/v1/volumes?";
    public static String URL_PARAM_QUERY = "q";
    // Field Selection
    public static String URL_PARAM_FIELDS = "fields";
    public static String URL_VALUE_FIELDS = String.valueOf("items(volumeInfo/title,volumeInfo/authors)");
    public static String URL_PARAM_MAX_RESULT = "maxResults";
    public static String URL_PARAM_START_INDEX = "startIndex";
    //    langRestrict="en" or "fr" (two-letter ISO-639-1 code).
    public static String URL_PARAM_LANG_RESTRICT = "langRestrict";
    public static String URL_VALUE_LANG_RESTRICT = "en";

    public static String JSON_BOOK_ITEMS = "items";
    public static String JSON_BOOK_VOLUME_INFO = "volumeInfo";
    public static String JSON_BOOK_TITLE = "title";
    public static String JSON_BOOK_AUTHORS = "authors";

    public static String NO_AUTHOR = "No author found";
    public static String NO_TITLE = "Title not found";
    public static String ERR_MESSAGE_NO_SERVER = "No Response from the Server";
    public static String ERROR_JSON_NO_AUTHOR = "No author found";
    public static String ERROR_JSON_NO_TITLE = "Title not found";
    public static String ERROR_JSON_NO_ITEM = "Title not found";
    public static String ERROR_JSON_NO_VOLUME_INFO = "Title not found";
    public static String ERROR_NETWORK_NO_NETWORK = "No Network, Check connectivity";
    public static String ERROR_NETWORK_NO_RESPONSE = "No Response from the Server";
    public static String ERROR_NETWORK_FILE_NOT_FOUND = "File Not Found";
    public static String ERROR_NETWORK_BAD_REQUEST = "Bad Request";
    public static String STATUS_OK = "Status OK";
    public static String MESSAGE_HOST_UNREACHABLE = "No Response from the server, Retry Later.";
    public static String MESSAGE_NO_ITEM_FOUND = "No item found in the response, Report an issue";
    public static String MESSAGE_UNKNOWN_ERROR = "Unknown Error";
    public static String MESSAGE_FILE_NOT_FOUND = "File not found on the server";
    public static String STATUS_HTTP_RESPONSE_OK = "STATUS_HTTP_RESPONSE_OK";
    public static String ERR_MESSAGE_NO_NETWORK = "Unable to resolve host";
    public static String SAVE_STATE_KEY_CURRENT_PAGE = "CurrentPage";
    public static String SAVE_STATE_KEY_PAGE_SIZE = "PageSize";
    public static String SAVE_STATE_KEY_BOOK_ARRAY_LIST = "BookArrayList";
    public static String SAVE_STATE_SCROLL_POSITION = "ScrollPosition";



}
