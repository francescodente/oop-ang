package oopang.model.components;

import oopang.model.gameobjects.GameObject;
import oopang.model.shooter.Shooter;

/**
 * Give to the GameObject the ability to Shoot.
 * Uses a {@link Shooter} to create {@link oopang.model.gameobjects.Shot}s.
 */
public class ShooterComponent extends AbstractComponent {

    private Shooter shooter;

    /**
     * super.update(deltaTime) has to be called when overriding this method because
     * updates the shooter and its position.
     */
    @Override
    public void update(final double deltaTime) {
        this.shooter.update(deltaTime);
        this.shooter.setPosition(this.getGameObject().getPosition());
    }
    /**
     * Create a new ShooterComponent object.
     * @param obj
     *      the GameObject this component is linked to.
     */
    public ShooterComponent(final GameObject obj) {
        super(obj);
    }

    /**
     * Sets the shooter.
     * @param shooter
     *      new shooter to be used
     */
    public void setShooter(final Shooter shooter) {
        this.shooter = shooter;
        this.shooter.setPosition(this.getGameObject().getPosition());
    }

    /**
     * Set the state of the shooter to true o false.
     * @param state
     *      true if the shooter should shoot.
     */
    public void setState(final boolean state) {
        this.shooter.setShootingState(state);
    }

}
