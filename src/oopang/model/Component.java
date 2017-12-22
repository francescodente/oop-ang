package oopang.model;

/**
 * A component to be attached to a GameObject to define its behavior.
 */
public interface Component {

    /**
     * Initialize Component activity.
     */
    void start();

    /**
     * Update Component status.
     * 
     * @param deltaTime
     *       time elapsed since last update.
     */
    void update(double deltaTime);

    /**
     * Enable the Component activity.
     */
    void enable();

    /**
     * Disable the Component activity.
     */
    void disable();

}
