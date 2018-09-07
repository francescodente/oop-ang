package oopang.controller.users;

import oopang.commons.events.Event;
import oopang.model.powers.PowerTag;

/**
 * 
 * @author Francesco
 *
 */
public interface User {
    /**
     * 
     * @return
     */
    String getName();
    /**
     * 
     * @return
     */
    int getXpPoints();
    /***
     * 
     * @param points
     */
    void addXpPoints(int points);
    /**
     * 
     * @return
     */
    int getCoins();
    /**
     * 
     * @param amount
     * @return
     */
    void changeCoins(int amount);
    /**
     * 
     * @param stat
     * @return
     */
    int getStatValue(UserStat stat);
    /**
     * 
     * @param stat
     * @param value
     */
    void setStatValue(UserStat stat, int value);
    /**
     * 
     * @param power
     * @param level
     */
    void setPowerLevel(PowerTag power, int level);
    /**
     * 
     * @param power
     * @return
     */
    int getPowerLevel(PowerTag power);
    /**
     * 
     * @return
     */
    Event<User> getUserChangedEvent();
}
