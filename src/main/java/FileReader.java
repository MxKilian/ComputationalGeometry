import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    static List<Double> readFile(String filePath) {
        File text = new File(filePath);
        Scanner sc = null;
        try {
            sc = new Scanner(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Double> doubleList = new ArrayList<>();
        while (sc.hasNext()) { // this loop breaks there is no more int input.
            doubleList.add(Double.valueOf(sc.next()));
        }

        return doubleList;
    }

    static List<LineSegment> convertToLineList(List<Double> doubleList) {
        List<LineSegment> lineSegmentList = new ArrayList<>();
        for(int i = 0; i < doubleList.size(); i += 4) {
            double x1 = doubleList.get(i);
            double y1 = doubleList.get(i + 1);
            double x2 = doubleList.get(i + 2);
            double y2 = doubleList.get(i + 3);
            lineSegmentList.add(new LineSegment(x1, y1, x2, y2));
        }

        return lineSegmentList;
    }

}
