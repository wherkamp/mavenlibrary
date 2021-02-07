package me.kingtux.mavenlibrary;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;

/**
 * Internal use class.
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

    public static void downloadFile(String downloadLocation, File finalFile) throws IOException {
        Request request = new Request.Builder()
                .url(downloadLocation)
                .build();
        Response execute = client.newCall(request).execute();
        if (!finalFile.exists()) {

            BufferedInputStream input = new BufferedInputStream(execute.body().byteStream());
            OutputStream output = new FileOutputStream(finalFile);

            byte[] data = new byte[1024];

            long total = 0;
            int count = 0;
            while ((count = input.read(data)) != -1) {
                total += count;
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();
        }
    }
}
