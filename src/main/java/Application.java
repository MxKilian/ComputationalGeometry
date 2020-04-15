import linesegment.LineSegment;
import linesegment.LineSegmentIntersection;
import reader.FileReader;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<Double> listOfPoints1 = FileReader.readFile("s_1000_1.dat");
        List<Double> listOfPoints2 = FileReader.readFile("s_10000_1.dat");
        List<Double> listOfPoints3 = FileReader.readFile("s_100000_1.dat");

        List<LineSegment> lineSegmentList1 = FileReader.convertToLineList(listOfPoints1);
        List<LineSegment> lineSegmentList2 = FileReader.convertToLineList(listOfPoints2);
        List<LineSegment> lineSegmentList3 = FileReader.convertToLineList(listOfPoints3);

        LineSegmentIntersection.doIntersectionCalculation(lineSegmentList1);
        LineSegmentIntersection.doIntersectionCalculation(lineSegmentList2);
        LineSegmentIntersection.doIntersectionCalculation(lineSegmentList3);
    }

}
