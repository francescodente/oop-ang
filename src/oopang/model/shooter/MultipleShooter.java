package oopang.model.shooter;



import oopang.model.gameobjects.Shot;
import oopang.model.levels.Level;
import oopang.commons.events.EventHandler;

/**
 * This is the implementation of the MultipleShooter Object.
 * It can shoot up to max projectile at the same time and waits until one collides with something different from the player (ball,wall)
 * The Shot disappears immediately after the collision so the canShoot method can be called again.
 * 
 * It has a map of shots with a value describing whether the shot can be shot or not.
 */

public class MultipleShooter implements Shooter {


    private boolean canShoot;

    /**
     * Create a new shooter obj.
     * @param level
     *      currentLevel reference
     */
    public MultipleShooter(final Level level) {
        this.currentLevel = level;
    }

    @Override
    public boolean canShoot() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void shoot() {
        if (canShoot()) {
            /*
            final Shot newShot =
            newShot.getShotResultEvent().registerHandler(s -> canShoot = true);
            */
        }
    }


}
