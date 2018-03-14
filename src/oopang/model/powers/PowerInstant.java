package oopang.model.powers;

import oopang.model.gameobjects.Player;
/**
 * 
 * 
 *
 */
public abstract class PowerInstant extends AbstractPower {
 
    /**
     * It must be called in extended methods.
     */
    @Override
    public void activate(final Player player) {
        super.activate(player);
        this.deactivate();
    }
    @Override
    public void update(final double deltaTime) {
    }

 }


