package oopang.model.shooter;

import java.util.function.Supplier;

import oopang.model.gameobjects.HookShot;

/**
 * This interface represent an Object that allow the player to shoot a Shot.
 */
public interface Shooter {

    /**
     * Check if the shooting action can be performed.
     * @return
     *      if the shooter can shoot
     */
    boolean canShoot();

    /**
     * Shoot the projectile if possible otherwise do nothing.
     */
    void shoot();

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
    void setSupplier(Supplier<HookShot> supplier);

}
