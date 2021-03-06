package oopang.controller.leaderboard;

import java.util.Optional;

/**
 * The Leaderboard Manager, keeps track of {@link Leaderboard}s (both survival and story mode).
 */
public interface LeaderboardManager {

    /**
     * Load the story Leaderboard.
     * @return
     *      The story Leaderboard.
     */
    Optional<Leaderboard> loadStoryModeLeaderboard();

    /**
     * Load the survival Leaderboard.
     * @return
     *      The survival Leaderboard.
     */
    Optional<Leaderboard> loadSurvivalModeLeaderboard();

    /**
     * Save the story Leaderboard.
     * @param leaderboard
     *      The Leaderboard record.
     * @return 
     *      true if the leaderboard is correctly saved
     */
    boolean saveStoryModeLeaderboardRecord(LeaderboardRecord record);

    /**
     * Save the survival Leaderboard.
     * @param record
     *      The Leaderboard record.
     * @return 
     *      true if the leaderboard is correctly saved 
     */
    boolean saveSurvivalModeLeaderboardRecord(LeaderboardRecord record);

}
