package oopang.controller.loader;

/**
 * The interface that controls the loading of the selected level into the game.
 */
public interface LevelLoader {

    /**
     * Method that load the infinite level.
     * @return
     *      the levelData of the loaded level
     */
    LevelData loadInfiniteLevel();

    /**
     * Method that load a level of the story mode.
     * @param index
     *      The index of the level to load
     * @return
     *      Information about the level 
     */
    LevelData loadStoryLevel(Integer index);

    /**
     * Method that load the Tutorial.
     * @return
     *      Information about the tutorial level
     */
    LevelData loadTutorial();
}
