package me.kingtux.mavenlibrary;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Internal use class.
 *
 */
public class WebHelper {
    private static final OkHttpClient client = new OkHttpClient();

    public static boolean doesFileExist(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response execute = client.newCall(request).execute()) {
            if (execute.isSuccessful()) {
                if (execute.code() == 200) return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
