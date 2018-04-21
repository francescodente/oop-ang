package oopang.model.powers;

import java.util.function.Supplier;

import oopang.model.components.ShooterComponent;
import oopang.model.gameobjects.Player;
import oopang.model.gameobjects.Shot;
import oopang.model.levels.LevelManager;
import oopang.model.shooter.MultipleShooter;
/**
 * This enhancement allows the player to fire a shot that attaches to the wall.
 */
public final class AdhesiveShot extends PowerInstant {
    private static final PowerTag TAG = PowerTag.ADHESIVESHOT;
    private final int shot;
    /**
     * Constructor AdhesiveShot.
     * @param shot
     *      The number of shot.
     */
    public AdhesiveShot(final int shot) {
        super(TAG);
        this.shot = shot;
    }

    @Override
    public void activate(final Player player) {
        super.activate(player);
        final Supplier<Shot> supplier = () -> 
            LevelManager.getCurrentLevel().getGameObjectFactory().createStickyShot();
        player.getComponent(ShooterComponent.class).ifPresent(c -> 
            c.setShooter(new MultipleShooter(shot, supplier)));
    }
    /**
     * This method return the power upgrade based on level.
     * @param powerlevel
     *      The PowerLevel.
     * @return
     *      The AdhesiveShot.
     */
        public static Power create(final int powerlevel) {
            return  new AdhesiveShot(powerlevel);
         }
 }
