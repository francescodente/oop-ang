package oopang.model.gameobjects;


import java.util.stream.Stream;

import oopang.commons.events.Event;
import oopang.model.components.Component;
import oopang.model.levels.Level;
import oopang.model.shooter.ShotResult;

/**
 * This class implements the GameObject Shot which is a projectile that can be shot by the player to hit the balls.
 * It can collide whit walls and balls, not with the player.
 * 
 */
public class Shot extends AbstractGameObject {

    /*
     * Speed, movement, colliding components
     */
    private final Event<ShotResult> shotResult;

    /**
     * Creates a GameObject of type Shot.
     * @param level
     *      the level this GameObject belongs to
     */
    public Shot(final Level level) {
        super(level);
        this.shotResult = new Event<>();
        // TODO Auto-generated constructor stub
    }

    @Override
    public Stream<Component> getAllComponents() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Return event reference to add handler.
     * @return
     *      Shot Result event
     */
    public Event<ShotResult> getShotResultEvent() {
        return this.shotResult;
    }


}
