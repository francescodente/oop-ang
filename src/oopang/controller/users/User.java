package oopang.controller.users;

import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

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
     * Method that spend coins.
     * @param amount
     *      the value to spend to the field.
     */
    public void spendCoins(final int amount) {
        this.coins -= amount;
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
        this.xpPoints += score;
    }

    /**
     * Method that check if the rank is reached up with the current xpPoints.
     */
    private void checkRank() {
        int nextRankLimit = (MIN_XP_LIMIT * ((int) (Math.pow(LIMIT_MULTIPLIER, this.rank)) / 10) * 10);
        if (this.xpPoints >= nextRankLimit) {
            this.xpPoints = this.xpPoints - nextRankLimit;
            addRank();
        }
    }

    /**
     * Method that increases the current rank.
     */
    private void addRank() {
        this.rank += 1;
        addCoins();
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

}
