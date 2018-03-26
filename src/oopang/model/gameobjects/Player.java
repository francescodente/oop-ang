package oopang.model.gameobjects;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.dyn4j.geometry.Capsule;
import org.dyn4j.geometry.Convex;

import oopang.commons.LevelManager;
import oopang.commons.events.Event;
import oopang.commons.events.EventHandler;
import oopang.model.components.CollisionComponent;
import oopang.model.components.Component;
import oopang.model.components.InputComponent;
import oopang.model.components.MovementComponent;
import oopang.model.components.ShooterComponent;
import oopang.model.physics.CollisionTag;
import oopang.model.physics.Collision;
import oopang.model.powers.Power;
import oopang.model.shooter.MultipleShooter;

/**
 * This class implements the player object.
 */
public class Player extends AbstractGameObject {

    private static final double WIDTH = 12;
    private static final double HEIGHT = 15;
    private static final double DEFAULT_SPEED = 1;
    private static final Supplier<Shot> DEFAULT_SHOT = 
            () -> LevelManager.getCurrentLevel().getGameObjectFactory().createHookShot();

    private final InputComponent input;
    private final MovementComponent movement;
    private final CollisionComponent collision;
    private final ShooterComponent shoot;
    private final List<Power> powerUps;
    private double speed;
    private final Event<Power> pickupCollected;
    /**
     * Constructor of this class.
     */
    public Player() {
        super();
        final Convex shape = new Capsule(WIDTH, HEIGHT);
        shape.translate(0, HEIGHT / 2);
        this.collision = new CollisionComponent(this, shape, CollisionTag.PLAYER);
        this.movement = new MovementComponent(this);
        this.shoot = new ShooterComponent(this);
        this.shoot.setShooter(new MultipleShooter(1, this, DEFAULT_SHOT));
        this.input = new InputComponent(this,
                e -> this.movement.setVelocity(e.multiply(this.speed)),
                () -> this.shoot.tryShoot());
        this.powerUps = new LinkedList<>();
        this.pickupCollected = new Event<Power>();
        this.speed = DEFAULT_SPEED;
    }

    /**
     * Method start which call the utility method.
     */
    @Override
    public void start() {
        this.collision.registerCollisionEvent(this::checkCollission);
    }

    /**
     * Method update to refresh all the active powerUps.
     */
    @Override
    public void update(final double deltaTime) {
        this.powerUps.forEach(c -> {
            if (c.isActive()) {
                c.update(deltaTime);
            }
        });
        super.update(deltaTime);
    }

    /**
     * Utility method to check the Collision.
     * @param c
     *      Type of Collision
     */
    private void checkCollission(final Collision c) {
        if (c.getOther().getCollisionTag() == CollisionTag.BALL) {
            this.destroy();
        }
        if (c.getOther().getCollisionTag() == CollisionTag.PICKUP) {
            final Pickup powerup = (Pickup) c.getOther().getAttachedGameObject().get();
            addPower(powerup.getPower());
            c.getOther().getAttachedGameObject().get().destroy();
        }
    }

    /**
     * Method which add the power to the List and activate it.
     * @param pow
     *      Power to add and activate.
     */
    private void addPower(final Power pow) {
        powerUps.add(pow);
        this.pickupCollected.trigger(pow);
        pow.activate(this);
    }

    @Override
    public final Stream<Component> getAllComponents() {
        return Stream.of(this.input, this.movement, this.collision, this.shoot);
    }

    @Override
    public double getWidth() {
        return WIDTH;
    }

    @Override
    public double getHeight() {
        return HEIGHT;
    }

    /**
     * Setter of speed.
     * @param speed
     *      Speed to set.
     */
    public void setSpeed(final double speed) {
        this.speed = speed;
    }

    /**
     * Getter of the speed.
     * @return
     *      The actual speed.
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Register Pickup collected event.
     * @param handler
     *      the {@link EventHandler}.
     */
    public void registerPickupCollectedEvent(final EventHandler<Power> handler) {
        this.pickupCollected.registerHandler(handler);
    }
}
