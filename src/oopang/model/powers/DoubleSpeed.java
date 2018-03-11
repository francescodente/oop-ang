package oopang.model.powers;


import oopang.model.components.MovementComponent;
import oopang.model.gameobjects.Player;
/**
 * 
 *
 */
public final class DoubleSpeed extends PowerTimed {
private static final int  DOUBLESPEED = 2;
    /**
     * This constructor set time.
     * @param timeout 
     *       Is the duration of enhancements.
     */
    public DoubleSpeed(final double timeout) {
        super(timeout);

    }
    @Override
    public void activate(final Player player) {
        super.activate(player);
        player.getComponent(MovementComponent.class).ifPresent(c -> c.setVelocity(c.getVelocity().multiply(DOUBLESPEED)));
    }

    @Override
    protected void deactivate() {
        super.deactivate();
        this.getPlayer().getComponent(MovementComponent.class).ifPresent(c -> c.setVelocity(c.getVelocity()));
    }
}
