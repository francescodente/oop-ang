package oopang.model.gameobjects;

import java.util.stream.Stream;

import org.dyn4j.geometry.Circle;

import oopang.commons.LevelManager;
import oopang.commons.space.Points2D;
import oopang.commons.space.Vector2D;
import oopang.commons.space.Vectors2D;
import oopang.model.BallColor;
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

    private static final double MIN_BOUNCE_HEIGHT = 18;
    private static final double SIZE_MULTIPLIER = 1;
    private static final int MIN_BALL_SIZE = 1;
    private final double gConst;

    private final GravityComponent gravity;
    private final MovementComponent movement;
    private final CollisionComponent collision;
    private final double radius;
    private final int size;
    private final BallColor color;

    /**
     * Creates the GameObject of the type Ball.
     * @param size
     *      the ball size.
     * @param vector
     *      the vector of the ball.
     * @param color
     *      The color of the ball.
     */
    public Ball(final int size, final Vector2D vector, final BallColor color) {
        super();
        this.size = size;
        this.radius = calculateRadius(size);
        this.gravity = new GravityComponent(this);
        this.gConst = -this.gravity.getGravity().getY();
        this.movement = new MovementComponent(this);
        this.movement.setVelocity(vector);
        this.collision = new CollisionComponent(this, new Circle(radius), CollisionTag.BALL);
        this.color = color;
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
        this.movement.setVelocity(Vectors2D.of(vector.getX(), this.getBounceY()));
    }

    private double getBounceY() {
        double yvalue = MIN_BOUNCE_HEIGHT;
        if (this.size != MIN_BALL_SIZE) {
            yvalue--;
        }
        return Math.sqrt(2 * size * yvalue * gConst);
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
        if (this.size > MIN_BALL_SIZE) {
            final double xmodule = Math.abs(this.movement.getVelocity().getX());
            final double deltaX = Ball.calculateRadius(this.size - 1);
            final GameObject balldx = LevelManager.getCurrentLevel().getGameObjectFactory().createBall(this.size - 1, Vectors2D.of(xmodule, gConst / 2), this.color);
            final GameObject ballsx = LevelManager.getCurrentLevel().getGameObjectFactory().createBall(this.size - 1, Vectors2D.of(-xmodule, gConst / 2), this.color);
            balldx.setPosition(this.getPosition().translate(Vectors2D.of(deltaX, 0)));
            ballsx.setPosition(this.getPosition().translate(Vectors2D.of(-deltaX, 0)));
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

    private static double calculateRadius(final int size) {
        return Math.pow(2, size) * SIZE_MULTIPLIER;
    }

    /**
     * Getter of the Ball Color.
     * @return
     *      The Ball Color.
     */
    public BallColor getColor() {
        return this.color;
    }
}
