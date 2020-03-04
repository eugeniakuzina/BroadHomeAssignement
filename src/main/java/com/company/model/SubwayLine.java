package com.company.model;

public class SubwayLine implements Comparable<SubwayLine> {
    private String routeId;
    private String tripId;
    // can be used later while extending the implementation to consider reverse stops
    private String reverseTripId;
    private int numberOfStops;

    public SubwayLine() {
        this.routeId = null;
        this.tripId = null;
        this.reverseTripId = null;
        this.numberOfStops = 0;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getReverseTripId() {
        return reverseTripId;
    }

    public void setReverseTripId(String reverseTripId) {
        this.reverseTripId = reverseTripId;
    }

    public int getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(int numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    public int compareTo(SubwayLine subwayLine) {
        return Integer.compare(this.getNumberOfStops(), subwayLine.getNumberOfStops());
    }

    @Override
    public String toString() {
        return "SubwayLine{" + "routeId='" + routeId + '\'' + ", tripId='" + tripId + '\'' + ", reverseTripId='" +
                reverseTripId + '\'' + ", numberOfStops=" + numberOfStops + '}';
    }
}
