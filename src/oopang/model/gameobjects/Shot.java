package oopang.model.gameobjects;

import java.util.Optional;
import java.util.stream.Stream;

import oopang.model.components.Component;
import oopang.model.levels.Level;

/**
 * This class implements the GameObject Shot which is a projectile that can be shot by the player to hit the balls.
 * It can collide whit walls and balls, not with the player.
 * 
 */
public class Shot extends AbstractGameObject {

    /*
     * Speed, movement, colliding components
     */
    /**
     * Creates a GameObject of type Shot.
     */
    public Shot() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Check if the shot is colliding with something except the player itself.
     * @return
     *      An Optional containing the colliding GameObject if any
     */
    public Optional<GameObject> isCollidingWith() {
        //TODO implements with colliding component
        return Optional.empty();
    }

    @Override
    public Stream<Component> getAllComponents() {
        // TODO Auto-generated method stub
        return null;
    }


}
