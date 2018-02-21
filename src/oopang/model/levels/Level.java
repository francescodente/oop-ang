package oopang.model.levels;

import java.util.stream.Stream;

import oopang.commons.events.Event;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.GameObjectFactory;

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
     * Returns a factory for objects that will be added to this level.
     * @return
     *      the factory for this level.
     */
    GameObjectFactory getGameObjectFactory();

    /**
     * Returns the {@link Event} object that is triggered when a new {@link GameObject} is
     * created and added to the level.
     * @return
     *      the {@link Event} object.
     */
    Event<GameObject> getObjectCreatedEvent();
}
