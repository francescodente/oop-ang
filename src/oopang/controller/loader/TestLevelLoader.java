package oopang.controller.loader;

import oopang.model.levels.BaseLevel;
import oopang.model.levels.Level;
import oopang.view.rendering.ImageID;

/**
 * Class to try some test for the game.
 */
public class TestLevelLoader implements LevelLoader {

    @Override
    public LevelData loadInfiniteLevel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LevelData loadStoryLevel(final int index) {
        final Level level = new BaseLevel();
        return new LevelData(ImageID.TAJ_MAHAL, level);
    }

    @Override
    public LevelData loadTutorial() {
        // TODO Auto-generated method stub
        return null;
    }
}
