package oopang.model.components;

import oopang.commons.space.Point2D;
import oopang.commons.space.Vector2D;
import oopang.commons.space.Vectors2D;
import oopang.model.gameobjects.GameObject;

/**
 * 
 * A component that updates the position based on the velocity.
 *
 */
public final class MovementComponent extends AbstractComponent {

    private Vector2D velocity;

    /**
     * Create a new MovementComponent with velocity (0,0).
     * @param obj
     *      the game object this component is attached to.
     */
    public MovementComponent(final GameObject obj) {
        super(obj);
        this.velocity = Vectors2D.ZERO;
    }

    @Override
    public void update(final double deltaTime) {
        Point2D position = this.getGameObject().getPosition();
        position = position.translate(this.velocity.multiply(deltaTime));
        this.getGameObject().setPosition(position);
    }
    /**
     * Gets the velocity of the Object.
     * @return
     *      the velocity.
     */
    public Vector2D getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the velocity of the Object.
     * @param velocity
     *      the new velocity of the Object.
     */
    public void setVelocity(final Vector2D velocity) {
        this.velocity = velocity;
    }

}
