package oopang.model;

import oopang.model.levels.Level;

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

    /**
     * Set in LevelManager the current Level.
     * @param level
     *      the current level to be played
     */
    void setCurrentLevel(Level level);
}
