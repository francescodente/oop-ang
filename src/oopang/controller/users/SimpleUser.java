package oopang.controller.users;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

import oopang.commons.events.Event;
import oopang.commons.events.EventSource;
import oopang.model.powers.PowerTag;

/**
 * This class represent the concept of User and contains the logic to level up,
 * gaining rewards and upgrade power levels.
 */
public class SimpleUser implements User, Serializable {
    private static final long serialVersionUID = -3367060662398059536L;

    private final String name;
    private int coins;
    private int xpPoints;
    private final Map<PowerTag, Integer> powerLevels;
    private final Map<UserStat, Integer> userStats;
    private transient Event<User> userChangedEvent;

    /**
     * Constructor of the class User.
     * 
     * @param name
     *            the name to initialized the field.
     */
    public SimpleUser(final String name) {
        this(name, 0, 0, Users.defaultPowerLevels(), Users.defaultUserStats());
    }

    /**
     * 
     * @param name
     * @param coins
     * @param xpPoints
     * @param powerLevels
     * @param userStats
     */
    public SimpleUser(final String name, final int coins, final int xpPoints, final Map<PowerTag, Integer> powerLevels,
            final Map<UserStat, Integer> userStats) {
        this.coins = coins;
        this.xpPoints = xpPoints;
        this.name = name;
        this.powerLevels = powerLevels;
        this.userStats = userStats;
        this.userChangedEvent = new EventSource<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getXpPoints() {
        return this.xpPoints;
    }

    @Override
    public void addXpPoints(final int points) {
        this.xpPoints += points;
    }

    @Override
    public int getCoins() {
        return this.coins;
    }

    @Override
    public void changeCoins(final int amount) {
        this.coins += amount;
    }

    @Override
    public int getStatValue(final UserStat stat) {
        if (this.userStats.containsKey(stat)) {
            return this.userStats.get(stat);
        }
        return stat.getDefaultValue();
    }

    @Override
    public void setStatValue(final UserStat stat, final int value) {
        this.userStats.put(stat, value);
    }

    @Override
    public void setPowerLevel(final PowerTag power, final int level) {
        this.powerLevels.put(power, level);
    }

    @Override
    public int getPowerLevel(final PowerTag power) {
        if (this.powerLevels.containsKey(power)) {
            return this.powerLevels.get(power);
        }
        return 1;
    }

    @Override
    public Event<User> getUserChangedEvent() {
        return this.userChangedEvent;
    }

    /**
     * Method used to read the object without losing transient fields.
     * @param in
     *      input stream.
     * @throws IOException
     *      if a problem occurred during I/O operations.
     * @throws ClassNotFoundException
     *      if the class is not present.
     */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.userChangedEvent = new EventSource<>();
    }

    /**
     * Method used to write the object on an output stream.
     * @param out
     *      output stream.
     * @throws IOException
     *      if a problem occurred during I/O operations.
     */
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }
}
