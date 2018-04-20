package oopang.model.shooter;

import java.util.function.Supplier;

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
     * Check if the shooting action can be performed.
     * @return
     *      if the shooter can shoot
     */
    boolean canShoot();

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
