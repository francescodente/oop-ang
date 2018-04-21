package oopang.model.shooter;

import java.util.function.Supplier;

import oopang.commons.space.Point2D;
import oopang.commons.space.Points2D;
import oopang.model.gameobjects.Shot;

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
    private Supplier<Shot> supplier;
    private boolean lastShootingState;
    private boolean currentShootingState;
    private Point2D position;

    /**
     * Create a new MultipleShooter instance.
     * @param max
     *      the max number of shots that can be shot simultaneously
     * @param supplier
     *      the supplier of shots
     */
    public MultipleShooter(final int max, final Supplier<Shot> supplier) {
        this.currentShotNumber = 0;
        this.max = max;
        this.position = Points2D.ZERO;
        this.supplier = supplier;
        this.currentShootingState = false;
        this.lastShootingState = false;
    }

    private boolean canShoot() {
        return currentShotNumber < max 
                && this.currentShootingState 
                && this.currentShootingState != lastShootingState;
    }

    private void shoot() {
        if (canShoot()) {
            final Shot newShot = supplier.get();
            newShot.setPosition(this.position);
            this.currentShotNumber++;
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
        this.shoot();
    }

    @Override
    public void setShootingState(final boolean state) {
        this.lastShootingState = this.currentShootingState;
        this.currentShootingState = state;
    }

    @Override
    public void setPosition(final Point2D position) {
        this.position = position;
    }

}
