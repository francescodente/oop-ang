package oopang.model.gameobjects;

import java.util.Optional;
import java.util.stream.Stream;

import oopang.commons.events.EventHandler;
import oopang.commons.space.Point2D;
import oopang.model.components.Component;

/**
 * Represents an entity of the game.
 */
public interface GameObject {

    /**
     * Starts all Components of this GameObject.
     */
    void start();

    /**
     * Updates this GameObject's status.
     * @param deltaTime 
     *      time elapsed since last update.
     */
    void update(double deltaTime);

    /**
     * Removes this GameObject from the Level.
     */
    void destroy();

    /**
     * Gets a Component of the specified type.
     * @param type
     *      the class of the component to search.
     * @param <T>
     *      the generic type of the component.
     * @return
     *      An Optional containing the requested Component, if present.
     */
    default <T extends Component> Optional<T> getComponent(Class<T> type) {
        return this.getAllComponents()
                .filter(c -> type.isInstance(c))
                .map(c -> type.cast(c))
                .findFirst();
    }

    /**
     * Gets a Stream of all components of this GameObject.
     * @return
     *      Stream of all components of this GameObject.
     */
    Stream<Component> getAllComponents();

    /**
     * Returns the current position of this GameObject.
     * @return
     *      current position.
     */
    Point2D getPosition();

    /**
     * Returns the GameObject width.
     * @return
     *      GameObject width
     */
    double getWidth();

    /**
     * Returns the GameObject height.
     * @return
     *      GameObject height.
     */
    double getHeight();

    /**
     * Sets the current position of this GameObject to the given Point2D.
     * @param position
     *      the new position.
     */
    void setPosition(Point2D position);

    /**
     * Registers an {@link EventHandler} for when the object is destroyed.
     * @param handler
     *      the {@link EventHandler}.
     */
    void registerDestroyedEvent(EventHandler<GameObject> handler);

    /**
     * Unregisters an {@link EventHandler} for when the object is destroyed.
     * @param handler
     *      the {@link EventHandler} to remove.
     */
    void unregisterDestroyedEvent(EventHandler<GameObject> handler);
}
