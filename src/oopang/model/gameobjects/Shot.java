package oopang.model.gameobjects;



import java.util.stream.Stream;

import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Rectangle;

import oopang.commons.events.Event;
import oopang.commons.events.EventHandler;
import oopang.commons.space.Point2D;
import oopang.commons.space.Vectors2D;
import oopang.model.components.CollisionComponent;
import oopang.model.components.Component;
import oopang.model.components.MovementComponent;
import oopang.model.physics.Collision;
import oopang.model.physics.CollisionTag;
import oopang.model.shooter.ShotResult;

/**
 * This class implements the GameObject Shot which is a projectile that can be shot by the player to hit the balls.
 * It can collide whit walls and balls, not with the player.
 * 
 */
public class Shot extends AbstractGameObject {

    private static final double WIDTH = 1;
    private static final double START_HEIGHT = 1;
    private static final double SPEED = 1;

    private final MovementComponent movementComponent;
    private final CollisionComponent collisionComponent;
    private final Event<ShotResult> shotResult;
    private final double startY;

    /**
     * Creates a GameObject of type Shot.
     * @param startPosition
     *      the Position of the player when a new Shot is created
     */
    public Shot(final Point2D startPosition) {
        super();
        this.shotResult = new Event<>();
        this.movementComponent = new MovementComponent(this);
        this.movementComponent.setVelocity(Vectors2D.UP.multiply(SPEED));

        final Convex boundingBox = new Rectangle(WIDTH, START_HEIGHT); 
        this.collisionComponent = new CollisionComponent(this, boundingBox, CollisionTag.SHOT);
        this.setPosition(startPosition);

        this.startY = startPosition.getY();
    }


    @Override
    public void start() {
        super.start();
        this.collisionComponent.registerCollisionEvent(c -> handleCollision(c));
    }

    @Override
    public void update(final double deltaTime) {
        super.update(deltaTime);
        collisionComponent.setShape(new Rectangle(WIDTH, this.getPosition().getY() - startY));
    }


    private void handleCollision(final Collision c) {
        final CollisionTag tag = c.getOther().getCollisionTag();
        if (tag == CollisionTag.BUBBLE || tag == CollisionTag.WALL) {
            shotResult.trigger(new ShotResult(tag, this));
        }
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


    @Override
    public Stream<Component> getAllComponents() {
        return Stream.of(collisionComponent, movementComponent);
    }
}
