package oopang.model.shooter;

import java.util.function.Supplier;

import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.HookShot;

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
     * @param supplier
     *      the shot supplier
     */
    public BasicShooter(final GameObject player, final Supplier<HookShot> supplier) {
        super(1, player, supplier);
    }

}
