package linesegment;

import java.awt.geom.Point2D;

public class LineSegment {

    private Point2D.Double firstPoint;
    private Point2D.Double secondPoint;

    public LineSegment(Point2D.Double firstPoint, Point2D.Double secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    public LineSegment(double x1, double y1, double x2, double y2) {
        this.firstPoint = new Point2D.Double(x1, y1);
        this.secondPoint = new Point2D.Double(x2, y2);
    }

    public Point2D.Double getFirstPoint() {
        return this.firstPoint;
    }

    public Point2D.Double getSecondPoint() {
        return this.secondPoint;
    }

}
