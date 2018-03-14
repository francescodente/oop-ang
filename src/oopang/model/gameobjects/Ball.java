package oopang.model.gameobjects;

import java.util.stream.Stream;

import org.dyn4j.geometry.Circle;

import oopang.commons.LevelManager;
import oopang.commons.space.Vector2D;
import oopang.commons.space.Vectors2D;
import oopang.model.components.CollisionComponent;
import oopang.model.components.Component;
import oopang.model.components.GravityComponent;
import oopang.model.components.MovementComponent;
import oopang.model.physics.Collision;
import oopang.model.physics.CollisionTag;

/**
 * This class implements the GameObject Ball 
 * It can collide whit walls.
 *
 */
public class Ball extends AbstractGameObject {

    private static final double BOUNCE_SPEED = 1;
    private static final double MINRADIUS_BALL = 1;
    private static final Vector2D VECTORDX = Vectors2D.of(1, 1);
    private static final Vector2D VECTORSX = Vectors2D.of(-1, 1);

    private final GravityComponent gravity;
    private final MovementComponent movement;
    private final CollisionComponent collision;
    private final double radius;

    /**
     * Creates the GameObject of the type Ball.
     * @param radius
     *      the ball radius.
     * @param vector
     *      the vector of the ball.
     */
    public Ball(final double radius, final Vector2D vector) {
        super();
        this.radius = radius;
        this.gravity = new GravityComponent(this);
        this.movement = new MovementComponent(this);
        this.movement.setVelocity(vector);
        this.collision = new CollisionComponent(this, new Circle(radius), CollisionTag.BALL);
    }

    @Override
    public void start() {
        super.start();
        this.collision.registerCollisionEvent(c -> handleCollision(c));
    }

    @Override
    public Stream<Component> getAllComponents() {
        return Stream.of(this.gravity, this.movement, this.collision);
    }

    /**
     * Method that call other utility methods based on the type of collision that is verified.
     * @param coll
     *      the object Collision of the event.
     */
    private void handleCollision(final Collision coll) {
        if (coll.getOther().getCollisionTag() == CollisionTag.WALL) {
            final Vector2D normal = Vectors2D.getNearestPerpendicularVector(coll.getNormal());
            if (Math.abs(normal.getX()) > Math.abs(normal.getY())) {
                this.reverseHorizontalDirection();
            } else {
                final Vector2D gravityNormalized = Vectors2D.getNearestPerpendicularVector(gravity.getGravity());
                if (gravityNormalized.equals(normal.multiply(-1))) {
                    this.bounce();
                } else {
                    this.reverseVerticalDirection();
                }
            }
        }
        if (coll.getOther().getCollisionTag() == CollisionTag.SHOT) {
            generate();
        }
    }

    /**
     * Method that make the ball bounce against the floor.
     */
    private void bounce() {
        final Vector2D vector = this.movement.getVelocity();
        this.movement.setVelocity(Vectors2D.of(vector.getX(), BOUNCE_SPEED * radius));
    }

    /**
     * Method that change the horizontal component of the vector.
     */
    private void reverseHorizontalDirection() {
        final Vector2D vector = this.movement.getVelocity();
        this.movement.setVelocity(vector.flipX());
    }

     /**
     * Method that change the vertical component of the vector.
     */
    private void reverseVerticalDirection() {
        final Vector2D vector = this.movement.getVelocity();
        this.movement.setVelocity(vector.flipY());
    }

    /**
     * Method that generate two new ball if the radius is greater than the minimum radius and finally call destroy.
     */
    private void generate() {
        if (this.radius > MINRADIUS_BALL) {
            LevelManager.getCurrentLevel().getGameObjectFactory().createBall(radius / 2, VECTORDX);
            LevelManager.getCurrentLevel().getGameObjectFactory().createBall(radius / 2, VECTORSX);
        }
        this.destroy();
    }

    @Override
    public double getWidth() {
        return this.radius * 2;
    }

    @Override
    public double getHeight() {
        return this.radius * 2;
    }

}
