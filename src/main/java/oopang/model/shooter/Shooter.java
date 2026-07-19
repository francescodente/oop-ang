package oopang.model.shooter;

import java.util.function.Supplier;

import oopang.commons.space.Point2D;
import oopang.model.gameobjects.Shot;

/**
 * This interface represent an Object that allow the player to shoot a Shot.
 */
public interface Shooter {

    /**
     * Updates the Shooter.
     * @param deltaTime
     *      the time elapsed from last update.
     */
    void update(double deltaTime);

    /**
     * Sets the Shooter current position.
     * @param position
     *      the current position of the shooter.
     */
    void setPosition(Point2D position);

    /**
     * Sets the shooter state to shooting or not shooting.
     * @param state
     *      true if the shooter should try to shoot.
     */
    void setShootingState(boolean state);

    /**
     * Change max shootable.
     * @param max
     *      the new max shootable
     */
    void setMaxShootable(int max);

    /**
     * Change shot supplier.
     * @param supplier
     *      the new shot supplier
     */
    void setSupplier(Supplier<Shot> supplier);

}
