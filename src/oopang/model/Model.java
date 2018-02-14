package oopang.model;


import oopang.model.levels.Level;

/**
 * Model of the application.
 */
public interface Model {

    /**
     * Returns the current level being played.
     * @return
     *      the current level.
     */
    Level getCurrentLevel();

    /**
     * Sets the current level to the given one.
     * @param level
     *      the new level
     */
    void setCurrentLevel(Level level);

    /**
     * Updates the model's state.
     * @param deltaTime
     *      time elapsed since last update
     */
    void update(double deltaTime);
}
