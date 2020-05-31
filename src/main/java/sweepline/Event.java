package sweepline;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

final class Event implements Comparable<Event> {
    final private static double EPSILON = 1E-9;
    final private Point2D.Double mPoint;
    final private List<SweepSegment> mSegments = new ArrayList<>();
    private Type mType;

    Event(Point2D.Double p, SweepSegment s1, Type type) {
        mPoint = p;
        mType = type;
        mSegments.add(s1);
    }

    Event(Point2D.Double p, SweepSegment s1, SweepSegment s2) {
        this(p, s1, Type.INTERSECTION);
        mSegments.add(s2);

        // Ensure s1 is always above s2
        if (!(mSegments.get(0).position() > mSegments.get(1).position())) {
            Collections.swap(mSegments, 0, 1);
        }
    }

    private static boolean nearlyEqual(double a, double b) {
        final double absA = Math.abs(a);
        final double absB = Math.abs(b);
        final double diff = Math.abs(a - b);

        if (a == b) {
            return true;
        } else if (a == 0 || b == 0 || (absA + absB < Double.MIN_NORMAL)) {
            return diff < (Event.EPSILON * Double.MIN_NORMAL);
        } else {
            return diff / Math.min((absA + absB), Double.MAX_VALUE) < Event.EPSILON;
        }
    }

    void setType(Type type) {
        mType = type;
    }


    Type type() {
        return mType;
    }


    Point2D.Double point() {
        return mPoint;
    }


    SweepSegment firstSegment() {
        return mSegments.get(0);
    }


    SweepSegment secondSegment() {
        return mSegments.get(1);
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "[%s, %s]", point().getX(), point().getY());
    }

    @Override
    public int compareTo(Event e) {
        if (e.point().getX() < point().getX() ||
                (nearlyEqual(e.point().getX(), point().getX()) && e.point().getY() < point().getY())) {
            return 1;
        }

        if (e.point().getX() > point().getX() ||
                (nearlyEqual(e.point().getX(), point().getX()) && e.point().getY() > point().getY())) {
            return -1;
        }

        return 0;
    }

    boolean nearlyEqual(Event e) {
        return nearlyEqual(point().getX(), e.point().getX()) && nearlyEqual(point().getY(), e.point().getY());
    }

    enum Type {
        POINT_LEFT, POINT_RIGHT, INTERSECTION
    }
}
