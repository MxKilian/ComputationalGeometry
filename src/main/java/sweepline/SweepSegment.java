package sweepline;

import linesegment.LineSegment;

import java.awt.geom.Point2D;
import java.util.Locale;
import java.util.Objects;

final class SweepSegment {

    final private Event e1;
    final private Event e2;
    final private LineSegment mSegment;
    private double mPosition;

    SweepSegment(LineSegment s) {
        mSegment = s;

        Event e1 = new Event(s.getFirstPoint(), this, Event.Type.POINT_LEFT);
        Event e2 = new Event(s.getSecondPoint(), this, Event.Type.POINT_RIGHT);
        if (!(Objects.compare(e2, e1, Event::compareTo) == 1)) {
            final Event swap = e1;
            e1 = e2;
            e2 = swap;
            e1.setType(Event.Type.POINT_LEFT);
            e2.setType(Event.Type.POINT_RIGHT);
        }

        this.e1 = e1;
        this.e2 = e2;

        updatePosition(leftEvent().point().getX());
    }

    static Point2D.Double intersection(SweepSegment s1, SweepSegment s2) {
        final double x1 = s1.leftEvent().point().getX();
        final double y1 = s1.leftEvent().point().getY();
        final double x2 = s1.rightEvent().point().getX();
        final double y2 = s1.rightEvent().point().getY();

        final double x3 = s2.leftEvent().point().getX();
        final double y3 = s2.leftEvent().point().getY();
        final double x4 = s2.rightEvent().point().getX();
        final double y4 = s2.rightEvent().point().getY();

        final double v = (x4 - x3) * (y1 - y2) - (x1 - x2) * (y4 - y3);
        if (v == 0) {
            return null;
        }

        final double ta = ((y3 - y4) * (x1 - x3) + (x4 - x3) * (y1 - y3)) / v;
        final double tb = ((y1 - y2) * (x1 - x3) + (x2 - x1) * (y1 - y3)) / v;

        if ((ta >= 0.0f && ta <= 1.0f) && (tb >= 0.0f && tb <= 1.0f)) {
            final double px = x1 + ta * (x2 - x1);
            final double py = y1 + ta * (y2 - y1);

            return new Point2D.Double(px, py);
        }

        return null;
    }

    double position() {
        return mPosition;
    }

    void setPosition(double position) {
        mPosition = position;
    }

    Event leftEvent() {
        return e1;
    }

    Event rightEvent() {
        return e2;
    }

    LineSegment segment() {
        return mSegment;
    }

    boolean nearlyEqual(SweepSegment s) {
        return s.leftEvent().nearlyEqual(leftEvent()) && s.rightEvent().nearlyEqual(rightEvent());
    }

    void updatePosition(double x) {
        final double x1 = leftEvent().point().getX();
        final double y1 = leftEvent().point().getY();
        final double x2 = rightEvent().point().getX();
        final double y2 = rightEvent().point().getY();

        final double y = y1 + (((y2 - y1) * (x - x1)) / (x2 - x1));
        this.setPosition(y);
    }

    public String toString() {
        return String.format(Locale.getDefault(), "[%s : %s]", leftEvent(), rightEvent());
    }
}