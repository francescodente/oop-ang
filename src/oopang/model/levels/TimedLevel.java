package oopang.model.levels;

/**
 * Represents a decorator for level that adds timeout functionality. Specifically, the GameOver event
 * is triggered when the time ends.
 */
public class TimedLevel extends LevelDecorator {

    private double timeLeft;

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
    }

    @Override
    public final void update(final double deltaTime) {
        super.update(deltaTime);
        this.timeLeft -= deltaTime;
        if (this.timeLeft <= 0) {
            // game over
        }
    }
}
