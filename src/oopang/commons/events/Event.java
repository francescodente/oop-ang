package oopang.commons.events;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an event which accepts a {@link EventHandler} as an observer.
 * @param <T>
 *      The type of the argument sent to all {@link EventHandler} objects registered
 *      to this event.
 */
public class Event<T> {

    private final List<EventHandler<T>> registeredHandlers;

    /**
     * Creates a new Event object with no handlers.
     */
    public Event() {
        this.registeredHandlers = new ArrayList<>();
    }

    /**
     * Registers a new {@link EventHandler} to this event.
     * @param handler
     *      the handler for the event.
     */
    public void registerHandler(final EventHandler<T> handler) {
        this.registeredHandlers.add(handler);
    }

    /**
     * Removes the specified {@link EventHandler} from the list of handlers.
     * @param handler
     *      the handler to remove.
     */
    public void unregisterHandler(final EventHandler<T> handler) {
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
