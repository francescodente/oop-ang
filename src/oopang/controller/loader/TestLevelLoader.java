package oopang.controller.loader;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import oopang.commons.space.Point2D;
import oopang.commons.space.Points2D;
import oopang.commons.space.Vectors2D;
import oopang.model.BallColor;
import oopang.model.Model;
import oopang.model.levels.BaseLevel;
import oopang.model.levels.InfiniteLevel;
import oopang.model.levels.Level;
import oopang.model.levels.LevelBuilder;
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
    private final PowerFactory factory = new BasicPowerFactory();

    @Override
    public LevelData loadInfiniteLevel(final LevelBuilder builder) {
        final Supplier<Power> freeze = () -> factory.createFreeze();
        final Supplier<Power> doubleShoot = () -> factory.createDoubleShot();
        builder.setSurvival();
        builder.addAvailablePower(freeze);
        builder.addAvailablePower(doubleShoot);
        return new LevelData(ImageID.getRandomBackground(), ImageID.getRandomWallTexture(), builder.build());
    }

    @Override
    public LevelData loadStoryLevel(final int index, final LevelBuilder builder) {
        final Supplier<Power> freeze = () -> factory.createFreeze();
        final Supplier<Power> doubleShoot = () -> factory.createDoubleShot();
        final Supplier<Power> sticky = () -> factory.createAdhesiveShot();
        final Supplier<Power> speed = () -> factory.createDoubleSpeed();
        final Supplier<Power> shield = () -> factory.createTimedShield();
        final List<Supplier<Power>> powerList = Arrays.asList(freeze, doubleShoot, sticky, speed, shield);

        builder.addAvailablePower(freeze).addAvailablePower(shield).addAvailablePower(sticky);
        builder.addBall(4, Vectors2D.LEFT.multiply(XSPEED), BallColor.randomColor(), BALL_POS);
        builder.setTime(60);
        builder.addWall(30, Model.WALL_WIDTH, Points2D.of(0, 50));
        return new LevelData(ImageID.getRandomBackground(), ImageID.getRandomWallTexture(), builder.build());

    }
}
