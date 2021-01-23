package com.example.postrequestserver.utill;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class GetRequest {

    private HttpURLConnection con = null;

    public String getTestData(String fullUrl){
        try {
            String inputLine = null;
            URL obj = new URL(fullUrl);

            con = (HttpURLConnection) obj.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept-Charset", "UTF-8");
            con.setConnectTimeout(10000);
            con.setReadTimeout(1800000);

            Charset charset = Charset.forName("UTF-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));

            StringBuffer outResult = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                outResult.append(inputLine);
            }
            con.disconnect();

            return outResult+"";
        } catch (Exception e) {
            return null;
        }
    }

}