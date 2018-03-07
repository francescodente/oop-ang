package oopang.model.gameobjects;

import java.util.stream.Stream;

import org.dyn4j.geometry.Capsule;

import oopang.model.components.CollisionComponent;
import oopang.model.components.Component;
import oopang.model.components.InputComponent;
import oopang.model.components.MovementComponent;
import oopang.model.components.ShooterComponent;
import oopang.model.physics.CollisionTag;
import oopang.model.physics.Collision;
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
    //private Event<GameOver> events;
    //private List<Power> powerUps;
    /**
     * Constructor of this class.
     */
    public Player() {
        super();
        collision = new CollisionComponent(this, new Capsule(WIDTH, HEIGHT), CollisionTag.PLAYER);
        movement = new MovementComponent(this);
        shoot = new ShooterComponent(this);
    }
    /**
     * Method start.
     */
    public void start() {
        collision.registerCollisionEvent(this::checkCollission);
    }
    /*
    private void death() {
    }
    */
    private void checkCollission(final Collision c) {
        if (c.getOther().getCollisionTag() == CollisionTag.BUBBLE) {
            this.destroy();
        }
        if (c.getOther().getCollisionTag() == CollisionTag.PICKUP) {
            //addPower();
            c.getOther().getAttachedGameObject().get().destroy();
        }
    }
    @Override
    public final Stream<Component> getAllComponents() {
        // TODO Auto-generated method stub
        return Stream.of(input, movement, collision, shoot);
    }
}
