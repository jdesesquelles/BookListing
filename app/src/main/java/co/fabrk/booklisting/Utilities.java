package co.fabrk.booklisting;

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

    public static String getHttpResponse(String stringUrl) {
        // String Url as an input, JSON String as the output
        String connectionMethod = Constants.HTTP_GET;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String response = null;
        StringBuilder buffer = new StringBuilder();
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
                buffer.append(line).append("\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            response = buffer.toString();
        } catch (UnknownHostException e) {
                response = Constants.ERROR_NETWORK_NO_RESPONSE;
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            response = Constants.ERROR_NETWORK_FILE_NOT_FOUND;
            e.printStackTrace();
        } catch (IOException e) {
            response = Constants.ERROR_NETWORK_NO_RESPONSE;
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException ignored) {
                }
            }
        }
        return response;

    }

    static public  Boolean isNetworkConnected(ConnectivityManager connectivityManager) {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

}
