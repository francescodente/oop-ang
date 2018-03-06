package oopang.model.gameobjects;

import java.util.stream.Stream;

import org.dyn4j.geometry.Capsule;

import oopang.model.components.CollisionComponent;
import oopang.model.components.Component;
import oopang.model.components.InputComponent;
import oopang.model.components.MovementComponent;
import oopang.model.components.ShooterComponent;
import oopang.model.physics.CollisionTag;
/**
 * This class implements the player object.
 */
public class Player extends AbstractGameObject {

    private final double WIDTH = 1;
    private final double HEIGHT = 1;
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
    @Override
    public final Stream<Component> getAllComponents() {
        // TODO Auto-generated method stub
        return null;
    }
}
