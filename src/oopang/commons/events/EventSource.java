package oopang.commons.events;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an event which accepts a {@link EventHandler} as an observer.
 * @param <T>
 *      The type of the argument sent to all {@link EventHandler} objects registered
 *      to this event.
 */
public final class EventSource<T> implements Event<T> {

    private final List<EventHandler<T>> registeredHandlers;

    /**
     * Creates a new Event object with no handlers.
     */
    public EventSource() {
        this.registeredHandlers = new ArrayList<>();
    }

    @Override
    public void register(final EventHandler<T> handler) {
        this.registeredHandlers.add(handler);
    }

    @Override
    public void unregister(final EventHandler<T> handler) {
        this.registeredHandlers.remove(handler);
    }

    /**
     * Triggers the event, causing all registered handlers to be notified.
     * @param arg
     *      the argument of the event.
     */
    public void trigger(final T arg) {
        this.registeredHandlers.stream().forEach(h -> h.handle(arg));
    }
}
