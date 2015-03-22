package baches.jperez.soy.utils;

/**
 * Created by jonatan on 22/03/15.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

import android.util.Base64;
import android.util.Log;

public class SoyBacheroUtils {

    public static String getTimelineForSearchTerm(){

        HttpURLConnection httpConnection = null;
        BufferedReader bufferedReader = null;
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(ConstantsUtils.URL_GETBACHES);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");

            httpConnection.setRequestProperty("Authorization", "6d57ac3ec2cdf81d7b50ee23151db681f0161253");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.connect();

            bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null){
                response.append(line);
            }

            Log.d("log1", "GET response code: " + String.valueOf(httpConnection.getResponseCode()));
            Log.d("log2", "JSON response: " + response.toString());

            return response.toString();

        } catch (Exception e) {
            Log.e("log3", "GET error: " + Log.getStackTraceString(e));
            return null;

        }finally {
            if(httpConnection != null){
                httpConnection.disconnect();
            }
        }
    }

}
