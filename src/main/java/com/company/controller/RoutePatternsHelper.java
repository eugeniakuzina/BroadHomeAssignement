package com.company.controller;

import com.company.model.SubwayLine;
import com.company.model.routepatterns.RoutePatterns;
import com.company.model.routepatterns.RoutePatternsData;

import static org.testng.Assert.fail;

public class RoutePatternsHelper {
    public static String getMostTypicalRoutePatternTripId(SubwayLine subwayLine, RoutePatternsData routePatterns) {
        for (RoutePatterns routePattern : routePatterns.getData()) {
            if (routePattern.getAttributes().getTypicality() == 1) {
                return routePattern.getRelationships().getRepresentativeTripData().getData().getId();
            }
        }
        fail("Something went wrong " + subwayLine.getRouteId() + " line doesn't have trip with typicality 1");
        return "";
    }
}
