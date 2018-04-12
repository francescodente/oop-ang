package oopang.model.levels;

import oopang.commons.events.Event;
import oopang.commons.events.EventSource;
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
    private double totalTime;
    private int ballCount;
    private final EventSource<Void> timeOutEvent;
    private final EventSource<Double> timeChangedEvent;

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
        this.timeOutEvent = new EventSource<>();
        this.timeChangedEvent = new EventSource<>();
    }

    @Override
    public final void update(final double deltaTime) {
        super.update(deltaTime);
        this.timeLeft -= deltaTime;
        this.timeChangedEvent.trigger(this.getRemainingTimePercentage());
        if (this.timeLeft <= 0) {
            this.timeOutEvent.trigger(null);
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
        return Math.min(this.timeLeft / this.totalTime, 1);
    }

    @Override
    public Event<Void> getTimeOutEvent() {
        return this.timeOutEvent;
    }

    @Override
    public Event<Double> getTimeChangedEvent() {
        return this.timeChangedEvent;
    }

    @Override
    public double getRemainingTime() {
        return this.timeLeft;
    }

    @Override
    public void addTime(final double time) {
        this.timeLeft += time;
        this.totalTime += time;
    }
}
