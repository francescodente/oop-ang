package test;

import static org.junit.Assert.assertEquals;

import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Rectangle;
import org.junit.Before;
import org.junit.Test;

import oopang.commons.space.Point2D;
import oopang.commons.space.Points2D;
import oopang.commons.space.Vector2D;
import oopang.commons.space.Vectors2D;
import oopang.model.components.CollisionComponent;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.Wall;
import oopang.model.physics.Collidable;
import oopang.model.physics.CollisionManager;
import oopang.model.physics.CollisionTag;
import oopang.model.physics.SimpleCollisionManager;

/**
 * JUnit test for {@link CollisionManager} and {@link Collidable}s.
 */
public class TestCollisions {

    private static final double EPSILON = 0.0000001;
    private static final double INV_SQRT_2 = 1 / Math.sqrt(2);
    private GameObject a;
    private GameObject b;
    private CollisionManager collisionManager;
    private boolean collisionResA;
    private boolean collisionResB;

    /**
     * Initialize test data.
     */
    @Before
    public void setUp() {
        this.collisionManager = new SimpleCollisionManager();
        this.a = new Wall(1, 1);
        this.b = new Wall(1, 1);
    }

    /**
     * Test collision between circles.
     */
    @Test
    public void testCircleCircle() {
        final Convex circle1 = new Circle(1);
        final Convex circle1b = new Circle(1);
        final Convex circle2 = new Circle(2);
        this.checkCollision(circle1, Points2D.ZERO, circle1b, Points2D.ZERO, true);
        this.checkCollision(circle1, Points2D.ZERO, circle2, Points2D.ZERO, true);
        this.checkCollision(circle1, Points2D.ZERO, circle2, Points2D.of(3, 0), false);
        this.checkCollision(circle1, Points2D.ZERO, circle2, Points2D.of(0, 1), true);
        this.checkCollision(circle1, Points2D.ZERO, circle1b, Points2D.of(0, 2 - EPSILON), true);
        this.checkCollision(circle1, Points2D.ZERO, circle1b, Points2D.of(0, 2 + EPSILON), false);
        this.checkCollision(circle1, Points2D.ZERO, circle2, Points2D.of(2, 2), true);
        this.checkCollision(circle1, Points2D.ZERO, circle1b, Points2D.of(2, 2), false);
        this.checkCollision(circle2, Points2D.ZERO, circle1, Points2D.of(3 - EPSILON, 0), true);
        this.checkCollision(circle2, Points2D.ZERO, circle1, Points2D.of(3 + EPSILON, 0), false);
    }

    /**
     * Test collision between rectangles.
     */
    @Test
    public void testRectangleRectangle() {
        final Convex rectTall = new Rectangle(1, 2);
        final Convex rectLarge = new Rectangle(2, 1);
        final Convex square1 = new Rectangle(1, 1);
        final Convex square2 = new Rectangle(1, 1);
        this.checkCollision(rectTall, Points2D.ZERO, rectLarge, Points2D.ZERO, true);
        this.checkCollision(rectTall, Points2D.ZERO, square1, Points2D.ZERO, true);
        this.checkCollision(rectTall, Points2D.ZERO, rectLarge, Points2D.of(3, 0), false);
        this.checkCollision(rectTall, Points2D.ZERO, square1, Points2D.of(0, 1), true);
        this.checkCollision(rectLarge, Points2D.of(2, 1d / 2), rectTall, Points2D.of(3, 3d / 2), true);
        this.checkCollision(rectLarge, Points2D.ZERO, square1, Points2D.of(3d / 2 - EPSILON, 0), true);
        this.checkCollision(rectLarge, Points2D.ZERO, square1, Points2D.of(3d / 2 + EPSILON, 0), false);
        this.checkCollision(square1, Points2D.ZERO, square2, Points2D.of(1 - EPSILON, 1 - EPSILON), true);
        this.checkCollision(square1, Points2D.ZERO, square2, Points2D.of(1 + EPSILON, 1 + EPSILON), false);
    }

    /**
     * Test collision between circles and rectangles.
     */
    @Test
    public void testCircleRectangle() {
        final Convex rectTall = new Rectangle(1, 2);
        final Convex rectLarge = new Rectangle(2, 1);
        final Convex square = new Rectangle(1, 1);
        final Convex circle = new Circle(1);
        this.checkCollision(circle, Points2D.ZERO, square, Points2D.of(1, 1), true);
        this.checkCollision(circle, Points2D.ZERO, rectLarge, Points2D.of(2, 3d / 2), false);
        this.checkCollision(circle, Points2D.ZERO, rectTall, Points2D.of(1, 0), true);
        this.checkCollision(circle, Points2D.ZERO, rectTall, Points2D.of(3d / 2 - EPSILON, 0), true);
        this.checkCollision(circle, Points2D.ZERO, rectTall, Points2D.of(3d / 2 + EPSILON, 0), false);
    }

    /**
     * Test collision normals.
     */
    @Test
    public void testNormals() {
        final Convex square = new Rectangle(2, 2);
        final Convex circle1 = new Circle(1);
        final Convex circle2 = new Circle(1);
        this.checkNormal(circle1, Points2D.ZERO, square, Points2D.of(3d / 2, 3d / 2), Vectors2D.of(-INV_SQRT_2, -INV_SQRT_2));
        this.checkNormal(square, Points2D.of(3d / 2, 3d / 2), circle1, Points2D.ZERO, Vectors2D.of(INV_SQRT_2, INV_SQRT_2));
        this.checkNormal(circle1, Points2D.ZERO, circle2, Points2D.of(1, 1), Vectors2D.of(-INV_SQRT_2, -INV_SQRT_2));
        this.checkNormal(circle2, Points2D.of(1, 1), circle1, Points2D.ZERO, Vectors2D.of(INV_SQRT_2, INV_SQRT_2));
        this.checkNormal(circle1, Points2D.ZERO, square, Points2D.of(1, 0), Vectors2D.of(-1, 0));
        this.checkNormal(square, Points2D.of(1, 0), circle1, Points2D.ZERO, Vectors2D.of(1, 0));
        this.checkNormal(circle1, Points2D.ZERO, square, Points2D.of(3d / 2, 0), Vectors2D.of(-1, 0));
        this.checkNormal(square, Points2D.of(3d / 2, 0), circle1, Points2D.ZERO, Vectors2D.of(1, 0));
    }

    private void checkCollision(final Convex s1, final Point2D p1, final Convex s2, final Point2D p2,
            final boolean expected) {
        final Collidable c1 = new CollisionComponent(this.a, s1, CollisionTag.WALL);
        final Collidable c2 = new CollisionComponent(this.b, s2, CollisionTag.BALL);
        this.a.setPosition(p1);
        this.b.setPosition(p2);
        this.collisionManager.addCollidable(c1);
        this.collisionManager.addCollidable(c2);
        this.collisionResA = false;
        this.collisionResB = false;
        c1.registerCollisionEvent(c -> {
            this.collisionResA = true;
            assertEquals(c.getOther(), c2);
        });
        c2.registerCollisionEvent(c -> {
            this.collisionResB = true;
            assertEquals(c.getOther(), c1);
        });
        this.collisionManager.step();
        this.collisionManager.removeCollidable(c1);
        this.collisionManager.removeCollidable(c2);
        assertEquals(expected, this.collisionResA);
        assertEquals(expected, this.collisionResB);
    }

    private void checkNormal(final Convex s1, final Point2D p1, final Convex s2, final Point2D p2,
            final Vector2D expected) {
        final Collidable c1 = new CollisionComponent(this.a, s1, CollisionTag.WALL);
        final Collidable c2 = new CollisionComponent(this.b, s2, CollisionTag.BALL);
        this.a.setPosition(p1);
        this.b.setPosition(p2);
        this.collisionManager.addCollidable(c1);
        this.collisionManager.addCollidable(c2);
        this.collisionResA = false;
        this.collisionResB = false;
        c1.registerCollisionEvent(c -> assertEquals(c.getNormal(), expected));
        c2.registerCollisionEvent(c -> assertEquals(c.getNormal().multiply(-1), expected));
        this.collisionManager.step();
        this.collisionManager.removeCollidable(c1);
        this.collisionManager.removeCollidable(c2);
    }
}
