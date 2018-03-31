package oopang.model.levels;

import java.util.Random;

import oopang.commons.LevelManager;
import oopang.commons.space.Point2D;
import oopang.commons.space.Points2D;
import oopang.commons.space.Vector2D;
import oopang.commons.space.Vectors2D;
import oopang.model.BallColor;
import oopang.model.Model;
import oopang.model.gameobjects.GameObject;

/**
 * Represents a decorator for level that spawns ball constantly.
 */
public class InfiniteLevel extends LevelDecorator {

    private static final int BALL_START_SIZE = 3;
    private static final Vector2D BALL_START_VELOCITY = Vectors2D.of(15, 0);
    private static final double INITIAL_WAIT_TIME = 15;
    private static final double DECREASE_RATE = 0.9;
    private static final double SPAWN_HEIGHT = 90;
    private static final double WORLD_OFFSET = 20;
    private static final double ENABLE_TIMEOUT = 1;

    private double currentWaitTime;
    private double nextBallTimeLeft;
    private double enableTime;
    private GameObject nextBall;

    /**
     * Creates a new infinite level based on the given level instance.
     * @param baseLevel
     *      the {@link Level} object to decorate.
     */
    public InfiniteLevel(final Level baseLevel) {
        super(baseLevel);
        this.currentWaitTime = INITIAL_WAIT_TIME;
        this.nextBallTimeLeft = 0;
        this.enableTime = ENABLE_TIMEOUT;
    }

    @Override
    public void update(final double deltaTime) {
        super.update(deltaTime);
        if (this.nextBall != null) {
            this.enableTime -= deltaTime;
            if (this.enableTime <= 0) {
                this.nextBall.getAllComponents().forEach(c -> c.enable());
                enableTime = ENABLE_TIMEOUT;
                nextBall = null;
            }
        }
        this.nextBallTimeLeft -= deltaTime;
        if (this.nextBallTimeLeft <= 0) {
            this.spawnBall();
            this.currentWaitTime *= DECREASE_RATE;
            this.nextBallTimeLeft = this.currentWaitTime;
        }
    }

    private void spawnBall() {
        this.nextBall = LevelManager.getCurrentLevel()
                .getGameObjectFactory()
                .createBall(BALL_START_SIZE, BALL_START_VELOCITY, BallColor.randomColor());
        nextBall.setPosition(this.randomPosition());
        nextBall.getAllComponents().forEach(c -> c.disable());
    }

    private Point2D randomPosition() {
        final Random rand = new Random();
        final double xvalue = (rand.nextDouble() * 2 - 1) * (Model.WORLD_WIDTH / 2 - WORLD_OFFSET);
        return Points2D.of(xvalue, SPAWN_HEIGHT);
    }

}
