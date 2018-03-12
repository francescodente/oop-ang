package oopang.model.components;

import oopang.model.gameobjects.GameObject;
import oopang.model.input.InputDirection;
import oopang.model.input.InputReader;

/**
 * A component used by object can have input from user.
 */
public class InputComponent extends AbstractComponent {
    private ShooterComponent shooter;
    private InputReader controller;
    private MovementComponent moving;
    /**
     * Create a new Input Component for the specified GameObject.
     * @param obj
     *      the GameObject specified
     */
    public InputComponent(final GameObject obj) {
        super(obj);
    }
    @Override
    public final void start() {
        this.getGameObject().getComponent(ShooterComponent.class).get();
        this.getGameObject().getComponent(MovementComponent.class).get();
    }

    @Override
    public final void update(final double deltaTime) {
        handleInput(controller.getDirection(), controller.isShooting());
    }
    /**
     * Private method to update all parameters.
     * @param direction
     *      the moving direction to update
     * @param shoot
     *      boolean representing the shooting status
     */
    private void handleInput(final InputDirection direction, final boolean shoot) {
        if (shoot) {
            shooter.tryShoot();
        }
        moving.getVelocity().multiply(direction.getDirection());
    }
}
