package oopang.model.shooter;

import oopang.model.gameobjects.GameObject;

/**
 * This is the Basic implementation of the Shooter Object.
 * It can shoot just one projectile and waits until it collides with something different from the player (ball,wall)
 * The Shot disappears immediately after the collision so the canShoot method can be called again.
 */

public class BasicShooter extends MultipleShooter {

    /**
     * Create a new BasicShooter instance.
     * @param player
     *      the player reference
     */
    public BasicShooter(final GameObject player) {
        super(1, player);
    }

}
