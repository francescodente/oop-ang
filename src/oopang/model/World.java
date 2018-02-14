package oopang.model;

import java.util.Optional;

import oopang.model.levels.Level;

/**
 * This class is the concrete implementation of the Model interface.
 * It contains the basic methods for loading new levels and update.
 *
 */
public class World implements Model {

    private Optional<Level> currentLevel = Optional.empty();

    @Override
    public Level getCurrentLevel() {
        if (currentLevel.isPresent()) {
            return this.currentLevel.get();
        } 
        throw new IllegalStateException();
    }

    @Override
    public void setCurrentLevel(final Level level) {
        this.currentLevel = Optional.of(level);
    }

    @Override
    public void update(final double deltaTime) {
        if (currentLevel.isPresent()) {
            this.currentLevel.get().update(deltaTime);
        }

    }

}
