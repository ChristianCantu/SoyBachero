package baches.jperez.soy.utils;

/**
 * Created by jonatan on 22/03/15.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import android.util.Base64;
import android.util.Log;

public class SoyBacheroUtils {

    public static String getTimelineForSearchTerm(){


        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        String result = "";

        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet get = new HttpGet(ConstantsUtils.URL_GETBACHES);

            String accessCredential = "6d57ac3ec2cdf81d7b50ee23151db681f0161253";
            String authorization = ("token " + accessCredential);
            get.setHeader("Authorization",authorization);
           // get.setHeader("content-type", "application/json);

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(get);

            InputStream stream = httpResponse.getEntity().getContent();



            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer("");
            String line = "";
            String newline = System.getProperty("line.separator");
            while((line = reader.readLine()) != null)
            {
                buffer.append(line + newline);

            }
            reader.close();
           String data = buffer.toString();

           Log.d("RESPUESTA:::::::!",data);

            return data;

        } catch (Exception e) {
            Log.e("log3", "GET error: " + Log.getStackTraceString(e));
            return null;

        }finally {

        }

    }
    // convert inputstream to String
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }//fin clase convert

}
