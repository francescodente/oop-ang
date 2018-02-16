package oopang.model.shooter;

import java.util.HashMap;
import java.util.Map;

import oopang.model.gameobjects.Shot;
import oopang.model.levels.Level;

/**
 * This is the implementation of the MultipleShooter Object.
 * It can shoot up to max projectile at the same time and waits until one collides with something different from the player (ball,wall)
 * The Shot disappears immediately after the collision so the canShoot method can be called again.
 * 
 * It has a map of shots with a value describing whether the shot can be shot or not.
 */

public class MultipleShooter implements Shooter {

    private final Map<Shot, Boolean> shots;
    private final int max;
    private int currentShooting;

    /**
     * Create a new basicShooter instance.
     * @param level
     *      the current level reference
     * @param max
     *      max number that can be shot at the same time 
     */
    public MultipleShooter(final Level level, final int max) {
        this.max = max;
        this.currentShooting = 0;

        this.shots = new HashMap<>();
        for (int i = 0; i < max; i++) {
            shots.put(new Shot(level), true); //TODO change with factory constructor of shot
        }
    }

    @Override
    public boolean canShoot() {
        return currentShooting < max;
    }

    @Override
    public void shoot() {
        if (canShoot()) {
            Shot tobeShot = shots.entrySet().stream().filter(e -> e.getValue()).findFirst().get().getKey();
            tobeShot.start(); //TODO change with implementation of shooting action
            currentShooting++;
            shots.put(tobeShot, false);
        }

    }

    @Override
    public void checkReset() {
        shots.entrySet().stream()
                        .filter(e -> !e.getValue())
                        .map(e -> e.getKey())
                        .forEach(s -> {
                            if (s.isCollidingWith().isPresent()) {
                                resetShot(s);
                                }
                        });
    }

    @Override
    public void resetShot(final Shot shot) {
        //TODO reset the shot
        currentShooting--;
        shots.put(shot, true);
    }

}
