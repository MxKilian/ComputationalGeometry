package sweepline;

import linesegment.LineSegment;

import java.awt.geom.Point2D;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final public class BentleyOttmann {

    final private EventQueue mEventQueue = new EventQueue();
    final private SweepLine mSweepLine = new SweepLine();
    final private List<Point2D.Double> mIntersections = new ArrayList<>();

    private OnIntersectionListener mListener;

    public void addSegments(List<LineSegment> segments) {
        for (LineSegment s : segments) {
            final SweepSegment ss = new SweepSegment(s);
            mEventQueue.add(ss.leftEvent());
            mEventQueue.add(ss.rightEvent());
        }
    }

    public long findIntersections() {
        Instant start = Instant.now();
        while (!mEventQueue.isEmpty()) {
            final Event E = mEventQueue.poll();
            if (E.type() == Event.Type.POINT_LEFT) {
                final SweepSegment segE = E.firstSegment();

                mSweepLine.updatePositions(E.point().getX());
                mSweepLine.add(segE);

                final SweepSegment segA = mSweepLine.above(segE);
                final SweepSegment segB = mSweepLine.below(segE);

                addEventIfIntersection(segE, segA, E, false);
                addEventIfIntersection(segE, segB, E, false);
            } else if (E.type() == Event.Type.POINT_RIGHT) {
                final SweepSegment segE = E.firstSegment();
                final SweepSegment segA = mSweepLine.above(segE);
                final SweepSegment segB = mSweepLine.below(segE);

                mSweepLine.remove(segE);
                addEventIfIntersection(segA, segB, E, true);
            } else {
                mIntersections.add(E.point());

                final SweepSegment segE1 = E.firstSegment();
                final SweepSegment segE2 = E.secondSegment();

                if (mListener != null) {
                    mListener.onIntersection(segE1.segment(), segE2.segment(), E.point());
                }

                mSweepLine.swap(segE1, segE2);

                final SweepSegment segA = mSweepLine.above(segE2);
                final SweepSegment segB = mSweepLine.below(segE1);

                addEventIfIntersection(segE2, segA, E, true);
                addEventIfIntersection(segE1, segB, E, true);
            }
        }
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis();
    }

    public List<Point2D.Double> intersections() {
        return Collections.unmodifiableList(mIntersections);
    }

    public void setListener(OnIntersectionListener listener) {
        mListener = listener;
    }

    public void reset() {
        mIntersections.clear();
        mEventQueue.clear();
        mSweepLine.clear();
    }

    private void addEventIfIntersection(SweepSegment s1, SweepSegment s2,
                                        Event E, boolean check) {
        if (s1 != null && s2 != null) {
            final Point2D.Double i = SweepSegment.intersection(s1, s2);
            if (i != null && i.getX() > E.point().getX()) {
                final Event e = new Event(i, s1, s2);
                if (check) {
                    if (mEventQueue.contains(e)) {
                        return;
                    }
                }

                mEventQueue.add(e);
            }
        }
    }
}
