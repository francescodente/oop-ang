package oopang.model.gameobjects;

import java.util.stream.Stream;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Rectangle;
import oopang.commons.Timeable;
import oopang.commons.events.Event;
import oopang.commons.events.EventSource;
import oopang.model.components.CollisionComponent;
import oopang.model.components.Component;
import oopang.model.components.GravityComponent;
import oopang.model.components.MovementComponent;
import oopang.model.physics.CollisionTag;
import oopang.model.powers.Power;

/**
 *  This class represents the GameObject Pickup, which is a wrapper for a power
 *  that the player can obtain when colliding.
 */
public final class Pickup extends AbstractGameObject implements Timeable {
    private static final double WIDTH = 6;
    private static final double HEIGHT = 6;
    private static final double TIMEOUT = 7;

    private final EventSource<Void> timeoutEvent;
    private final EventSource<Double> timeChangedEvent;
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
        this.time = TIMEOUT;
        this.timeoutEvent = new EventSource<>();
        this.timeChangedEvent = new EventSource<>();
        this.movementcomponent = new MovementComponent(this);
        this.gravitycomponent = new GravityComponent(this);
        final Convex pickup = new Rectangle(WIDTH, HEIGHT);
        this.collisioncomponent = new CollisionComponent(this, pickup, CollisionTag.PICKUP);
        this.inCollision = false;
        this.collisioncomponent.getCollisionEvent().register(c -> this.inCollision = true);
    }

    @Override
    public void update(final double deltaTime) {
        super.update(deltaTime);
        if (inCollision) {
            this.inCollision = false;
            this.gravitycomponent.disable();
        } else {
            this.gravitycomponent.enable();
        }
        this.time -= deltaTime;
        this.timeChangedEvent.trigger(this.getRemainingTimePercentage());
        if (this.time < 0) {
            this.timeoutEvent.trigger(null);
            destroy();
        }
    }

    @Override
    public Stream<Component> getAllComponents() {
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
    public double getWidth() {
        return WIDTH;
    }

    @Override
    public double getHeight() {
        return HEIGHT;
    }

    @Override
    public <T> T accept(final GameObjectVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public double getRemainingTimePercentage() {
        return this.time / TIMEOUT;
    }

    @Override
    public double getRemainingTime() {
        return this.time;
    }

    @Override
    public Event<Void> getTimeOutEvent() {
         return this.timeoutEvent;
    }

    @Override
    public Event<Double> getTimeChangedEvent() {
        return this.timeChangedEvent;
    }

    @Override
    public void addTime(final double time) {
        //The lifetime of a pickup cannot be extend.
    }
}
