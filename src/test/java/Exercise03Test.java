import linesegment.LineSegment;
import linesegment.LineSegmentIntersection;
import org.junit.Before;
import org.junit.Test;
import reader.FileReader;
import sweepline.BentleyOttmann;

import java.util.List;

public class Exercise03Test {

    private static List<LineSegment> lineSegmentList1;
    private static List<LineSegment> lineSegmentList2;
    private static List<LineSegment> lineSegmentList3;

    @Before
    public void setup() {
        List<Double> listOfPoints1 = FileReader.readFile("s_1000_1.dat");
        List<Double> listOfPoints2 = FileReader.readFile("s_10000_1.dat");
        List<Double> listOfPoints3 = FileReader.readFile("s_100000_1.dat");

        lineSegmentList1 = FileReader.convertToLineSegmentList(listOfPoints1);
        lineSegmentList2 = FileReader.convertToLineSegmentList(listOfPoints2);
        lineSegmentList3 = FileReader.convertToLineSegmentList(listOfPoints3);
    }

    @Test
    public void compareRuntimeForFile1000() {
        // Brute Force
        long naiveAlgorithmRunTime1000 = LineSegmentIntersection.doIntersectionCalculationForListOfSegments(lineSegmentList1);
        long naiveAlgorithmIntersections1000 = LineSegmentIntersection.interSectionCounter;

        // SweepLine
        final BentleyOttmann bentleyOttmann = new BentleyOttmann();
        bentleyOttmann.addSegments(lineSegmentList1);
        long sweepLineRunTime1000 = bentleyOttmann.findIntersections();
        long sweepLine1000Intersections = bentleyOttmann.intersections().size();

        assert (naiveAlgorithmRunTime1000 > sweepLineRunTime1000);
        System.out.println("Laufzeit naiver Algorithmus für s_1000 (in ms): " + naiveAlgorithmRunTime1000);
        System.out.println("Gefunde Schnittpunkte für s_1000: " + naiveAlgorithmIntersections1000);
        System.out.println("--------------------------------------------------------");
        System.out.println("Laufzeit Sweepline für s_1000 (in ms): " + sweepLineRunTime1000);
        System.out.println("Gefunde Schnittpunkte für s_1000: " + sweepLine1000Intersections);
    }

    @Test
    public void compareRuntimeForFile10000() {
        // Brute Force
        long naiveAlgorithmRunTime10000 = LineSegmentIntersection.doIntersectionCalculationForListOfSegments(lineSegmentList2);
        long naiveAlgorithmIntersections10000 = LineSegmentIntersection.interSectionCounter;

        // SweepLine
        final BentleyOttmann bentleyOttmann = new BentleyOttmann();
        bentleyOttmann.addSegments(lineSegmentList2);
        long sweepLineRunTime10000 = bentleyOttmann.findIntersections();
        long sweepLine10000Intersections = bentleyOttmann.intersections().size();

        assert (naiveAlgorithmRunTime10000 > sweepLineRunTime10000);
        System.out.println("Laufzeit naiver Algorithmus für s_1000 (in ms): " + naiveAlgorithmRunTime10000);
        System.out.println("Gefunde Schnittpunkte für s_10000: " + naiveAlgorithmIntersections10000);
        System.out.println("--------------------------------------------------------");
        System.out.println("Laufzeit Sweepline für s_1000 (in ms): " + sweepLineRunTime10000);
        System.out.println("Gefunde Schnittpunkte für s_10000: " + sweepLine10000Intersections);
    }

    @Test
    public void compareRuntimeForFile100000() {
        // Brute Force
        long naiveAlgorithmRunTime100000 = LineSegmentIntersection.doIntersectionCalculationForListOfSegments(lineSegmentList3);
        long naiveAlgorithmIntersections100000 = LineSegmentIntersection.interSectionCounter;

        // SweepLine
        final BentleyOttmann bentleyOttmann = new BentleyOttmann();
        bentleyOttmann.addSegments(lineSegmentList3);
        long sweepLineRunTime100000 = bentleyOttmann.findIntersections();
        long sweepLine100000Intersections = bentleyOttmann.intersections().size();

        // assert(naiveAlgorithmRunTime100000 > sweepLineRunTime100000);
        System.out.println("Laufzeit naiver Algorithmus für s_100000 (in ms): " + naiveAlgorithmRunTime100000);
        System.out.println("Gefunde Schnittpunkte für s_100000: " + naiveAlgorithmIntersections100000);
        System.out.println("--------------------------------------------------------");
        System.out.println("Laufzeit Sweepline für s_100000 (in ms): " + sweepLineRunTime100000);
        System.out.println("Gefunde Schnittpunkte für s_100000: " + sweepLine100000Intersections);
    }

}