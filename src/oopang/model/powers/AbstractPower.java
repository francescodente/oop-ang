package oopang.model.powers;

import oopang.model.gameobjects.Player;
/**
 * This is abstract class of the enhancement.
 *
 */
public abstract class AbstractPower implements Power {
    private Player player;
    private boolean active;
    private final PowerTag powertag;
    /**
     * This constructor set the active field on true.
     * @param powertag
     *      The PowerTag.
     */
    public AbstractPower(final PowerTag powertag) {
        this.powertag = powertag;
        this.active = true;
    }

    /**
     * It must be called in extended methods.
     */
    @Override
    public void activate(final Player player) {
        this.player = player;
        this.active = true;
    }

    /**
     * It must be called in extended methods.
     */
    @Override
    public boolean isActive() {
        return this.active;
    }

    /**
     * Return the player.
     * @return
     *      Player reference.
     */
    protected Player getPlayer() {
        return player;
    }

    /**
     * Deactivate the power.
     * 
     */
    protected void deactivate() {
        this.active = false;
    }
    /**
     * Return the PowerTag.
     * @return
     *      The PowerTag.
     */
    public PowerTag getPowertag() {
        return powertag;
    }
}
