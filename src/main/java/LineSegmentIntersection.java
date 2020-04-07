import java.awt.geom.Point2D;

public class LineSegmentIntersection {

    /**
     * Check if of three given colinear points p (firstPoint), q (secondPoint) and r (thirdPoint)
     * point q is placed on line segment qr
     *
     * @param firstPoint
     * @param secondPoint
     * @param thirdPoint
     * @return
     */
    static boolean onSegment(Point2D.Double firstPoint, Point2D.Double secondPoint, Point2D.Double thirdPoint) {

        boolean rtrnValue = false;
        if (secondPoint.x <= Math.max(firstPoint.x, thirdPoint.x)
                && secondPoint.x >= Math.min(firstPoint.x, thirdPoint.x)
                && secondPoint.y <= Math.max(firstPoint.y, thirdPoint.y)
                && secondPoint.y >= Math.min(firstPoint.y, thirdPoint.y)) {
            rtrnValue = true;
        }

        return rtrnValue;
    }

    /**
     * Calculation of the slope of 3 given points
     * (y2 - y1) * (x3 - x2) * (y3 - y2) * (x2 - x1)
     *
     * @param firstPoint
     * @param secondPoint
     * @param thirdPoint
     * @return 0 = collinear, 1 = clockwise, 2 = counter clockwise
     */
    static double direction(Point2D.Double firstPoint, Point2D.Double secondPoint, Point2D.Double thirdPoint) {
        double val = (secondPoint.y - firstPoint.y) * (thirdPoint.x - secondPoint.x)
                - (secondPoint.x - firstPoint.x) * (thirdPoint.y - secondPoint.y);

        if (val == 0) return 0;

        return (val > 0) ? 1 : 2;
    }

    static boolean doIntersect(Point2D.Double firstPoint, Point2D.Double secondPoint, Point2D.Double thirdPoint, Point2D.Double fourthPoint)
    {
        // Find the four orientations needed for general and
        // special cases
        double o1 = direction(firstPoint, secondPoint, thirdPoint);
        double o2 = direction(firstPoint, secondPoint, fourthPoint);
        double o3 = direction(thirdPoint, fourthPoint, firstPoint);
        double o4 = direction(thirdPoint, fourthPoint, secondPoint);

        // General case
        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        // Second point of line segment 2 sits on line 1 results in an intersection
        if (o1 == 0 && onSegment(firstPoint, thirdPoint, secondPoint)) return true;

        if (o2 == 0 && onSegment(firstPoint, fourthPoint, secondPoint)) return true;

        if (o3 == 0 && onSegment(thirdPoint, firstPoint, fourthPoint)) return true;

        if (o4 == 0 && onSegment(thirdPoint, secondPoint, fourthPoint)) return true;

        return false;
    }

}
