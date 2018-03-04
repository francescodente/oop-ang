package oopang.model.shooter;

import java.util.function.Supplier;

import oopang.commons.LevelManager;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.HookShot;

/**
 * This is the implementation of the MultipleShooter Object.
 * It can shoot up to max projectile at the same time and waits until one collides with something different from the player (ball,wall)
 * The Shot disappears immediately after the collision so the canShoot method can be called again.
 * 
 * It has a map of shots with a value describing whether the shot can be shot or not.
 */

public class MultipleShooter implements Shooter {

    private int currentShotNumber;
    private int max;
    private GameObject player;
    private Supplier<HookShot> supplier;

    /**
     * Create a new MultipleShooter instance.
     * @param max
     *      the max number of shots that can be shot simultaneously
     * @param player
     *      the player reference
     * @param supplier
     *      the supplier of shots
     */
    public MultipleShooter(final int max, final GameObject player, final Supplier<HookShot> supplier) {
        this.currentShotNumber = 0;
        this.max = max;
        this.player = player;
        this.supplier = supplier;
    }

    @Override
    public final boolean canShoot() {
        return currentShotNumber < max;
    }

    @Override
    public final void shoot() {
        if (canShoot()) {
        final HookShot newShot = supplier.get();
        LevelManager.getCurrentLevel().addGameObject(newShot);

        this.currentShotNumber++;

        newShot.registerDestroyedEvent(s -> this.currentShotNumber--);
        }
    }

    @Override
    public void setMaxShootable(final int max) {
        this.max = max;
    }

    @Override
    public void setSupplier(final Supplier<HookShot> supplier) {
        this.supplier = supplier;

    }

}
