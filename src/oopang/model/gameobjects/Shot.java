package oopang.model.gameobjects;

import java.util.stream.Stream;

import org.dyn4j.geometry.Convex;

import oopang.commons.space.Vectors2D;
import oopang.model.components.CollisionComponent;
import oopang.model.components.Component;
import oopang.model.components.MovementComponent;
import oopang.model.physics.Collision;
import oopang.model.physics.CollisionTag;

/**
 * This is an abstract implementation of Shot.
 * 
 *
 */
public abstract class Shot extends AbstractGameObject {

    /**
     * Standard Speed for all Shot objects.
     */
    protected static final double SPEED = 1;

    private final MovementComponent movementComponent;
    private final CollisionComponent collisionComponent;

    /**
     * Create a new Shot instance.
     * @param boundingBox
     *      the shape of the Shot obj
     */
    public Shot(final Convex boundingBox) {
        super();
        this.movementComponent = new MovementComponent(this);
        this.getMovementComponent().setVelocity(Vectors2D.UP.multiply(SPEED)); 
        this.collisionComponent = new CollisionComponent(this, boundingBox, CollisionTag.SHOT);
    }

    /**
     * This method has to be called as super.start() in children.
     */
    @Override
    public void start() {
        super.start();
        this.collisionComponent.registerCollisionEvent(c -> handleCollision(c));
    }

    /**
     * Handle the collision event.
     * @param c
     *      the collision object 
     */
    protected abstract void handleCollision(Collision c);

    /**
     * Returns the MovementComponent.
     * @return
     *      the movement component
     */
    protected MovementComponent getMovementComponent() {
        return movementComponent;
    }

    /**
     * Returns the collisonComponent.
     * @return
     *      the collision component
     */
    protected CollisionComponent getCollisionComponent() {
        return collisionComponent;
    }

    @Override
    public final Stream<Component> getAllComponents() {
        return Stream.of(collisionComponent, movementComponent);
    }
}
