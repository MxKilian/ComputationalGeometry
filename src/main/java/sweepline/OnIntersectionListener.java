package sweepline;

import linesegment.LineSegment;

import java.awt.geom.Point2D;

public interface OnIntersectionListener {
    void onIntersection(LineSegment s1, LineSegment s2, Point2D.Double p);
}
