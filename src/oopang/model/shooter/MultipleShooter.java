package oopang.model.shooter;

import oopang.commons.LevelManager;
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
    private final int max;

    /**
     * Create a new MultipleShooter instance.
     * @param max
     *      the max number of shots that can be shot simultaneously
     */
    public MultipleShooter(final int max) {
        this.currentShotNumber = 0;
        this.max = max;
    }

    @Override
    public final boolean canShoot() {
        return currentShotNumber < max;
    }

    @Override
    public final void shoot() {
        if (canShoot()) {
        //Shot newShot = LevelManager.getCurrentLevel().getGameObjectFactory().createHookShot();
        //LevelManager.getCurrentLevel().addGameObject(newShot);

        //TODO set new shot proprieties to current player pos
        this.currentShotNumber++;

        //register to event calling this.handleCollision
        }
    }

    /**
     * Used in a shotResult handler registered to each new shot.
     * @param arg
     *      the ShotResult 
     */
    protected void handleShotResult(final ShotResult arg) {
        this.decreaseCurrentShotNumber();
        arg.getShot().destroy();
    }

    /**
     * Used in children to modify currentShotNumber.
     */
    protected void decreaseCurrentShotNumber() {
        this.currentShotNumber--;
    }

}
