package oopang.model;

import oopang.model.levels.LazyLevelBuilder;
import oopang.model.levels.Level;
import oopang.model.levels.LevelBuilder;
import oopang.model.levels.LevelManager;

/**
 * This class is the concrete implementation of the Model interface.
 * It contains the basic methods for loading new levels and update them.
 */
public final class World implements Model {

    @Override
    public void update(final double deltaTime) {
        LevelManager.getCurrentLevel().update(deltaTime);
    }

    @Override
    public void setCurrentLevel(final Level level) {
        LevelManager.setCurrentLevel(level);
        level.start();
    }

    @Override
    public LevelBuilder getLevelBuilder() {
        return new LazyLevelBuilder();
    }

}
