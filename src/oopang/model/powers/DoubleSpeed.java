package oopang.model.powers;

import oopang.model.gameobjects.Player;
/**
 * 
 *
 */
public final class DoubleSpeed extends TimedPower {
    private static final int DOUBLE = 2;
    private static final PowerTag TAG = PowerTag.DOUBLESPEED;
    private static final int INITIALVALUE = 4;
    private static final double TIMEFEE = 0.5;
    /**
     * This constructor set time.
     * @param timeout 
     *       Is the duration of enhancements.
     */
    public DoubleSpeed(final double timeout) {
        super(timeout, TAG);

    }
    @Override
    public void activate(final Player player) {
        super.activate(player);
        player.setSpeed(player.getSpeed() * DOUBLE);
    }

    @Override
    protected void deactivate() {
        super.deactivate();
        this.getPlayer().setSpeed(this.getPlayer().getSpeed() / DOUBLE);
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
       return  new DoubleSpeed(calculateTimeout(powerlevel));
    }
}
