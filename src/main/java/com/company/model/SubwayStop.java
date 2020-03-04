package com.company.model;

import java.util.*;

public class SubwayStop {
    private String stopId;
    private String stopName;
    private String parentName;
    private String routeName;

    public SubwayStop(String stopId, String stopName, String parentName, String routeName) {
        this.stopId = stopId;
        this.stopName = stopName;
        this.parentName = parentName;
        this.routeName = routeName;
    }

    public String getStopId() {
        return stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public String getParentName() {
        return parentName;
    }

    public String getRouteName() {
        return routeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SubwayStop that = (SubwayStop) o;
        return stopId.equals(that.stopId) && stopName.equals(that.stopName) &&
                Objects.equals(parentName, that.parentName) && routeName.equals(that.routeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopId, routeName);
    }

    @Override
    public String toString() {
        return "SubwayStop{" + "stopId='" + stopId + '\'' + ", stopName='" + stopName + '\'' + ", parentName='" +
                parentName + '\'' + ", routeName='" + routeName + '\'' + '}';
    }
}
