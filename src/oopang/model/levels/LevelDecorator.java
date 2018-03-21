package oopang.model.levels;

import java.util.stream.Stream;

import oopang.commons.events.EventHandler;
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

    @Override
    public void start() {
        this.innerLevel.start();
    }

    @Override
    public void update(final double deltaTime) {
        this.innerLevel.update(deltaTime);
    }

    @Override
    public Stream<GameObject> getAllObjects() {
        return this.innerLevel.getAllObjects();
    }

    @Override
    public void addGameObject(final GameObject obj) {
        this.innerLevel.addGameObject(obj);
    }

    @Override
    public void removeGameObject(final GameObject obj) {
        this.innerLevel.removeGameObject(obj);
    }

    @Override
    public int getScore() {
        return this.innerLevel.getScore();
    }

    @Override
    public void addScore(final int amount) {
        this.innerLevel.addScore(amount);
    }

    @Override
    public GameObjectFactory getGameObjectFactory() {
        return this.innerLevel.getGameObjectFactory();
    }

    @Override
    public CollisionManager getCollisionManager() {
        return this.innerLevel.getCollisionManager();
    }

    @Override
    public void registerObjectCreatedEvent(final EventHandler<GameObject> handler) {
        this.innerLevel.registerObjectCreatedEvent(handler);
    }

    @Override
    public void unregisterObjectCreatedEvent(final EventHandler<GameObject> handler) {
        this.innerLevel.unregisterObjectCreatedEvent(handler);
    }

    @Override
    public void registerGameOverEvent(final EventHandler<GameOverStatus> handler) {
        this.innerLevel.registerGameOverEvent(handler);
    }
}
