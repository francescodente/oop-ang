package oopang.model.powers;

import oopang.model.gameobjects.Player;

/**
 * This is abstract class for a Power. Overriding the activate() method it can do different things to
 * help the player.
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

    @Override
    public final boolean isActive() {
        return this.active;
    }

    /**
     * Return the player.
     * @return
     *      Player reference.
     */
    protected final Player getPlayer() {
        return player;
    }

    /**
     * Sets the state of this Power to disabled.
     */
    @Override
    public void deactivate() {
        this.active = false;
    }

    /**
     * Return the PowerTag.
     * @return
     *      The PowerTag.
     */
    public final PowerTag getPowertag() {
        return powertag;
    }

}
