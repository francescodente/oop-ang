package oopang.controller.leaderboard;
/**
 * The Leaderboard Manager, keeps track of Leaderboards (Survival and Story mode).
 */
public interface LeaderboardManager {
    /**
     * Load the story Leaderboard.
     * @return
     *      The story Leaderboard.
     */
    Leaderboard loadStoryModeLeaderboard();
    /**
     * Load the survival Leaderboard.
     * @return
     *      The survival Leaderboard.
     */
    Leaderboard loadSurvivalModeLeaderboard();
    /**
     * Save the story Leaderboard.
     * @param leaderboard
     *      The Leaderboard.
     */
    void saveStoryModeLeaderboard(Leaderboard leaderboard);
    /**
     * Save the survival Leaderboard.
     * @param leaderboard
     *      The Leaderboard.
     */
    void saveSurvivalModeLeaderboard(Leaderboard leaderboard);
}
