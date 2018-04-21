package oopang.model.components;

import oopang.model.gameobjects.GameObject;
import oopang.model.shooter.Shooter;

/**
 * Give to the GameObject the ability to Shoot.
 * Uses a Shooter to create Shot.
 */
public class ShooterComponent extends AbstractComponent {

    private Shooter shooter;

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
