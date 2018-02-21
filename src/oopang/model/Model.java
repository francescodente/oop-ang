package oopang.model;

/**
 * Model of the application.
 */
public interface Model {
    /**
     * Updates the model's state.
     * @param deltaTime
     *      time elapsed since last update
     */
    void update(double deltaTime);
}
