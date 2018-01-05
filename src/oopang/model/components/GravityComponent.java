package oopang.model.components;

import oopang.commons.space.Vector2D;
import oopang.commons.space.Vectors2D;
import oopang.model.gameobjects.GameObject;

/**
 * A component that adds gravity acceleration.
 */
public class GravityComponent extends AbstractComponent {

    private static final Vector2D DEFAULT_GRAVITY_ACC = Vectors2D.of(0, -9.81);

    private final Vector2D gravity;

    /**
     * Creates a new GravityComponent instance with default gravity acceleration (0, -9.81).
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
        // TODO: get a reference to the VelocityComponent of this GameObject.
    }

    @Override
    public void update(final double deltaTime) {
        final Vector2D dV = gravity.multiply(deltaTime);
        this.updateVelocity(dV);
    }

    private void updateVelocity(final Vector2D dV) {
        // TODO: add dV to VelocityComponent's velocity.
    }
}
