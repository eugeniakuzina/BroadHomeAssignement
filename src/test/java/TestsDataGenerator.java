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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class TestsDataGenerator {
    static ArrayList<SubwayLine> retrieveSubwayLines() throws IOException {
        ArrayList<SubwayLine> mySubwayLines = new ArrayList<>();

        RoutesData subwaysData = RoutesController.getSubwaysData();
        for (Routes route : subwaysData.getData()) {
            mySubwayLines.add(new SubwayLine());
            mySubwayLines.get(mySubwayLines.size() - 1).setRouteId(route.getId());
        }

        for (SubwayLine subwayLine : mySubwayLines) {
            RoutePatternsData routePatterns = RoutePatternsController.getOneWayRoutePatterns(subwayLine.getRouteId());
            String mostTypicalRoutePatternTripId = RoutePatternsHelper
                    .getMostTypicalRoutePatternTripId(subwayLine, routePatterns);

            subwayLine.setTripId(mostTypicalRoutePatternTripId);

            TripsData trips = TripsController.getTrips(subwayLine.getTripId());
            subwayLine.setNumberOfStops(trips.getData().getTripRelationships().getStopsData().getStops().size());
        }
        return mySubwayLines;
    }

    static void retrieveSubwayStops(ArrayList<SubwayLine> mySubwayLines,
                                    LinkedHashSet<SubwayStop> mySubwayStops) throws IOException {
        for (SubwayLine subwayLine : mySubwayLines) {
            TripsData trips = TripsController.getTrips(subwayLine.getTripId());
            subwayLine.setNumberOfStops(trips.getData().getTripRelationships().getStopsData().getStops().size());

            List<String> stopIds = new ArrayList<>();
            for (Stops stopData : trips.getData().getTripRelationships().getStopsData().getStops()) {
                stopIds.add(stopData.getId());
            }
            StopData stops = StopsController.getStops(stopIds);

            for (Stop stop : stops.getStops())
                mySubwayStops.add(new SubwayStop(stop.getId(), stop.getAttributes().getName(),
                        stop.getRelationships().getParent_station().getData().getId(), subwayLine.getRouteId()));
        }
    }

}
