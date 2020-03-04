package com.company.controller;

import com.company.model.SubwayLine;

import java.util.ArrayList;
import java.util.Collections;

public class SubwayLineSorter {
    private ArrayList<SubwayLine> subwayLines;

    public SubwayLineSorter(ArrayList<SubwayLine> subwayLines) {
        this.subwayLines = subwayLines;
    }

    public ArrayList<SubwayLine> sortSubwayLinesByNumberOfStops() {
        Collections.sort(subwayLines);
        return subwayLines;
    }

}
