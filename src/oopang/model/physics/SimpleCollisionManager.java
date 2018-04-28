package oopang.model.physics;

import java.util.ArrayList;
import java.util.List;

import org.dyn4j.collision.narrowphase.Gjk;
import org.dyn4j.collision.narrowphase.NarrowphaseDetector;
import org.dyn4j.collision.narrowphase.Penetration;
import org.dyn4j.geometry.Transform;

import oopang.commons.space.Vector2D;
import oopang.commons.space.Vectors2D;

/**
 * Basic implementation of the {@link CollisionManager} interface. This implementation looks
 * at each possible pair of {@link Collidable}s and notifies both of them if they are colliding.
 * This class uses the dyn4j library to detect collisions.
 */
public final class SimpleCollisionManager implements CollisionManager {

    private final NarrowphaseDetector narrowPhase;
    private final List<Collidable> collidables;

    /**
     * Creates a new SimpleCollisionManager instance.
     */
    public SimpleCollisionManager() {
        this.narrowPhase = new Gjk();
        this.collidables = new ArrayList<>();
    }

    @Override
    public void step() {
        for (int i = 0; i < this.collidables.size(); i++) {
            for (int j = i + 1; j < this.collidables.size(); j++) {
                final Collidable c1 = this.collidables.get(i);
                final Collidable c2 = this.collidables.get(j);
                this.checkCollidablePair(c1, c2);
            }
        }
    }

    @Override
    public void addCollidable(final Collidable coll) {
        this.collidables.add(coll);
    }

    @Override
    public void removeCollidable(final Collidable coll) {
        this.collidables.remove(coll);
    }

    private void checkCollidablePair(final Collidable c1, final Collidable c2) {
        final CollisionTag tag1 = c1.getCollisionTag();
        final CollisionTag tag2 = c2.getCollisionTag();
        if (tag1.canCollideWith(tag2)) {
            final Transform t1 = getTransform(c1);
            final Transform t2 = getTransform(c2);
            final Penetration p = new Penetration();
            if (c1.getShape().createAABB().overlaps(c2.getShape().createAABB())
                    && this.narrowPhase.detect(c1.getShape(), t1, c2.getShape(), t2, p)) {
                final Vector2D normal = Vectors2D.of(p.getNormal().x, p.getNormal().y).normalized();
                double depth = p.getDepth();
                if (!tag1.isStaticWith(tag2) && !tag2.isStaticWith(tag1)) {
                    depth /= 2;
                }
                if (!tag1.isStaticWith(tag2)) {
                    c1.translate(normal.multiply(-depth));
                }
                if (!tag2.isStaticWith(tag1)) {
                    c2.translate(normal.multiply(depth));
                }
                c1.notifyCollision(new Collision(c2, normal.multiply(-1)));
                c2.notifyCollision(new Collision(c1, normal));
            }
        }
    }

    private static Transform getTransform(final Collidable coll) {
        final Transform t = new Transform();
        t.setTranslation(coll.getPosition().getX(), coll.getPosition().getY());
        return t;
    }

}
