package oopang.model.gameobjects;

import java.util.stream.Stream;

import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Rectangle;

import oopang.model.components.CollisionComponent;
import oopang.model.components.Component;
import oopang.model.components.GravityComponent;
import oopang.model.components.MovementComponent;
import oopang.model.physics.CollisionTag;
import oopang.model.powers.Power;
/**
 *  This class represents the GameObject Pick-up, which is a series of enhancements 
 *  that the player can get colliding.
 * 
 * 
 */
public class Pickup extends AbstractGameObject {
    private static final double WIDTH = 6;
    private static final double HEIGHT = 6;
    private static final double TIMEOUT = 10;

    private final GravityComponent gravitycomponent;
    private final MovementComponent movementcomponent;
    private final CollisionComponent collisioncomponent;
    private double time;
    private final Power power;
    private boolean inCollision;

    /**
     * Create a Pick-Up GameObject.
     * @param power
     *      The specific enhancement of each pick-up.
     */ 
    public Pickup(final Power power) {
        super();
        this.power = power;
        this.movementcomponent = new MovementComponent(this);
        this.gravitycomponent = new GravityComponent(this);
        final Convex pickup = new Rectangle(WIDTH, HEIGHT);
        this.collisioncomponent = new CollisionComponent(this, pickup, CollisionTag.PICKUP);
        this.inCollision = false;
        this.collisioncomponent.getCollisionEvent().register(c -> this.inCollision = true);
    }

    @Override
    public final void update(final double deltaTime) {
        super.update(deltaTime);
        if (inCollision) {
            this.inCollision = false;
            this.gravitycomponent.disable();
        } else {
            this.gravitycomponent.enable();
        }
        this.time += deltaTime;
        if (this.time > TIMEOUT) {
            destroy();
        }
    }

    @Override
    public final Stream<Component> getAllComponents() {
        return Stream.of(this.gravitycomponent, this.movementcomponent, this.collisioncomponent); 
    }

    /**
     * This getter returns the associated power.
     * @return
     *      the associated power.
     */
    public Power getPower() {
        return this.power;
    }

    @Override
    public final double getWidth() {
        return WIDTH;
    }

    @Override
    public final double getHeight() {
        return HEIGHT;
    }
}
