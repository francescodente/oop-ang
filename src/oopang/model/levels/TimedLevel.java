package oopang.model.levels;

import oopang.commons.space.Vector2D;
import oopang.model.BallColor;
import oopang.model.LevelResult;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.GameObjectFactory;
import oopang.model.gameobjects.GameObjectFactoryDecorator;

/**
 * Represents a decorator for level that adds timeout functionality. Specifically, the GameOver event
 * is triggered when the time ends.
 */
public class TimedLevel extends GameOverLevelDecorator {

    private double timeLeft;
    private final double totalTime;
    private int ballCount;

    /**
     * Creates a new timed level based on the given level instance.
     * @param baseLevel
     *      the {@link Level} object to decorate.
     * @param time
     *      the duration of the level in seconds.
     */
    public TimedLevel(final Level baseLevel, final double time) {
        super(baseLevel);
        this.totalTime = time;
        this.timeLeft = time;
        this.ballCount = 0;
    }

    @Override
    public final void update(final double deltaTime) {
        super.update(deltaTime);
        this.timeLeft -= deltaTime;
        if (this.timeLeft <= 0) {
            this.endLevel(LevelResult.OUT_OF_TIME);
        }
        if (this.ballCount == 0) {
            this.endLevel(LevelResult.LEVEL_COMPLETE);
        }
    }

    @Override
    public GameObjectFactory getGameObjectFactory() {
        return new GameObjectFactoryDecorator(super.getGameObjectFactory()) {
            @Override
            public GameObject createBall(final int size, final Vector2D velocity, final BallColor color) {
                final GameObject ball = super.createBall(size, velocity, color);
                ballCount++;
                ball.getDestroyedEvent().register(b -> ballCount--);
                return ball;
            }
        };
    }
    @Override
    public double getRemainingTimePercentage() {
        return this.timeLeft / this.totalTime;
    }
}
