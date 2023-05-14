package it.uninsubria.emotionalsongs.model;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://localhost:5555/emotional-songs");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);

        OutputStream os = con.getOutputStream();
        os.write("json".getBytes());
        os.flush();
        os.close();
        int responseCode = con.getResponseCode();
        con.disconnect();
    }
}
