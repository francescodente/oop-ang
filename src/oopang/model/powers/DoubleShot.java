package oopang.model.powers;

import java.util.function.Supplier;

import oopang.commons.LevelManager;
import oopang.model.components.ShooterComponent;
import oopang.model.gameobjects.Player;
import oopang.model.gameobjects.Shot;
import oopang.model.shooter.MultipleShooter;
/**
 * This enhancement allows the player to fire two shots.
 */
public final class DoubleShot extends PowerInstant {
    @Override
    public void activate(final Player player) {
        super.activate(player);
        final Supplier<Shot> supplier = () -> LevelManager.getCurrentLevel().getGameObjectFactory().createHookShot();
        player.getComponent(ShooterComponent.class).ifPresent(c -> c.setShooter(new MultipleShooter(2, player, supplier)));
    }
}
