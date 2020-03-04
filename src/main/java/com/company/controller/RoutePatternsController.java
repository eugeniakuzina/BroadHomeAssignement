package com.company.controller;

import com.company.model.routepatterns.RoutePatternsData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;

public class RoutePatternsController {

    private static String routePatterns = "route_patterns?filter[route]=";
    private static String filter = "&filter[direction_id]=";
    // sort by typicality to find the routes with typicality = 1, which are the most typical
    private static String sort = "&sort=typicality";

    // this can also be done by sending one request to /route_patterns&filter[route]=Red,Orange...
    public static RoutePatternsData getOneWayRoutePatterns(String routeId) throws IOException {
        // direction 0 is straight way
        URL routePatternUrl = new URL(Connector.mbta + routePatterns + routeId + filter + "0" + sort);
        Reader reader = new Connector().getGson(routePatternUrl);

        Type dataType = new TypeToken<RoutePatternsData>() {}.getType();
        return new Gson().fromJson(reader, dataType);
    }

    /**
     * This method is not used in current implementation but can be used later to check if straight way and reverse
     * way have the same number of stops
     *
     * @param routeId line id
     * @return
     * @throws IOException
     */
    public static RoutePatternsData getReverseRoutePatterns(String routeId) throws IOException {
        // direction 1 is reverse way
        URL routePatternUrl = new URL(Connector.mbta + routePatterns + routeId + filter + "1" + sort);
        Reader reader = new Connector().getGson(routePatternUrl);

        Type dataType = new TypeToken<RoutePatternsData>() {}.getType();
        return new Gson().fromJson(reader, dataType);
    }

}
