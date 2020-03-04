package com.company.service;

import com.company.controller.*;
import com.company.model.SubwayLine;
import com.company.model.SubwayStop;
import com.company.model.routepatterns.RoutePatternsData;
import com.company.model.routes.Routes;
import com.company.model.routes.RoutesData;
import com.company.model.stop.Stop;
import com.company.model.stop.StopData;
import com.company.model.stops.Stops;
import com.company.model.trips.TripsData;

import java.io.IOException;
import java.util.*;

import static org.testng.Assert.*;

public class SubwayStopService {
    public static Set<SubwayStop> getSubwayStopsByStopName(HashSet<SubwayStop> subwayStops, String stopName) {
        Set<SubwayStop> result = new HashSet<>();
        for (SubwayStop stop : subwayStops)
            if (stop.getStopName().equals(stopName))
                result.add(stop);

        return result;
    }

    public static HashSet<SubwayStop> findAllConnectingStops(HashSet<SubwayStop> subwayStops) {
        HashSet<SubwayStop> result = new HashSet<>();

        Set<String> repeats = new HashSet<>();
        Set<String> uniques = new HashSet<>();

        for (SubwayStop t : subwayStops) {
            if (!uniques.add(t.getStopName())) {
                repeats.add(t.getStopName());
            }
        }

        for (String duplicateStop : repeats)
            result.addAll(getSubwayStopsByStopName(subwayStops, duplicateStop));

        return result;
    }

    public static void printAllConnectingStops(LinkedHashSet<SubwayStop> mySubwayStops) {
        HashSet<SubwayStop> connectingStops = findAllConnectingStops(mySubwayStops);
        System.out.println("List of all stations that belong to more than one subway line");
        for (SubwayStop subwayStop : connectingStops) {
            System.out.println(subwayStop.toString());
        }
    }

    public static void printLeastAndMostSubwayStations(ArrayList<SubwayLine> mySubwayLines) {
        ArrayList<SubwayLine> sortedSubwayLines = new SubwayLineSorter(mySubwayLines).sortSubwayLinesByNumberOfStops();
        // implied there's just one line with least and one line with most number of stops
        System.out.println(sortedSubwayLines.get(0).getRouteId() + " has the least number of stops: " +
                sortedSubwayLines.get(0).getNumberOfStops());
        System.out.println(
                sortedSubwayLines.get(sortedSubwayLines.size() - 1).getRouteId() + " has the most number of stops: " +
                        sortedSubwayLines.get(sortedSubwayLines.size() - 1).getNumberOfStops());
        System.out.println();
    }

    public static void createSubwayLineStops(ArrayList<SubwayLine> mySubwayLines,
                                             LinkedHashSet<SubwayStop> mySubwayStops) throws IOException {
        for (SubwayLine subwayLine : mySubwayLines) {
            TripsData trips = TripsController.getTrips(subwayLine.getTripId());
            subwayLine.setNumberOfStops(trips.getData().getTripRelationships().getStopsData().getStops().size());

            List<String> stopIds = new ArrayList<>();
            for (Stops stopData : trips.getData().getTripRelationships().getStopsData().getStops()) {
                stopIds.add(stopData.getId());
            }
            assertNotNull(stopIds, "List of stops is null for line " + subwayLine.getRouteId());
            StopData stops = StopsController.getStops(stopIds);

            assertEquals(stopIds.size(), stops.getStops().size(),
                    "endpoint /stops returned incorrect amount of stops for line" + subwayLine.getRouteId());
            for (Stop stop : stops.getStops())
                mySubwayStops.add(new SubwayStop(stop.getId(), stop.getAttributes().getName(),
                        stop.getRelationships().getParent_station().getData().getId(), subwayLine.getRouteId()));
        }
    }

    public static void setSubwayLinesTripIds(ArrayList<SubwayLine> mySubwayLines) throws IOException {
        for (SubwayLine subwayLine : mySubwayLines) {
            RoutePatternsData routePatterns = RoutePatternsController.getOneWayRoutePatterns(subwayLine.getRouteId());
            assertTrue(routePatterns.getData().size() > 0,
                    "Something went wrong " + subwayLine.getRouteId() + " didn't return any route patterns");

            subwayLine.setTripId(RoutePatternsHelper.getMostTypicalRoutePatternTripId(subwayLine, routePatterns));
        }
    }

    public static void printAllSubwayStations(ArrayList<SubwayLine> mySubwayLines) throws IOException {
        RoutesData routesData = RoutesController.getSubwaysData();
        List<Routes> routes = routesData.getData();
        System.out.println("List of names of all subway stations: ");
        for (int i = 0; i < routes.size(); i++) {
            Routes route = routes.get(i);
            System.out.println(route.getAttributes().getLong_name());

            mySubwayLines.add(new SubwayLine());
            mySubwayLines.get(i).setRouteId(route.getId());
        }
        System.out.println();
    }
}
