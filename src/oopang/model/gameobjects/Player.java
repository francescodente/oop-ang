package oopang.model.gameobjects;

import java.util.List;
import java.util.stream.Stream;

import org.dyn4j.geometry.Capsule;

import oopang.model.components.CollisionComponent;
import oopang.model.components.Component;
import oopang.model.components.InputComponent;
import oopang.model.components.MovementComponent;
import oopang.model.components.ShooterComponent;
import oopang.model.physics.CollisionTag;
import oopang.model.physics.Collision;
import oopang.model.powers.Power;

/**
 * This class implements the player object.
 */
public class Player extends AbstractGameObject {

    private static final double WIDTH = 1;
    private static final double HEIGHT = 1;

    private InputComponent input;
    private MovementComponent movement;
    private CollisionComponent collision;
    private ShooterComponent shoot;
    private List<Power> powerUps;

    /**
     * Constructor of this class.
     */
    public Player() {
        super();
        this.collision = new CollisionComponent(this, new Capsule(WIDTH, HEIGHT), CollisionTag.PLAYER);
        this.movement = new MovementComponent(this);
        this.shoot = new ShooterComponent(this);
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
        pow.activate(this);
    }

    @Override
    public final Stream<Component> getAllComponents() {
        return Stream.of(this.input, this.movement, this.collision, this.shoot);
    }
}
