import com.company.controller.*;
import com.company.model.SubwayLine;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SubwayLinesTests {

    //Magic number 8 is taken from the MBTA documentation
    @Test
    public void testNumberOfSubwayLinesTest() throws IOException {
        assertEquals(RoutesController.getSubwaysData().getData().size(), 8, "Incorrect number of subway lines");
    }

    @Test
    public void testNumberOfStopsInLine() throws IOException {
        ArrayList<SubwayLine> mySubwayLines = TestsDataGenerator.retrieveSubwayLines();

        ArrayList<SubwayLine> sortedSubwayLines = new SubwayLineSorter(mySubwayLines).sortSubwayLinesByNumberOfStops();

        assertTrue(sortedSubwayLines.get(0).getNumberOfStops() > 0,
                "Number of stops of " + sortedSubwayLines.get(0).getRouteId() + " should be > 0");

        assertTrue(sortedSubwayLines.get(sortedSubwayLines.size() - 1).getNumberOfStops() > 0,
                "Number of stops of " + sortedSubwayLines.get(sortedSubwayLines.size() - 1).getRouteId() +
                        " should be > 0");

        assertTrue(sortedSubwayLines.get(0).getNumberOfStops() <
                        sortedSubwayLines.get(sortedSubwayLines.size() - 1).getNumberOfStops(),
                "Least number of stops should be less than most number of stops");
    }

}
