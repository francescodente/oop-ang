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

    /**
     * Check if the shots should be reset and call reset shot.
     */
    void checkReset();


    /**
     * Reset shot status to be ready for next shooting.
     * @param shot
     *      the shot to be reset
     */
    void resetShot(Shot shot);

}
