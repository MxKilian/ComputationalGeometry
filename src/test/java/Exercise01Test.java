import linesegment.LineSegment;
import linesegment.LineSegmentIntersection;
import org.junit.Test;
import reader.FileReader;

import java.util.List;

public class Exercise01Test {

    @Test
    public void checkIntersection() {
        // Read in the example files of exercise 01
        List<Double> listOfPoints1 = FileReader.readFile("s_1000_1.dat");
        List<Double> listOfPoints2 = FileReader.readFile("s_10000_1.dat");
        List<Double> listOfPoints3 = FileReader.readFile("s_100000_1.dat");

        // Convert to line segment structure
        List<LineSegment> lineSegmentList1 = FileReader.convertToLineSegmentList(listOfPoints1);
        List<LineSegment> lineSegmentList2 = FileReader.convertToLineSegmentList(listOfPoints2);
        List<LineSegment> lineSegmentList3 = FileReader.convertToLineSegmentList(listOfPoints3);

        // Calculate intersections
        long runtime = LineSegmentIntersection.doIntersectionCalculationForListOfSegments(lineSegmentList1);
        System.out.println("Laufzeit (in ms): " + runtime + ", Schnittpunkte: " + LineSegmentIntersection.interSectionCounter);
        runtime = LineSegmentIntersection.doIntersectionCalculationForListOfSegments(lineSegmentList2);
        System.out.println("Laufzeit (in ms): " + runtime + ", Schnittpunkte: " + LineSegmentIntersection.interSectionCounter);
        runtime = LineSegmentIntersection.doIntersectionCalculationForListOfSegments(lineSegmentList3);
        System.out.println("Laufzeit (in ms): " + runtime + ", Schnittpunkte: " + LineSegmentIntersection.interSectionCounter);
    }

}
