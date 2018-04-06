package oopang.model.shooter;

import java.util.function.Supplier;

import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.Shot;

/**
 * This is the implementation of the MultipleShooter Object.
 * It can shoot up to max projectile at the same time and waits until one collides with something different from the player (ball,wall)
 * The Shot disappears immediately after the collision so the canShoot method can be called again.
 * 
 * It has a map of shots with a value describing whether the shot can be shot or not.
 */

public class MultipleShooter implements Shooter {

    private static final double COOLDOWN = 0.1;

    private int currentShotNumber;
    private double cooldownTime;
    private int max;
    private final GameObject player;
    private Supplier<Shot> supplier;

    /**
     * Create a new MultipleShooter instance.
     * @param max
     *      the max number of shots that can be shot simultaneously
     * @param player
     *      the player reference
     * @param supplier
     *      the supplier of shots
     */
    public MultipleShooter(final int max, final GameObject player, final Supplier<Shot> supplier) {
        this.currentShotNumber = 0;
        this.max = max;
        this.player = player;
        this.supplier = supplier;
    }

    @Override
    public final boolean canShoot() {
        return currentShotNumber < max && this.cooldownTime <= 0;
    }

    @Override
    public final void shoot() {
        if (canShoot()) {
        final Shot newShot = supplier.get();
        newShot.setPosition(player.getPosition());
        this.currentShotNumber++;
        this.cooldownTime = COOLDOWN;

        newShot.getDestroyedEvent().register(s -> this.currentShotNumber--);
        }
    }

    @Override
    public void setMaxShootable(final int max) {
        this.max = max;
    }

    @Override
    public void setSupplier(final Supplier<Shot> supplier) {
        this.supplier = supplier;

    }

    @Override
    public void update(final double deltaTime) {
        this.cooldownTime -= deltaTime;
    }

}
