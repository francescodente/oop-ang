package oopang.model.powers;

import oopang.model.gameobjects.Player;

/**
 * Specific class for power that have instant activation.
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
     * It must be called in extended methods. Activte and then deactivate the power.
     */
    @Override
    public void activate(final Player player) {
        super.activate(player);
        this.deactivate();
    }

    @Override
    public void update(final double deltaTime) {
        //update is not necessary for instant powers
    }

 }


