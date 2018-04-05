package oopang.commons;

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
}
