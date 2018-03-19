package oopang.model.levels;

import oopang.commons.space.Vector2D;
import oopang.commons.space.Vectors2D;
import oopang.model.BallColor;

/**
 * Represents a decorator for level that spawns ball constantly.
 */
public class InfiniteLevel extends LevelDecorator {

    private static final int BALL_START_SIZE = 4;
    private static final Vector2D BALL_START_VELOCITY = Vectors2D.RIGHT;
    private static final double INITIAL_WAIT_TIME = 15;
    private static final double DECREASE_RATE = 0.9;

    private double currentWaitTime;
    private double nextBallTimeLeft;

    /**
     * Creates a new infinite level based on the given level instance.
     * @param baseLevel
     *      the {@link Level} object to decorate.
     */
    public InfiniteLevel(final Level baseLevel) {
        super(baseLevel);
        this.currentWaitTime = INITIAL_WAIT_TIME;
        this.nextBallTimeLeft = INITIAL_WAIT_TIME;
    }

    @Override
    public void update(final double deltaTime) {
        super.update(deltaTime);
        this.nextBallTimeLeft -= deltaTime;
        if (this.nextBallTimeLeft <= 0) {
            this.spawnBall();
            this.currentWaitTime *= DECREASE_RATE;
            this.nextBallTimeLeft = this.currentWaitTime;
        }
    }

    private void spawnBall() {
        this.getGameObjectFactory().createBall(BALL_START_SIZE, BALL_START_VELOCITY, BallColor.randomColor());
    }

}
