package oopang.model.powers;

import oopang.model.gameobjects.Player;

/**
 * This power creates a shield around the player and sets him invulnerable.
 */
public final class TimedShield extends TimedPower {

    private static final PowerTag TAG = PowerTag.TIMEDSHIELD;
    private static final int INITIALVALUE = 4;
    private static final double TIMEFEE = 0.5;

    /**
     * This constructor set time.
     * @param timeout 
     *       Is the duration of enhancements.
     */
    private TimedShield(final double timeout) {
        super(timeout, TAG);
    }

    @Override
    public void activate(final Player player) {
        super.activate(player);
        player.setInvulnerable(true);
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.getPlayer().setInvulnerable(false);
    }
    private static double calculateTimeout(final int powerlevel) {
        return INITIALVALUE + (TIMEFEE * (powerlevel - 1));
    }

    /**
     * This method return the power upgrade based on level.
     * @param powerlevel
     *      The power level.
     * @return 
     *      The freeze power.
     */
    public static Power create(final int powerlevel) {
       return  new TimedShield(calculateTimeout(powerlevel));
    }

}
