package oopang.model.levels;

import java.util.stream.Stream;

import oopang.commons.events.Event;
import oopang.model.GameOverStatus;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.GameObjectFactory;
import oopang.model.physics.CollisionManager;

/**
 * Represents a level containing all GameObjects.
 */
public interface Level {

    /**
     * Starts all Level GameObjects and initialize stats.
     */
    void start();

    /**
     * Updates all Level GameObjects and update stats.
     * @param deltaTime
     *      the time elapsed since last update.
     */
    void update(double deltaTime);

    /**
     * Returns a Stream of all GameObjects in this Level.
     * @return
     *      Stream of all GameObjects.
     */
    Stream<GameObject> getAllObjects();

    /**
     * Adds a GameObject to this Level.
     * @param obj
     *      the GameObject to be added.
     */
    void addGameObject(GameObject obj);

    /**
     * Removes the given GameObject from this Level.
     * @param obj
     *      the GameObject to be removed.
     */
    void removeGameObject(GameObject obj);

    /**
     * Returns the current score of this level.
     * @return
     *      the score.
     */
    int getScore();

    /**
     * Adds a value to the current score of the level.
     * @param amount
     *      the amount to add.
     */
    void addScore(int amount);

    /**
     * Returns a factory for objects that will be added to this level.
     * @return
     *      the factory for this level.
     */
    GameObjectFactory getGameObjectFactory();

    /**
     * Returns a collision manager for collidable in this level.
     * @return
     *      the collision manager for this level
     */
    CollisionManager getCollisionManager();

    /**
     * Returns the Object created event.
     * @return
     *      the object created event
     */
    Event<GameObject> getObjectCreatedEvent();

    /**
     * Returns the GameOverStatus event.
     * @return
     *      the gameOverStatus event.
     */
    Event<GameOverStatus> getGameOverStatusEvent();

}
