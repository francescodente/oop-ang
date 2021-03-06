package oopang.commons.events;

/**
 * Represents an object that is responsible for handling {@link Event}s.
 * @param <T>
 */
@FunctionalInterface
public interface EventHandler<T> {
    /**
     * Handles the event.
     * @param arg
     *      the parameter of the event.
     */
    void handle(T arg);
}
