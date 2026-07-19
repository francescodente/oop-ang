package oopang.model.powers;

import java.util.function.Supplier;

import oopang.model.components.ShooterComponent;
import oopang.model.gameobjects.Player;
import oopang.model.gameobjects.Shot;
import oopang.model.levels.LevelManager;
import oopang.model.shooter.MultipleShooter;

/**
 * This power allows the player to fire two shots at the same time.
 */
public final class DoubleShot extends PowerInstant {

    private static final PowerTag TAG = PowerTag.DOUBLESHOT;

    private final int shot;

    /**
     * Constructor DoubleShot.
     * @param shot
     *      The number of Shots.
     */
    public DoubleShot(final int shot) {
        super(TAG);
        this.shot = shot;

    }
    @Override
    public void activate(final Player player) {
        super.activate(player);
        final Supplier<Shot> supplier = () -> 
            LevelManager.getCurrentLevel().getGameObjectFactory().createHookShot();
        player.getComponent(ShooterComponent.class).ifPresent(c ->
            c.setShooter(new MultipleShooter(shot, supplier)));
    }

    /**
     * This method return the power upgrade based on level.
     * @param powerlevel
     *      The PowerLevel.
     * @return
     *      The Shot.
     */
    public static Power create(final int powerlevel) {
        return  new DoubleShot(powerlevel + 1);
     }

 }
