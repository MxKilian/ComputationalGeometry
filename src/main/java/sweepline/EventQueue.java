package sweepline;

import java.util.PriorityQueue;

class EventQueue extends PriorityQueue<Event> {
    @Override
    public boolean contains(Object o) {
        boolean result = false;
        for (Event e : this) {
            if (e.nearlyEqual((Event) o)) {
                result = true;
                break;
            }
        }

        return result;
    }
}
