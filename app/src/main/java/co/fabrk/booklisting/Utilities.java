package co.fabrk.booklisting;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Created by ebal on 19/12/16.
 */

public class Utilities {

    private static String NO_NETWORK = "No Network";
    private static String NO_SERVER = "No Response from the Server";

    public static String getHttpResponse(String stringUrl) {
        // String Url as an input, JSON String as the output
        String connectionMethod = Constants.HTTP_GET;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String response = null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(stringUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(connectionMethod);
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            response = buffer.toString();
        } catch (UnknownHostException e) {
                response = Constants.ERROR_NETWORK_NO_NETWORK;
        } catch (FileNotFoundException e) {
            response = Constants.ERROR_NETWORK_FILE_NOT_FOUND;
        } catch (IOException e) {
            response = Constants.ERROR_NETWORK_NO_RESPONSE;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
            return response;
        }
    }

}
