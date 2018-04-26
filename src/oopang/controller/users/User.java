package oopang.controller.users;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import oopang.commons.events.Event;
import oopang.commons.events.EventSource;
import oopang.model.powers.PowerTag;

/**
 * Class User that implements {@link Serializable}.
 */
public final class User implements Serializable {

    private static final long serialVersionUID = -3367060662398059536L;
    private static final int MAX_LEVEL = 10;
    private static final double LIMIT_MULTIPLIER = 1.8;
    private static final int MIN_XP_LIMIT = 100000;
    private static final List<Integer> LEVELS_REWARD = Arrays.asList(100, 100, 500, 500, 1500, 1500, 1500, 2000, 2500, 3000); 

    private final String name;
    private int coins;
    private int rank;
    private int survivalMaxStage;
    private int arcadeMaxStage;
    private int survivalMaxScore;
    private int arcadeMaxScore;
    private int xpPoints;
    private final Map<PowerTag, Integer> powerLevels;
    private transient EventSource<Void> userModifiedEvent;

    /**
     * Constructor of the class User.
     * @param name
     *      the name to initialized the field.
     */
    public User(final String name) {
        this.powerLevels = Arrays.stream(PowerTag.values()).collect(Collectors.toMap(p -> p, p -> 1));
        this.coins = 0;
        this.rank = 0;
        this.survivalMaxStage = 0;
        this.arcadeMaxStage = 0;
        this.survivalMaxScore = 0;
        this.arcadeMaxScore = 0;
        this.xpPoints = 0;
        this.name = name;
        this.userModifiedEvent = new EventSource<>();
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
     * Returns the xpPoints.
     * @return
     *      the xpPoints
     */
    public int getXpPoints() {
        return this.xpPoints;
    }

    /**
     * Method that get the percentage of the XpPoints.
     * @return
     *      a double between 0 and 1.
     */
    public double getXpLevelPercentage() {
        return this.xpPoints / (double) this.computeNextRankLimit();
    }

    /**
     * Method that return the coin event which triggers every time you spend some coins.
     * @return
     *      the coin event.
     */
    public Event<Void> getUserModifiedEvent() {
        return this.userModifiedEvent;
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
        this.coins += LEVELS_REWARD.get(this.rank);
        this.userModifiedEvent.trigger(null);
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
            this.userModifiedEvent.trigger(null);
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
        if (survivalMaxStage > this.survivalMaxStage) {
            this.survivalMaxStage = survivalMaxStage;
            this.userModifiedEvent.trigger(null);
        }
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
        if (arcadeMaxStage > this.arcadeMaxStage) {
            this.arcadeMaxStage = arcadeMaxStage;
            this.userModifiedEvent.trigger(null);
        }
    }

    /**
     * Method that get the value of the Survival max score.
     * @return
     *      the value of the Survival max score.
     */
    public int getSurvivalMaxScore() {
        return this.survivalMaxScore;
    }
    /**
     * Method that set the value of the Survival max score.
     * @param survivalMaxScore
     *      value used to set the Survival max score.
     */

    public void setSurvivalMaxScore(final int survivalMaxScore) {
        if (survivalMaxScore > this.survivalMaxScore) {
            this.survivalMaxScore = survivalMaxScore;
            this.userModifiedEvent.trigger(null);
        }
    }

    /**
     * Method that get the value of the Arcade max score.
     * @return
     *      the value of the Arcade max score.
     */
    public int getArcadeMaxScore() {
        return this.arcadeMaxScore;
    }

    /**
     * Method that set the value of the Arcade max score.
     * @param arcadeMaxScore
     *      value used to set the Arcade max score.
     */
    public void setArcadeMaxScore(final int arcadeMaxScore) {
        if (arcadeMaxScore > this.arcadeMaxScore) {
            this.arcadeMaxScore = arcadeMaxScore;
            this.userModifiedEvent.trigger(null);
        }
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
        this.userModifiedEvent.trigger(null);
    }

    /**
     * Method that check if the rank is reached up with the current xpPoints.
     */
    private void checkRank() {
        int nextRankLimit = computeNextRankLimit();
        while (this.xpPoints >= nextRankLimit) {
            this.xpPoints = this.xpPoints - nextRankLimit;
            this.userModifiedEvent.trigger(null);
            addRank();
            nextRankLimit = computeNextRankLimit();
        }
    }

    private int computeNextRankLimit() {
        return MIN_XP_LIMIT * (int) Math.pow(LIMIT_MULTIPLIER, this.rank);
    }

    /**
     * Method that increases the current rank.
     * @return
     *      true if is possible to increase the rank false otherwise.
     */
    private boolean addRank() {
        if (this.rank < MAX_LEVEL) {
            addCoins();
            this.rank += 1;
            this.userModifiedEvent.trigger(null);

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
        this.userModifiedEvent.trigger(null);
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
     *      if a problem occurred during I/O operations.
     * @throws ClassNotFoundException
     *      if the class is not present.
     */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.userModifiedEvent = new EventSource<>();
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
