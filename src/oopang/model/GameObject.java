package oopang.model;

import java.util.Optional;
import java.util.stream.Stream;

import oopang.commons.Point2D;

public interface GameObject {
    
    /**
     * Starts all Components of this GameObject
     */
    void start();
    
    /**
     * Updates this GameObject status
     * @param deltaTime 
     *      time elapsed since last update
     */
    void update(final double deltaTime);
    
    /**
     * Removes this GameObject from the Level
     */
    void destroy();
    
    /**
     * Gets a Component of the specified type
     * @return
     *      An Optional containing the requested Component if present
     */
    default <T extends Component> Optional<T> getComponent(final Class<T> type) {
        return this.getAllComponents()
                .filter(c -> type.isInstance(c))
                .map(c -> type.cast(c))
                .findFirst();
    }
    
    /**
     * Gets a Stream of all components of this GameObject
     * @return
     *      Stream of all components of this GameObject
     */
    Stream<Component> getAllComponents();
    
    /**
     * Returns the current position of this GameObject
     * @return
     *      current position
     */
    Point2D getPosition();
    
    /**
     * Sets the current position of this GameObject to the given Point2D
     * @param position
     *      the new position
     */
    void setPosition(final Point2D position);
    
    
}
