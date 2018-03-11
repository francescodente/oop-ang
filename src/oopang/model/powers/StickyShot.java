package oopang.model.powers;

import java.util.function.Supplier;

import oopang.commons.LevelManager;
import oopang.model.components.ShooterComponent;
import oopang.model.gameobjects.Player;
import oopang.model.gameobjects.Shot;
import oopang.model.shooter.MultipleShooter;
/**
 * This enhancement allows the player to fire a shot that attaches to the wall.
 */
public final class StickyShot extends PowerInstant {
    @Override
    public void activate(final Player player) {
        super.activate(player);
        Supplier<Shot> supplier = () -> LevelManager.getCurrentLevel().getGameObjectFactory().createStickyShot();
        player.getComponent(ShooterComponent.class).ifPresent(c -> c.setShooter(new MultipleShooter(1, player, supplier)));
    }
 }
