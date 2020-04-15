package polygon;

import linesegment.LineSegmentIntersection;
import org.w3c.dom.svg.SVGPoint;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Polygon {

    public static double calculatePolygonArea(ArrayList<SVGPoint> points) {

        double area = 0;

        if (points.size() == 0) {
            return area;
        }

        if (points.size() < 3) {
            return area;
        }

        int amountOfCornersInPolygon = points.size();
        for (int i = 0; i < points.size(); i++) {
            area += (points.get(i).getY() + (points.get((i + 1) % amountOfCornersInPolygon).getY())) * (points.get(i).getX() - (points.get((i + 1) % amountOfCornersInPolygon).getX()));
        }

        return Math.abs(area / 2.0);
    }

    public static boolean isPointInsidePolygon(ArrayList<SVGPoint> polygon, int n, Point2D.Double point) {

        if (polygon.size() < 3) {
            return false;
        }

        Point2D.Double extreme = new Point2D.Double(10000, point.y);

        int count = 0, i = 0;
        ArrayList<Point2D.Double> list = convertSVGPointListToPoint2D(polygon);
        do {
            int next = (i + 1) % n;

            if (LineSegmentIntersection.doIntersect(list.get(i), list.get(next), point, extreme)) {
                if (LineSegmentIntersection.orientation(list.get(i), extreme, list.get(next)) == 0) {
                    return LineSegmentIntersection.onSegment(list.get(i), extreme, list.get(next));
                }
                count++;
            }
            i = next;

        } while (i != 0);

        return (count % 2 == 1);
    }

    private static ArrayList<Point2D.Double> convertSVGPointListToPoint2D(ArrayList<SVGPoint> polygon) {
        ArrayList<Point2D.Double> pointList = new ArrayList<>();
        for (SVGPoint p : polygon) {
            pointList.add(new Point2D.Double(p.getX(), p.getY()));
        }
        return pointList;
    }

}
