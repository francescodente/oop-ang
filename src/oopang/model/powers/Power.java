package oopang.model.powers;

import oopang.model.gameobjects.Player;
 /**
 * 
 *   This interface represents the specific enhancement of each pick-up.
 * 
 */
public interface Power {
    /**
     * Activate this enhancement effects.
     * @param player 
     *     The player on which the power is activated.
     */
    void activate(Player player);

    /**
     * Notify the time passed since last update.
     * @param deltaTime
     *      The number of seconds passed.
     */
    void update(double deltaTime);

    /**
     * Returns the enhancement active state.
     * @return
     *      the enhancement active state.
     */
    boolean isActive();
    /**
     * Return the PowerTag.
     * @return
     *      The PowerTag.
     */
    PowerTag getPowertag();

    /**
     * Deactivate the power.
     * 
     */
    void deactivate();

}
