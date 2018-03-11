package oopang.model.powers;

import oopang.model.components.CollisionComponent;
import oopang.model.gameobjects.Player;
/**
 * This enhancements creates a shield around the player, where the balls can not hit it, disabling the collision component.
 *
 */
public final class TimedShield extends PowerTimed {

    /**
     * This constructor set time.
     * @param timeout 
     *       Is the duration of enhancements.
     */
    public TimedShield(final double timeout) {
        super(timeout);
    }

    @Override
    public void activate(final Player player) {
        super.activate(player);
        player.getComponent(CollisionComponent.class).ifPresent(c -> c.disable());
    }

    @Override
    protected void deactivate() {
        super.deactivate();
        this.getPlayer().getComponent(CollisionComponent.class).ifPresent(c -> c.enable());
    }

}
