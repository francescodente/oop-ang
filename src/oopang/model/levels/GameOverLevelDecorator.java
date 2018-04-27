package oopang.model.levels;

import oopang.commons.events.EventSource;
import oopang.commons.events.CompositeEvent;
import oopang.commons.events.Event;
import oopang.model.GameOverStatus;
import oopang.model.LevelResult;

/**
 * Represents a common class for level decorators that may want to end the level.
 */
public abstract class GameOverLevelDecorator extends LevelDecorator {

    private final EventSource<GameOverStatus> gameOverEvent;

    /**
     * Creates a new game over level based on the given level instance.
     * @param baseLevel
     *      the {@link Level} object to decorate.
     */
    public GameOverLevelDecorator(final Level baseLevel) {
        super(baseLevel);
        this.gameOverEvent = new EventSource<>();
    }

    @Override
    public final Event<GameOverStatus> getGameOverStatusEvent() {
        final CompositeEvent<GameOverStatus> composition = new CompositeEvent<>();
        composition.addEvent(gameOverEvent);
        composition.addEvent(super.getGameOverStatusEvent());
        return composition;
    }

    /**
     * Terminates this level with the given status.
     * @param result
     *      the level result.
     */
    protected void endLevel(final LevelResult result) {
        this.gameOverEvent.trigger(new GameOverStatus(this.getScore(), result));
    }
}
