package oopang.controller.leaderboard;

import java.io.Serializable;
/**
 * This class keeps track of the user Statistics.
 */
public class LeaderboardRecord implements Serializable, Comparable<LeaderboardRecord> {
    private static final long serialVersionUID = -4374863784204719291L;
    private final String name;
    private final int score;
    private final int stage;
    /**
     * 
     * @param name
     *      The name of the User.
     * @param score
     *      The score of the User.
     * @param stage
     *      The Stage of the User.
     */
    public LeaderboardRecord(final String name, final int score, final int stage) {
        this.name = name;
        this.stage = stage;
        this.score = score;
    }
    /**
     * Getter of the User name.
     * @return
     *      The User name.
     */
    public String getName() {
        return name;
    }
    /**
     * Getter of the score of the User.
     * @return
     *      The User score.
     */
    public int getScore() {
        return score;
    }
    /**
     * Getter of the User stage. 
     * @return
     *      The User stage.
     */
    public int getStage() {
        return stage;
    }

    @Override
    public int compareTo(final LeaderboardRecord o) {
        int scoreComparison = Integer.compare(this.score, o.score);
        return scoreComparison == 0 ? Integer.compare(this.stage, o.stage) : scoreComparison;
    }
}
