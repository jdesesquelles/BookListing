package co.fabrk.booklisting;

/**
 * Created by ebal on 16/12/16.
 */

public class Constants {
    public static String TAG_TITLE = "book_title";
    public static String TAG_AUTHOR = "book_author";

    public final String URL_PARAM_MAX_RESULT = "maxResults";
//    public final String URL_VALUE_MAX_RESULT = String.valueOf(pageSize);
    public final String URL_PARAM_START_INDEX = "startIndex";
//    public final String URL_VALUE_START_INDEX = String.valueOf(startIndex);

    //    langRestrict="en" or "fr" (two-letter ISO-639-1 code).
    public final String URL_PARAM_LANG_RESTRICT = "langRestrict";
    public final String URL_VALUE_LANG_RESTRICT = "en";
    //    filter=partial, full, free-ebooks, paid-ebooks, ebooks

    final String BOOK_JSON_ITEMS = "items";
    final String BOOK_JSON_VOLUME_INFO = "volumeInfo";
    final String BOOK_JSON_TITLE = "title";
    final String BOOK_JSON_AUTHORS = "authors";

    public String NO_AUTHOR = "No author found";
    public String NO_TITLE = "Title not found";
}
