package oopang.model.gameobjects;

import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Rectangle;

import oopang.model.physics.Collision;
import oopang.model.physics.CollisionTag;

/**
 * This class implements the GameObject Shot which is a projectile that can be shot by the player to hit the balls.
 * It can collide whit walls and balls, not with the player.
 * It can hit balls also with the rope.
 * 
 */
public class HookShot extends Shot {

    private static final double WIDTH = 2;
    private static final double START_HEIGHT = 18;

    private double startY;

    /**
     * Creates a GameObject of type HookShot.
     */
    public HookShot() {
        super(new Rectangle(WIDTH, START_HEIGHT));
    }

    @Override
    public final void start() {
        super.start();
        this.startY = this.getPosition().getY();
        this.setPosition(this.getPosition().setPointY(START_HEIGHT));
    }

    /**
     * super.update(deltaTime) has to be called when overriding this method because
     * updates the shape to a bigger one.
     */
    @Override
    public void update(final double deltaTime) {
        super.update(deltaTime);
        final Convex newShape = new Rectangle(WIDTH, this.getHeight());
        newShape.translate(0, -this.getHeight() / 2);
        this.getCollisionComponent().setShape(newShape);
    }

    /**
     * This method should be completely overridden in order to change the behaviour of the Shot.
     */
    @Override
    protected void handleCollision(final Collision c) {
        final CollisionTag tag = c.getOther().getCollisionTag();
        if (tag == CollisionTag.BALL || tag == CollisionTag.WALL) {
            this.destroy();
        }
    }

    @Override
    public final double getWidth() {
        return WIDTH;
    }

    @Override
    public final double getHeight() {
        return this.getPosition().getY() - startY;
    }

    @Override
    public final <T> T accept(final GameObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
