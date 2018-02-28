package oopang.model.shooter;


/**
 * This is the implementation of the MultipleShooter Object.
 * It can shoot one projectile at the same time and waits until one collides with a ball
 * The Shot disappears immediately after the collision so the canShoot method can be called again.
 * 
 * It has a map of shots with a value describing whether the shot can be shot or not.
 */

public class StickyShooter extends MultipleShooter {

    /**
     * Create a new StickyShooter instance.
     */
    public StickyShooter() {
        super(1);
    }

    @Override
    protected void handleShotResult(final ShotResult arg) {
        if (arg == ShotResult.BALL) {
            this.decreaseCurrentShotNumber();
        }
    }


}
