package com.company.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.testng.Assert.assertEquals;

public class Connector {
    public static String mbta = "https://api-v3.mbta.com/";
    public static String key = "f1ee51563a1345ee996f83e5be9d12ea";

    public InputStreamReader getGson(URL connectionURL) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) connectionURL.openConnection();
        urlConnection.setRequestProperty("x-api-key", key);

        assertEquals(urlConnection.getResponseCode(), HttpURLConnection.HTTP_OK, "Request failed");

        return new InputStreamReader(urlConnection.getInputStream());
    }
}
