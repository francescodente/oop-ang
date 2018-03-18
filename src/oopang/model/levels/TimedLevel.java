package oopang.model.levels;

import oopang.model.LevelResult;
import oopang.model.gameobjects.Ball;
import oopang.model.gameobjects.GameObject;

/**
 * Represents a decorator for level that adds timeout functionality. Specifically, the GameOver event
 * is triggered when the time ends.
 */
public class TimedLevel extends GameOverLevelDecorator {

    private double timeLeft;
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
        this.timeLeft = time;
        this.ballCount = baseLevel.getAllObjects().filter(obj -> obj instanceof Ball).mapToInt(o -> 1).sum();
    }

    @Override
    public void addGameObject(final GameObject obj) {
        super.addGameObject(obj);
        if (obj instanceof Ball) {
            this.ballCount++;
            obj.registerDestroyedEvent(o -> this.ballCount--);
        }
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
}
