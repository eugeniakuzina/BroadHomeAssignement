package com.company.controller;

import com.company.model.routes.RoutesData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;

public class RoutesController {

    public static RoutesData getSubwaysData() throws IOException {
        String routes = "routes?filter[type]=0,1";

        URL routesUrl = new URL(Connector.mbta + routes);
        Reader reader = new Connector().getGson(routesUrl);

        Type dataType = new TypeToken<RoutesData>() {}.getType();
        return new Gson().fromJson(reader, dataType);
    }

}
