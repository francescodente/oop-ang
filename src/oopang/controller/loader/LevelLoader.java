package oopang.controller.loader;

import java.io.IOException;

import oopang.model.levels.LevelBuilder;

/**
 * The interface that controls the loading of the selected level into the game.
 */
public interface LevelLoader {

    /**
     * Method that load the infinite level.
     * @param builder
     *      the builder used to create the level.
     * @return
     *      the levelData of the loaded level
     * @throws IOException 
     */
    LevelData loadInfiniteLevel(LevelBuilder builder) throws IOException;

    /**
     * Method that load a level of the story mode.
     * @param index
     *      The index of the level to load
     * @param builder 
     *      the builder used to create the level. 
     * @return
     *      Information about the level 
     * @throws IOException 
     */
    LevelData loadStoryLevel(int index, LevelBuilder builder) throws IOException;
}
