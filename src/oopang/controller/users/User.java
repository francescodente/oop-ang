package oopang.controller.users;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import oopang.model.powers.PowerTag;

/**
 * Class User that implements {@link Serializable}.
 */
public class User implements Serializable {

    private final String name;
    private int coins;
    private final Map<PowerTag, Integer> powerLevels;

    /**
     * Constructor of the class User.
     * @param name
     *      the name to initialized the field.
     */
    public User(final String name) {
        this.powerLevels = new HashMap<>();
        this.coins = 0;
        this.name = name;
    }

    /**
     * Method that get the name of the User.
     * @return
     *      the name of the user.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method that get coins of the User.
     * @return
     *      the value of the coins.
     */
    public int getCoins() {
        return this.coins;
    }

    /**
     * Method that add coins.
     * @param amount
     *      the value to add to the field.
     */
    public void addCoins(final int amount) {
        this.coins += amount;
    }

    /**
     * Method that spend coins.
     * @param amount
     *      the value to spend to the field.
     */
    public void spendCoins(final int amount) {
        this.coins -= amount;
    }

    /**
     * Method that set the PowerLevel.
     * @param powerTag
     *      the powerTag to set the key of the map.
     * @param level
     *      the Integer to set the value of the map.
     */
    public void setPowerLevel(final PowerTag powerTag, final Integer level) {
        this.powerLevels.put(powerTag, level);
    }

    /**
     * Method that get the powerLevels.
     * @return
     *      defensive copy of the map.
     */
    public Map<PowerTag, Integer> getPowerLevels(){
        return new EnumMap<>(this.powerLevels);
    }
}
