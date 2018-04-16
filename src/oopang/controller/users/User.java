package oopang.controller.users;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

import oopang.commons.events.Event;
import oopang.commons.events.EventSource;
import oopang.model.powers.PowerTag;

/**
 * Class User that implements {@link Serializable}.
 */
public final class User implements Serializable {

    private static final int MAX_LEVEL = 10;
    private static final double LIMIT_MULTIPLIER = 1.5;
    private static final int MIN_XP_LIMIT = 100000;
    private static final int REWARD_LEVEL_8_9_10 = 2000;
    private static final int REWARD_LEVEL_5_6_7 = 1500;
    private static final int REWARD_LEVEL_1_2 = 100;
    private static final int REWARD_LEVEL_3_4 = 500;
    private static final int RANK_LEVEL_5 = 5;
    private static final long serialVersionUID = 6104521551950372404L;

    private final String name;
    private int coins;
    private int rank;
    private int survivalMaxStage;
    private int arcadeMaxStage;
    private int xpPoints;
    private final Map<PowerTag, Integer> powerLevels;
    private transient EventSource<Void> coinsEvent;

    /**
     * Constructor of the class User.
     * @param name
     *      the name to initialized the field.
     */
    public User(final String name) {
        this.powerLevels = Arrays.stream(PowerTag.values()).collect(Collectors.toMap(p -> p, p -> 1));
        this.coins = 0;
        this.rank = 0;
        this.setArcadeMaxStage(0);
        this.setSurvivalMaxStage(0);
        this.xpPoints = 0;
        this.name = name;
        this.coinsEvent = new EventSource<>();
    }

    /**
     * Method that get the value of the rank.
     * @return
     *      the level of the rank.
     */
    public int getRank() {
        return this.rank;
    }

    /**
     * Method that get the percentage of the XpPoints.
     * @return
     *      a double between 0 and 1.
     */
    public double getXpLevelPercentage() {
        return this.xpPoints / this.computeNextRankLimit();
    }

    /**
     * Method that return the coin event which triggers every time you spend some coins.
     * @return
     *      the coin event.
     */
    public Event<Void> getCoinsEvent() {
        return this.coinsEvent;
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
     * @param rank
     *      the value to calculate the reward.
     */
    private void addCoins() {
        if (this.rank >= 8) {
            this.coins += REWARD_LEVEL_8_9_10;
        } else if (this.rank >= RANK_LEVEL_5) {
            this.coins += REWARD_LEVEL_5_6_7;
        } else if (this.rank <= 2) {
            this.coins += this.rank * REWARD_LEVEL_1_2;
        } else {
            this.coins += (this.rank - 2) * REWARD_LEVEL_3_4;
        }
    }

    /**
     * 
     * Method that spend coins.
     * @param amount
     *      the value to spend to the field.
     * @return
     *      true if there are enough coins false otherwise.

     */
    public boolean spendCoins(final int amount) {
        if (this.coins >= amount) {
            this.coins -= amount;
            this.coinsEvent.trigger(null);
            return true; 
        } 
            return false;
    }

    /**
     * Method that get the value of the Survival max stage.
     * @return
     *      the value of the Survival max stage.
     */
    public int getSurvivalMaxStage() {
        return survivalMaxStage;
    }

    /**
     * Method that set the value of the Survival max stage.
     * @param survivalMaxStage
     *      value used to set the Survival max stage.
     */
    public void setSurvivalMaxStage(final int survivalMaxStage) {
        this.survivalMaxStage = survivalMaxStage;
    }

    /**
     * Method that get the value of the Arcade max stage.
     * @return
     *      the value of the Arcade max stage.
     */
    public int getArcadeMaxStage() {
        return arcadeMaxStage;
    }

    /**
     * Method that set the value of the Arcade max stage.
     * @param arcadeMaxStage
     *      value used to set the Arcade max stage.
     */
    public void setArcadeMaxStage(final int arcadeMaxStage) {
        this.arcadeMaxStage = arcadeMaxStage;
    }

    /**
     * Method that add the score to the xpPoints of the user.
     * @param score
     *      value to add to the current xpPoints.
     */
    public void addXpPoints(final int score) {
        if (this.rank != MAX_LEVEL) {
            this.xpPoints += score;
            this.checkRank();
        }
    }

    /**
     * Method that check if the rank is reached up with the current xpPoints.
     */
    private void checkRank() {
        final int nextRankLimit = computeNextRankLimit();
        if (this.xpPoints >= nextRankLimit) {
            this.xpPoints = this.xpPoints - nextRankLimit;
            addRank();
        }
    }

    private int computeNextRankLimit() {
        return (MIN_XP_LIMIT * ((int) (Math.pow(LIMIT_MULTIPLIER, this.rank))) * 10);
    }

    /**
     * Method that increases the current rank.
     * @return
     *      true if is possible to increase the rank false otherwise.
     */
    private boolean addRank() {
        if (this.rank < MAX_LEVEL) {
            this.rank += 1;
            addCoins();
            return true;
        }
        return false;
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
    public Map<PowerTag, Integer> getPowerLevels() {
        return new EnumMap<>(this.powerLevels);
    }

    /**
     * Method used to read the object without losing transient fields.
     * @param in
     *      input stream.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(final java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.coinsEvent = new EventSource<>();
    }
}
