package com.company.controller;

import com.company.model.stop.StopData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;

public class StopsController {

    public static StopData getStops(List<String> stopIds) throws IOException {
        String stopsUrl = "stops?filter[id]=";
        // include is not used because /stops endpoint returns not all stops if it's included, see readme
        String include = "?&include=parent_station";

        String stops = "";
        for (String stopId : stopIds) {
            stops = stops.concat(stopId);
            stops = stops.concat(",");
        }

        String idsWithoutComma = stops.substring(0, stops.length() - 1);

        URL tripsUrl = new URL(Connector.mbta + stopsUrl + idsWithoutComma /* + include */);
        Reader reader = new Connector().getGson(tripsUrl);

        Type dataType = new TypeToken<StopData>() {}.getType();
        return new Gson().fromJson(reader, dataType);
    }
}
