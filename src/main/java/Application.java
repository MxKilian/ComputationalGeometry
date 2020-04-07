import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        List<Double> listOfPoints1 = FileReader.readFile("s_1000_1.dat");
        List<Double> listOfPoints2 = FileReader.readFile("s_10000_1.dat");
        List<Double> listOfPoints3 = FileReader.readFile("s_100000_1.dat");

        List<LineSegment> lineSegmentList1 = FileReader.convertToLineList(listOfPoints1);
        List<LineSegment> lineSegmentList2 = FileReader.convertToLineList(listOfPoints2);
        List<LineSegment> lineSegmentList3 = FileReader.convertToLineList(listOfPoints3);

        doIntersectionCalculation(lineSegmentList1);
        doIntersectionCalculation(lineSegmentList2);
        doIntersectionCalculation(lineSegmentList3);
    }

    /**
     * Test all line segements against each other
     * Please note: we don't need to remove the line segment we are testing against from the list as they won't
     * intersect and therefore don't deliver a faulty result
     *
     * @param lineSegmentList list of line segements consisting of two points
     * @return
     */
    static void doIntersectionCalculation(List<LineSegment> lineSegmentList) {

        Instant start = Instant.now();
        int overallIntersectionCounter = 0;

        for(int i = 0; i < lineSegmentList.size(); i += 1) {
            LineSegment lineSegmentToTestAgainst = lineSegmentList.get(i);
            for (int j = 1; j < lineSegmentList.size(); j += 1) {
                LineSegment lineSegment = lineSegmentList.get(j);
                boolean doIntersect = LineSegmentIntersection.doIntersect(
                        lineSegmentToTestAgainst.getFirstPoint(),
                        lineSegmentToTestAgainst.getSecondPoint(),
                        lineSegment.getFirstPoint(),
                        lineSegment.getSecondPoint()
                );

                if(doIntersect) { overallIntersectionCounter++; }
            }
        }

        Instant end = Instant.now();
        System.out.println("Overall intersections: " + overallIntersectionCounter);
        System.out.println("Time needed for execution (in ms): " + Duration.between(start, end).toMillis());
        System.out.println("-------------------------------");
    }

}
