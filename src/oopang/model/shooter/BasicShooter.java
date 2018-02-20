package oopang.model.shooter;

import oopang.model.levels.Level;

/**
 * This is the Basic implementation of the Shooter Object.
 * It can shoot just one projectile and waits until it collides with something different from the player (ball,wall)
 * The Shot disappears immediately after the collision so the canShoot method can be called again.
 */

public class BasicShooter extends MultipleShooter {

    /**
     * Create a BasicShooter Object.
     * @param level
     *      current level reference
     */
    public BasicShooter(final Level level) {
        super(level, 1);
    }

}
