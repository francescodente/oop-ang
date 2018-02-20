package oopang.model.shooter;

import oopang.model.gameobjects.Shot;

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


}
