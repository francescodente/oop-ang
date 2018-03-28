package oopang.controller.loader;

import oopang.commons.space.Points2D;
import oopang.commons.space.Vectors2D;
import oopang.model.BallColor;
import oopang.model.levels.BaseLevel;
import oopang.model.levels.Level;
import oopang.view.rendering.ImageID;

/**
 * Class to try some test for the game.
 */
public class TestLevelLoader implements LevelLoader {

    private static final double XSPEED = 22.5;

    @Override
    public LevelData loadInfiniteLevel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LevelData loadStoryLevel(final int index) {
        final Level level = new BaseLevel();
        level.getGameObjectFactory().createBall(3, Vectors2D.LEFT.multiply(XSPEED), BallColor.BLUE).setPosition(Points2D.of(70, 50));
        return new LevelData(ImageID.TAJ_MAHAL, level);
    }

    @Override
    public LevelData loadTutorial() {
        // TODO Auto-generated method stub
        return null;
    }
}
