package co.fabrk.booklisting.data;

/**
 * Created by ebal on 23/12/16.
 */

public interface BookServiceApi {
    void getBookForQuery(String query, String page, String pageSize);
}
