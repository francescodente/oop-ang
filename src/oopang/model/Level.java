package oopang.model;

import java.util.stream.Stream;

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
}
