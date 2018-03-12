package oopang.model.physics;

import java.util.ArrayList;
import java.util.List;

import org.dyn4j.collision.narrowphase.Gjk;
import org.dyn4j.collision.narrowphase.NarrowphaseDetector;
import org.dyn4j.collision.narrowphase.Penetration;
import org.dyn4j.geometry.Transform;

import oopang.commons.space.Vector2D;
import oopang.commons.space.Vectors2D;
import oopang.model.components.CollisionComponent;

/**
 * Basic implementation of the {@link CollisionManager} interface. This implementation looks
 * at each possible pair of {@link Collidable}s and notifies both of them if they are colliding.
 * This class uses the dyn4j library to detect collisions.
 */
public class SimpleCollisionManager implements CollisionManager {

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
            for (int j = i + 1; i < this.collidables.size(); j++) {
                final Collidable c1 = this.collidables.get(i);
                final Collidable c2 = this.collidables.get(j);
                if (!c1.equals(c2)) {
                    final Transform t1 = getTransform(c1);
                    final Transform t2 = getTransform(c2);
                    final Penetration p = new Penetration();
                    if (this.narrowPhase.detect(c1.getShape(), t1, c2.getShape(), t2, p)) {
                        final Vector2D normal = Vectors2D.of(p.getNormal().x, p.getNormal().y)
                                .multiply(p.getDepth());
                        c1.notifyCollision(new Collision(c2, normal.multiply(-1)));
                        c2.notifyCollision(new Collision(c1, normal));
                    }
                }
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

    private static Transform getTransform(final Collidable coll) {
        final Transform t = new Transform();
        t.setTranslation(coll.getPosition().getX(), coll.getPosition().getY());
        return t;
    }
}
