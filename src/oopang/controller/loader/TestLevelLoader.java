package oopang.controller.loader;

import oopang.commons.space.Point2D;
import oopang.commons.space.Points2D;
import oopang.commons.space.Vector2D;
import oopang.commons.space.Vectors2D;
import oopang.model.BallColor;
import oopang.model.Model;
import oopang.model.levels.LevelBuilder;
import oopang.model.powers.BasicPowerFactory;
import oopang.model.powers.PowerFactory;
import oopang.view.rendering.ImageID;

/**
 * Class to try some test for the game.
 */
public class TestLevelLoader implements LevelLoader {

    private static final double XSPEED = 15;
    private static final Point2D BALL_POS = Points2D.of(70, 50);
    private static final Point2D WALL_POS = Points2D.of(0, 50);
    private static final double WALL_WIDTH = 30;
    private static final Vector2D GRAVITY = Vectors2D.of(0, 50);
    private static final double TIME = 60;
    private final PowerFactory factory = new BasicPowerFactory();

    @Override
    public LevelData loadInfiniteLevel(final LevelBuilder builder) {
        builder.setSurvival()
            .addAvailablePower(() -> factory.createFreeze())
            .addAvailablePower(() -> factory.createDoubleShot());
        return new LevelData(ImageID.getRandomBackground(), ImageID.getRandomWallTexture(), builder.build());
    }

    @Override
    public LevelData loadStoryLevel(final int index, final LevelBuilder builder) {
        builder.addAvailablePower(() -> factory.createFreeze())
            .addAvailablePower(() -> factory.createDoubleShot())
            .addAvailablePower(() -> factory.createAdhesiveShot())
            .addAvailablePower(() -> factory.createDoubleSpeed())
            .addAvailablePower(() -> factory.createTimedShield())
            .addBall(4, Vectors2D.LEFT.multiply(XSPEED), BallColor.randomColor(), BALL_POS)
            .setTime(TIME)
            .setBallGravity(GRAVITY)
            .addWall(WALL_WIDTH, Model.WALL_WIDTH, WALL_POS);
        return new LevelData(ImageID.getRandomBackground(), ImageID.getRandomWallTexture(), builder.build());

    }
}
