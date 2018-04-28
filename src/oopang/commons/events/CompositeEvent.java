package oopang.commons.events;

import java.util.HashSet;
import java.util.Set;
/**
 * This class is a composition of {@link Event}s.
 * @param <T>
 *      the type of the events.
 */
public final class CompositeEvent<T> implements Event<T> {

    private final Set<Event<T>> events;

    /**
     * Create a new compositeEvent.
     */
    public CompositeEvent() {
        events = new HashSet<>();
    }
    /**
     * Add a new {@link Event} to the composition.
     * @param event
     *      the event to be added to the composition.
     */
    public void addEvent(final Event<T> event) {
        this.events.add(event);
    }

    @Override
    public void register(final EventHandler<T> handler) {
        events.forEach(e -> e.register(handler));
    }

    @Override
    public void unregister(final EventHandler<T> handler) {
        events.forEach(e -> e.unregister(handler));
    }
}
