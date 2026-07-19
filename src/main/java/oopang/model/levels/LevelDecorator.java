package oopang.model.levels;

import java.util.stream.Stream;

import oopang.commons.events.Event;
import oopang.model.GameOverStatus;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.GameObjectFactory;
import oopang.model.physics.CollisionManager;

/**
 * Represents an abstraction for all level decorators. Every method of the {@link Level} interface
 * is implemented by delegating to the decorated instance of level. Thus, subclasses may access
 * the decorated object by calling this class' methods.
 */
public abstract class LevelDecorator implements Level {

    private final Level innerLevel;

    /**
     * Initializes this decorator with the given Level object.
     * @param baseLevel
     *      the level to decorate.
     */
    public LevelDecorator(final Level baseLevel) {
        this.innerLevel = baseLevel;
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public void start() {
        this.innerLevel.start();
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public void update(final double deltaTime) {
        this.innerLevel.update(deltaTime);
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public Stream<GameObject> getAllObjects() {
        return this.innerLevel.getAllObjects();
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public void addGameObject(final GameObject obj) {
        this.innerLevel.addGameObject(obj);
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public void removeGameObject(final GameObject obj) {
        this.innerLevel.removeGameObject(obj);
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public int getScore() {
        return this.innerLevel.getScore();
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public void addScore(final int amount) {
        this.innerLevel.addScore(amount);
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public GameObjectFactory getGameObjectFactory() {
        return this.innerLevel.getGameObjectFactory();
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public CollisionManager getCollisionManager() {
        return this.innerLevel.getCollisionManager();
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public Event<GameObject> getObjectCreatedEvent() {
        return this.innerLevel.getObjectCreatedEvent();
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public Event<GameOverStatus> getGameOverStatusEvent() {
        return this.innerLevel.getGameOverStatusEvent();
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public double getRemainingTimePercentage() {
        return this.innerLevel.getRemainingTimePercentage();
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public Event<Void> getTimeOutEvent() {
        return this.innerLevel.getTimeOutEvent();
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public Event<Double> getTimeChangedEvent() {
        return this.innerLevel.getTimeChangedEvent();
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public void addTime(final double time) {
        this.innerLevel.addTime(time);
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public double getRemainingTime() {
        return this.innerLevel.getRemainingTime();
    }
}

