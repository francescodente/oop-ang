package oopang.model;

import oopang.commons.LevelManager;
import oopang.model.levels.Level;

/**
 * This class is the concrete implementation of the Model interface.
 * It contains the basic methods for loading new levels and update.
 *
 */
public class World implements Model {

    @Override
    public void update(final double deltaTime) {
        LevelManager.getCurrentLevel().update(deltaTime);
    }

    @Override
    public void setCurrentLevel(final Level level) {
        LevelManager.setCurrentLevel(level);
        level.start();
    }

}
