package oopang.model.components;

import java.util.function.Consumer;

import oopang.commons.space.Vector2D;
import oopang.commons.space.Vectors2D;
import oopang.model.gameobjects.GameObject;
import oopang.model.input.InputDirection;
import oopang.model.input.InputReader;

/**
 * A component used by object can have input from user.
 */
public class InputComponent extends AbstractComponent {

    private InputReader controller;
    private final Consumer<Vector2D> velocityApplier;
    private final Runnable shootHandler;
    /**
     * Create a new Input Component for the specified GameObject.
     * @param obj
     *      the GameObject specified
     * @param velocityApplier
     *      the consumer that modify velocity.
     * @param shootHandler
     *      the runnable controlling the shoot.
     */
    public InputComponent(final GameObject obj, final Consumer<Vector2D> velocityApplier, final Runnable shootHandler) {
        super(obj);
        this.velocityApplier = velocityApplier;
        this.shootHandler = shootHandler;
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
            shootHandler.run();
        }
        velocityApplier.accept(Vectors2D.RIGHT.multiply(direction.getDirection()));
    }

    /**
     * Set the inputRader.
     * @param input
     *      The reader to set.
     */
    public void setInputReader(final InputReader input) {
        this.controller = input;
    }
}
