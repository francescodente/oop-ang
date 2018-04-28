package oopang.model;

import oopang.model.levels.Level;
import oopang.model.levels.LevelBuilder;

/**
 * Model of the application.
 */
public interface Model {

    /**
     * Default width for the levels.
     */
    double WORLD_WIDTH = 200;
    /**
     * Default height for the levels.
     */
    double WORLD_HEIGHT = 100;
    /**
     * Default wall width for external walls.
     */
    double WALL_WIDTH = 5.5;
    /**
     * Total width of the world counting walls.
     */
    double TOTAL_WIDTH = WORLD_WIDTH + WALL_WIDTH * 2;
    /**
     * Total height of the world counting walls.
     */
    double TOTAL_HEIGHT = WORLD_HEIGHT + WALL_WIDTH * 2;

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

    /**
     * Creates and returns a new level builder.
     * @return
     *      the level builder.
     */
    LevelBuilder getLevelBuilder();
}
