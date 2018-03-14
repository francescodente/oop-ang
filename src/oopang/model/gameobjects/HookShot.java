package oopang.model.gameobjects;

import org.dyn4j.geometry.Rectangle;

import oopang.model.physics.Collision;
import oopang.model.physics.CollisionTag;

/**
 * This class implements the GameObject Shot which is a projectile that can be shot by the player to hit the balls.
 * It can collide whit walls and balls, not with the player.
 * 
 */
public class HookShot extends Shot {

    private static final double WIDTH = 1;
    private static final double START_HEIGHT = 1;

    private double startY;

    /**
     * Creates a GameObject of type Shot.
     */
    public HookShot() {
        super(new Rectangle(WIDTH, START_HEIGHT));
    }


    @Override
    public void start() {
        super.start();
        this.startY = this.getPosition().getY();
    }

    @Override
    public void update(final double deltaTime) {
        super.update(deltaTime);
        this.getCollisionComponent().setShape(new Rectangle(WIDTH, this.getPosition().getY() - startY));
    }

    @Override
    protected void handleCollision(final Collision c) {
        final CollisionTag tag = c.getOther().getCollisionTag();
        if (tag == CollisionTag.BALL || tag == CollisionTag.WALL) {
            this.destroy();
        }
    }


    @Override
    public double getWidth() {
        return WIDTH;
    }


    @Override
    public double getHeight() {
        return this.getPosition().getY() - startY;
    }

}
