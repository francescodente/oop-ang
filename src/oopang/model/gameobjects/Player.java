package oopang.model.gameobjects;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Rectangle;

import oopang.commons.PlayerTag;
import oopang.commons.Timeable;
import oopang.commons.events.EventSource;
import oopang.commons.events.Event;
import oopang.model.components.CollisionComponent;
import oopang.model.components.Component;
import oopang.model.components.InputComponent;
import oopang.model.components.MovementComponent;
import oopang.model.components.ShooterComponent;
import oopang.model.levels.LevelManager;
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
    private static final double DEFAULT_SPEED = 30;
    private static final Supplier<Shot> DEFAULT_SHOT = 
            () -> LevelManager.getCurrentLevel().getGameObjectFactory().createHookShot();

    private final InputComponent input;
    private final MovementComponent movement;
    private final CollisionComponent collision;
    private final ShooterComponent shoot;
    private final List<Power> powerUps;
    private double speed;
    private int invulnerable;
    private final PlayerTag tag;
    private final EventSource<Power> pickupCollected;
    /**
     * Constructor of this class.
     * @param tag
     *      the tag used to distinguish the player1 from player2.
     */
    public Player(final PlayerTag tag) {
        super();
        this.tag = tag;
        final Convex shape = new Rectangle(WIDTH, HEIGHT);
        shape.translate(0, HEIGHT / 2);
        this.collision = new CollisionComponent(this, shape, CollisionTag.PLAYER);
        this.movement = new MovementComponent(this);
        this.shoot = new ShooterComponent(this);
        this.shoot.setShooter(new MultipleShooter(1, this, DEFAULT_SHOT));
        this.input = new InputComponent(this,
                e -> this.movement.setVelocity(e.multiply(this.speed)),
                () -> this.shoot.tryShoot());
        this.powerUps = new LinkedList<>();
        this.pickupCollected = new EventSource<Power>();
        this.speed = DEFAULT_SPEED;
        this.invulnerable = 0;
    }

    /**
     * Method start which call the utility method.
     */
    @Override
    public void start() {
        super.start();
        this.collision.getCollisionEvent().register(this::checkCollission);
    }

    /**
     * Method update to refresh all the active powerUps.
     */
    @Override
    public void update(final double deltaTime) {

        this.powerUps.forEach(p -> {
            if (p.isActive()) {
                p.update(deltaTime);
            } else {
                powerUps.remove(p);
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
        if (c.getOther().getCollisionTag() == CollisionTag.BALL && this.invulnerable == 0) {
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
    private void addPower(final Power power) {
        boolean found = false;
        for (final Power p : powerUps) {
            if (p.getPowertag() == power.getPowertag() &&  (p instanceof Timeable)) {
                ((Timeable) p).addTime(((Timeable) power).getRemainingTime());
                found = true;
            }
        }
        if (!found) {
            powerUps.add(power);
            this.pickupCollected.trigger(power);
            power.activate(this);
        }
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

    @Override
    public <T> T accept(final GameObjectVisitor<T> visitor) {
        return visitor.visit(this);
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
     * Getter of the PlayerTag.
     * @return
     *      The player tag.
     */
    public PlayerTag getPlayerTag() {
        return this.tag;
    }

    /**
     * Returns the event which triggers when a Pickup is collected.
     * @return
     *      the pickup collected event
     */
    public Event<Power> getPickupCollectedEvent() {
        return this.pickupCollected;
    }

    /**
     * Sets whether to ignore collisions with balls or not.
     * @param invulnerable
     *      the invulnerable state.
     */
    public void setInvulnerable(final boolean invulnerable) {
        this.invulnerable = invulnerable ? this.invulnerable + 1 : this.invulnerable - 1;
    }
}
