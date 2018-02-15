package oopang.model.shooter;

import oopang.model.gameobjects.Shot;
import oopang.model.levels.Level;

/**
 * This is the Basic implementation of the Shooter Object.
 * It can shoot just one projectile and waits until it collides with something different from the player (ball,wall)
 * The Shot disappears immediately after the collision so the canShoot method can be called again.
 */

public class BasicShooter implements Shooter {

    private final Shot shot;
    private boolean isShooting;

    /**
     * Create a new basicShooter instance.
     * @param level
     *      the current level reference
     */
    public BasicShooter(final Level level) {
        this.shot = new Shot(level);
        this.isShooting = false;
    }

    @Override
    public boolean canShoot() {
        return !isShooting;
    }

    @Override
    public void shoot() {
        if (!isShooting) {
            isShooting = true;
            //TODO implement method
        }
    }

    @Override
    public void checkReset() {
        isShooting = !shot.isCollidingWith().isPresent();
    }

    @Override
    public void resetShot(final Shot shot) {
        checkReset();
        if (!isShooting) {
         // TODO Auto-generated method stub
        }

    }



}
