package oopang.controller.loader;

import java.util.Optional;
import java.util.logging.Level;

/**
 * The interface that controls the loading of the selected level into the game.
 */
public interface LevelLoader {
    /**
     * Method that load the level.
     * @param level
     *      The kind of level to load.
     * @param index
     *      The index of the level (if present) to load.
     */
    public Level load(LevelTag level, Optional<Integer> index);
}
