package oopang.model.components;

import oopang.model.gameobjects.GameObject;
import oopang.model.shooter.Shooter;

/**
 * Give to the GameObject the ability to Shoot.
 * Uses a Shooter to create Shot.
 */
public class ShooterComponent extends AbstractComponent {

    private Shooter shooter;

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
     * Asks the shooter to shoot. It's not sure that canShoot is enabled.
     */
    public void tryShoot() {
        this.shooter.shoot();
    }

}
