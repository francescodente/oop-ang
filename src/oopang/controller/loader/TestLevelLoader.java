package oopang.controller.loader;

import java.util.Arrays;
import java.util.function.Supplier;

import oopang.commons.space.Point2D;
import oopang.commons.space.Points2D;
import oopang.commons.space.Vectors2D;
import oopang.model.BallColor;
import oopang.model.levels.BaseLevel;
import oopang.model.levels.InfiniteLevel;
import oopang.model.levels.Level;
import oopang.model.levels.PickUpGeneratingLevel;
import oopang.model.levels.TimedLevel;
import oopang.model.powers.BasicPowerFactory;
import oopang.model.powers.Power;
import oopang.model.powers.PowerFactory;
import oopang.view.rendering.ImageID;

/**
 * Class to try some test for the game.
 */
public class TestLevelLoader implements LevelLoader {

    private static final double XSPEED = 15;
    private static final Point2D BALL_POS = Points2D.of(70, 50);

    @Override
    public LevelData loadInfiniteLevel() {
        Level level = new BaseLevel();
        final PowerFactory factory = new BasicPowerFactory();
        final Supplier<Power> freeze = () -> factory.createFreeze();
        level = new InfiniteLevel(new PickUpGeneratingLevel(level, Arrays.asList(freeze)));
        return new LevelData(ImageID.ANGKOR_WAT, level);
    }

    @Override
    public LevelData loadStoryLevel(final int index) {
        final PowerFactory factory = new BasicPowerFactory();
        final Level level = new TimedLevel(new BaseLevel(), 100);
        level.getGameObjectFactory().createBall(2, Vectors2D.LEFT.multiply(XSPEED), BallColor.BLUE).setPosition(BALL_POS);
        level.getGameObjectFactory().createPickup(factory.createDoubleShot()).setPosition(BALL_POS);
        return new LevelData(ImageID.TAJ_MAHAL, level);
    }

    @Override
    public LevelData loadTutorial() {
        // TODO Auto-generated method stub
        return null;
    }
}
