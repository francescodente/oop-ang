package oopang.model;

import oopang.model.levels.Level;

/**
 * Model of the application.
 */
public interface Model {
    public static final double WORLD_WIDTH = 200;
    public static final double WORLD_HEIGHT = 100;
    public static final double WALL_WIDTH = 4;
    
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
