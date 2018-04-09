package oopang.commons;

import oopang.commons.events.Event;

/**
 * This interface describes objects which have a limited time.
 */
public interface Timeable {

    /**
     * Returns a double between 0 and 1 that represents the percentage of remaining time.
     * @return
     *      the percentage of remaining time.
     */
    double getRemainingTimePercentage();

    /**
     * Returns a void event that triggers when time is finished.
     * @return
     *      the timeout event
     */
    Event<Void> getTimeOutEvent();
}
