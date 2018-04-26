package oopang.commons.events;

/**
 * Represents an event that cannot be triggered, thus implementing register() and unregister()
 * as empty methods.
 * @param <T>
 *      the type of the event.
 */
public final class NullEvent<T> implements Event<T> {

    @Override
    public void register(final EventHandler<T> handler) {
        // Registering a null event results in no operation.
    }

    @Override
    public void unregister(final EventHandler<T> handler) {
        // Unregistering a null event results in no operation.
    }
}
