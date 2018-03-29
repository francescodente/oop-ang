package oopang.model.components;

import java.util.Optional;

import org.dyn4j.geometry.Convex;

import oopang.commons.LevelManager;
import oopang.commons.events.Event;
import oopang.commons.events.EventHandler;
import oopang.commons.space.Point2D;
import oopang.commons.space.Vector2D;
import oopang.model.gameobjects.GameObject;
import oopang.model.physics.Collidable;
import oopang.model.physics.Collision;
import oopang.model.physics.CollisionManager;
import oopang.model.physics.CollisionTag;

/**
 * A component that makes the {@link GameObject} it is attached to able to collide with other objects.
 */
public final class CollisionComponent extends AbstractComponent implements Collidable {

    private Convex boundingBox;
    private final Event<Collision> collisionEvent;
    private final CollisionTag collisionTag;

    /**
     * Creates a new Collision component with the given bounding box.
     * @param obj
     *      the game object this component is attached to.
     * @param boundingBox
     *      the convex shape of the object.
     * @param tag
     *      the {@link CollisionTag} for this object.
     */
    public CollisionComponent(final GameObject obj, final Convex boundingBox, final CollisionTag tag) {
        super(obj);
        this.boundingBox = boundingBox;
        this.collisionEvent = new Event<>();
        this.collisionTag = tag;
    }

    @Override
    public void start() {
        final CollisionManager manager = LevelManager.getCurrentLevel().getCollisionManager();
        manager.addCollidable(this);
        this.getGameObject().registerDestroyedEvent(o -> manager.removeCollidable(this));
    }

    @Override
    public Convex getShape() {
        return this.boundingBox;
    }

    /**
     * Set the boundingBox to a new Shape.
     * @param shape
     *      the new shape to be used
     */
    public void setShape(final Convex shape) {
        this.boundingBox = shape;
    }

    @Override
    public Point2D getPosition() {
       return this.getGameObject().getPosition();
    }

    @Override
    public void notifyCollision(final Collision coll) {
        if (this.isEnabled()) {
            this.collisionEvent.trigger(coll);
        }
    }

    @Override
    public void registerCollisionEvent(final EventHandler<Collision> handler) {
        this.collisionEvent.registerHandler(handler);
    }

    @Override
    public void unregisterCollisionEvent(final EventHandler<Collision> handler) {
        this.collisionEvent.unregisterHandler(handler);
    }

    @Override
    public CollisionTag getCollisionTag() {
        return this.collisionTag;
    }

    @Override
    public Optional<GameObject> getAttachedGameObject() {
        return Optional.of(this.getGameObject());
    }

    @Override
    public void translate(final Vector2D offset) {
        this.getGameObject().setPosition(this.getPosition().translate(offset));
    }
}
