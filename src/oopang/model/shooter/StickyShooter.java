package oopang.model.shooter;

import oopang.model.levels.Level;

/**
 * This is the implementation of the MultipleShooter Object.
 * It can shoot one projectile at the same time and waits until one collides with a ball
 * The Shot disappears immediately after the collision so the canShoot method can be called again.
 * 
 * It has a map of shots with a value describing whether the shot can be shot or not.
 */

public class StickyShooter extends MultipleShooter {

    /**
     * Create a StickyShooter Object.
     * @param level
     *      the current level reference
     */
    public StickyShooter(final Level level) {
        super(level, 1);
    }

    @Override
    public void checkReset() {
        super.getShots().entrySet().stream()
        .filter(e -> !e.getValue())
        .map(e -> e.getKey())
        .forEach(s -> {
            if (s.isCollidingWith().isPresent()) {
                //TODO ONLY IF IS A BALL
                resetShot(s);
                }
        });
    }

}
