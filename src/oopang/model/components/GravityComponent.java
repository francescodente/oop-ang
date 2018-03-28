package oopang.model.components;

import oopang.commons.space.Vector2D;
import oopang.commons.space.Vectors2D;
import oopang.model.gameobjects.GameObject;

/**
 * A component that adds gravity acceleration.
 */
public class GravityComponent extends AbstractComponent {

    private static final Vector2D DEFAULT_GRAVITY_ACC = Vectors2D.of(0, -70);

    private final Vector2D gravity;
    private MovementComponent movement;

    /**
     * Creates a new GravityComponent instance with default gravity acceleration (0, -50).
     * @param obj
     *      the game object this component is attached to.
     */
    public GravityComponent(final GameObject obj) {
        this(obj, DEFAULT_GRAVITY_ACC);
    }

    /**
     * Creates a new GravityComponent instance that has the specified gravity acceleration.
     * @param obj
     *      the game object this component is attached to.
     * @param gravity
     *      the gravity acceleration.
     */
    public GravityComponent(final GameObject obj, final Vector2D gravity) {
        super(obj);
        this.gravity = gravity;
    }

    @Override
    public void start() {
        this.movement = this.getGameObject().getComponent(MovementComponent.class).get();
    }

    @Override
    public void update(final double deltaTime) {
        final Vector2D dV = gravity.multiply(deltaTime);
        this.movement.setVelocity(this.movement.getVelocity().sumVector(dV));
    }
    
    /**
     * Gets the gravity value.
     * @return
     *      the gravity value.
     */
    public Vector2D getGravity() {
        return gravity;
    }
}
