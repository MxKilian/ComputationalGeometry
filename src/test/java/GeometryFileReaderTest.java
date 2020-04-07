import org.junit.Test;

import java.awt.geom.Line2D;
import java.util.List;

public class GeometryFileReaderTest {

    @Test
    public void fileReaderShouldReturnNonEmptyList() {
        List<Double> listOfPoints = FileReader.readFile("s_1000_1.dat");
        assert(listOfPoints.size() > 0);
    }

    @Test
    public void convertToLineListShouldReturnNonEmptyList() {
        List<Double> listOfPoints = FileReader.readFile("s_1000_1.dat");
        List<LineSegment> lineSegmentList = FileReader.convertToLineList(listOfPoints);
        assert(lineSegmentList.size() > 0);
    }

}
