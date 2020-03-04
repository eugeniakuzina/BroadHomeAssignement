package com.company.controller;

import com.company.model.trips.TripsData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;

public class TripsController {

    public static TripsData getTrips(String tripId) throws IOException {
        String trips = "trips/";
        String include = "?include=stops";
        URL tripsUrl = new URL(Connector.mbta + trips + tripId + include);
        Reader reader = new Connector().getGson(tripsUrl);

        Type dataType = new TypeToken<TripsData>() {}.getType();
        return new Gson().fromJson(reader, dataType);
    }

    public static TripsData getTrips(List<String> tripIds) throws IOException {
        String tripsUrl = "trips/";
        String include = "?include=stops";

        String trips = "";
        for (String tripId : tripIds) {
            trips = trips.concat(tripId);
            trips = trips.concat(",");
        }

        String idsWithoutComma = trips.substring(0, trips.length() - 1);

        Reader reader = new Connector().getGson(new URL(Connector.mbta + tripsUrl + idsWithoutComma + include));

        Type dataType = new TypeToken<TripsData>() {}.getType();
        return new Gson().fromJson(reader, dataType);
    }

}
