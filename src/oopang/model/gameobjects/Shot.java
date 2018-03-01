package oopang.model.gameobjects;


import java.util.stream.Stream;

import oopang.commons.events.Event;
import oopang.commons.events.EventHandler;
import oopang.model.components.CollisionComponent;
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
     * Speed, movement
     */
    //private final CollisionComponent collisionComponent;
    private final Event<ShotResult> shotResult;

    /**
     * Creates a GameObject of type Shot.
     */
    public Shot() {
        super();
        this.shotResult = new Event<>();
        // TODO Auto-generated constructor stub
    }

    @Override
    public Stream<Component> getAllComponents() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void start() {
        super.start();
        //this.collisionComponent.registerCollisionEvent( TODO handler which triggers shotResult )
        // handler call super.destroy
    }

    /**
     * Register to event.
     * @param handler
     *      the handler to be registered.
     */
    public void registerShotResultEvent(final EventHandler<ShotResult> handler) {
        this.shotResult.registerHandler(handler);
    }

    /**
     * Unregister to event.
     * @param handler
     *      the handler to be unregistered.
     */
    public void unregisterShotResultEvent(final EventHandler<ShotResult> handler) {
        this.shotResult.unregisterHandler(handler);
    }

}
