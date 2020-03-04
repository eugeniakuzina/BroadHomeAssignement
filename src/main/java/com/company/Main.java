package com.company;

import com.company.model.SubwayLine;
import com.company.model.SubwayStop;
import com.company.service.SubwayStopService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<SubwayLine> mySubwayLines = new ArrayList<>();
        LinkedHashSet<SubwayStop> mySubwayStops = new LinkedHashSet<>();

        // Task 1
        SubwayStopService.printAllSubwayStations(mySubwayLines);

        // Tasks 2.1 and 2.2.
        SubwayStopService.setSubwayLinesTripIds(mySubwayLines);
        SubwayStopService.createSubwayLineStops(mySubwayLines, mySubwayStops);

        SubwayStopService.printLeastAndMostSubwayStations(mySubwayLines);

        // Task 2.3
        SubwayStopService.printAllConnectingStops(mySubwayStops);

    }

}
