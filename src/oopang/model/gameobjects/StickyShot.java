package oopang.model.gameobjects;

import oopang.commons.space.Vectors2D;
import oopang.model.physics.Collision;
import oopang.model.physics.CollisionTag;

/**
 * This class implements the GameObject Shot which is a projectile that can be shot by the player to hit the balls.
 * It is destroyed only when colliding with a ball and sticks to the wall for some time.
 */
public final class StickyShot extends HookShot {

    private static final double TIMEOUT = 3;

    private boolean isSticked;
    private double time;

    /**
     * Create a new StickyShot instance.
     */
    public StickyShot() {
        super();
        isSticked = false;
        time = 0.0;
    }

    @Override
    public void update(final double deltaTime) {
        super.update(deltaTime);
        if (isSticked) {
            time += deltaTime;
            if (time >= TIMEOUT) {
                this.destroy();
            }
        }
    }

    @Override
    protected void handleCollision(final Collision c) {
        final CollisionTag tag = c.getOther().getCollisionTag();
        if (tag == CollisionTag.BALL) {
            this.destroy();
        }
        if (tag == CollisionTag.WALL) {
            isSticked = true;
            this.getMovementComponent().setVelocity(Vectors2D.ZERO);
        }
    }

}
