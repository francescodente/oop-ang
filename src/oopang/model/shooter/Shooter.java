package oopang.model.shooter;

import oopang.model.gameobjects.Shot;

/**
 * This interface represent an Object that allow the player to shoot a Shot.
 */
public interface Shooter {

    /**
     * Enable the shooting.
     * @return
     *      if the shooter can shoot
     */
    boolean canShoot();

    /**
     * Shoot the projectile.
     */
    void shoot();

    /**
     * Reset shot status to be ready for next shooting.
     * @param shot
     *      the shot to be reset
     */
    void resetShot(Shot shot);

    /**
     * Check if the shot should be reset.
     * 
     */
    void checkReset();

}
