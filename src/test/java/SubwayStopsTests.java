import com.company.model.SubwayLine;
import com.company.model.SubwayStop;
import com.company.service.SubwayStopService;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

import static org.testng.Assert.*;

public class SubwayStopsTests {

    @Test
    public void testSubwayStops() throws IOException {
        ArrayList<SubwayLine> mySubwayLines = TestsDataGenerator.retrieveSubwayLines();

        LinkedHashSet<SubwayStop> mySubwayStops = new LinkedHashSet<>();

        TestsDataGenerator.retrieveSubwayStops(mySubwayLines, mySubwayStops);

        HashSet<SubwayStop> connectingStops = SubwayStopService.findAllConnectingStops(mySubwayStops);

        for (SubwayStop stop : connectingStops) {
            Set<SubwayStop> stops = SubwayStopService.getSubwayStopsByStopName(connectingStops, stop.getStopName());
            assertTrue(stops.size() > 1, stop.getStopName() + " appears only once in the list of connecting stops ");
        }

        assertTrue(connectingStops.size() > 0, "List of connecting stops is empty ");
    }

}
