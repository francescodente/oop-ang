package oopang.model.powers;

import oopang.model.gameobjects.Player;
/**
 * Specific class for power that have instant enable.
 */
public abstract class PowerInstant extends AbstractPower {
 /**
  * The constructor of PowerInstant.
  * @param powertag
  *         The PowerTag.
  */
    public PowerInstant(final PowerTag powertag) {
        super(powertag);
    }
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


