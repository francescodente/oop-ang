package oopang.commons.events;
/**
 * This interface is used to give a protected access from outside the class that holds the {@link EventSource}.
 * @param <T>
 *      the type of the Event
 */
public interface Event<T> {

    /**
     * Register the given {@link EventHandler} to this Event.
     * @param handler
     *      the handler to be registered.
     */
    void register(EventHandler<T> handler);

    /**
     * Unregister the given {@link EventHandler} to this Event.
     * @param handler
     *      the handler to be unregistered.
     */
    void unregister(EventHandler<T> handler);
}
