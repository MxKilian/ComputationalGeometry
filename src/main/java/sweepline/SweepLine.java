package sweepline;

import java.util.Comparator;
import java.util.TreeSet;

class SweepLine extends TreeSet<SweepSegment> {
    SweepLine() {
        super(Comparator.comparingDouble(SweepSegment::position));
    }

    void remove(SweepSegment s) {
        removeIf(sweepSegment -> sweepSegment.nearlyEqual(s));
    }

    void swap(SweepSegment s1, SweepSegment s2) {
        remove(s1);
        remove(s2);

        final double swap = s1.position();
        s1.setPosition(s2.position());
        s2.setPosition(swap);

        add(s1);
        add(s2);
    }


    SweepSegment above(SweepSegment s) {
        return higher(s);
    }


    SweepSegment below(SweepSegment s) {
        return lower(s);
    }

    void updatePositions(double x) {
        for (SweepSegment s : this) {
            s.updatePosition(x);
        }
    }
}
