package com.l524l.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class GetRequest {
    private boolean RequestError = false;

    public boolean isRequestError() {
        return RequestError;
    }

    public String makeRequest(String city, String contryCode){
        StringBuffer stringBuffer = new StringBuffer();
        try {
            city = URLEncoder.encode(city, "utf-8");
            String url = "https://api.weatherbit.io/v2.0/current?city=" + city +","+ contryCode + "&key=dd7ff70ea0b9468ca0fb3a73c0a4bff8&lang=ru";
            URL getweather = new URL(url);
            URLConnection connection = getweather.openConnection();
            connection.setConnectTimeout(5000);
            Reader reader = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
        }catch (Exception e){
            RequestError = true;
            return e.getMessage();
        }
        return stringBuffer.toString();
    }
}
