package oopang.controller.leaderboard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import oopang.controller.database.DatabaseManager;

public class OnlineLeaderboardManager implements LeaderboardManager {

    private static final String STORY_MODE_TABLE_NAME = "StoryModeLeaderboard";
    private static final String SURVIVAL_MODE_TABLE_NAME = "SurvivalModeLeaderBoard";
    private final DatabaseManager manager;

    public OnlineLeaderboardManager() {
        this.manager = new DatabaseManager();
    }

    private Optional<Leaderboard> load(final String tableName) {
        try {
            this.manager.createConnection();
            final String query = "SELECT TOP(" + 10 + ") * " + 
                                 "FROM " + tableName + " " +
                                 "ORDER BY Score DESC";
            final Leaderboard leaderboard = new Leaderboard();
            this.manager.getRecordsFromQuery(query, r -> {
                try {
                    leaderboard.addRecord(
                            new LeaderboardRecord(
                            r.getString("Name"),
                            r.getInt("Score"),
                            r.getInt("Stage")));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            return Optional.of(leaderboard);
        } catch (SQLException e) {
            return Optional.empty();
        } finally {
            this.manager.closeConnection();
        }
    }
    
    private boolean save(final LeaderboardRecord record, final String tableName) {
        try {
            this.manager.createConnection();
            final String query = "INSERT INTO " + tableName + " (Name, Score, Stage) " +
                    "VALUES ('" + record.getName() + "', " + record.getScore() + ", " +
                    record.getStage() + ")";
            this.manager.insertRecords(query);
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            this.manager.closeConnection();
        }
    }

    @Override
    public Optional<Leaderboard> loadStoryModeLeaderboard() {
        return this.load(STORY_MODE_TABLE_NAME);
    }

    @Override
    public Optional<Leaderboard> loadSurvivalModeLeaderboard() {
        return this.load(SURVIVAL_MODE_TABLE_NAME);
    }

    @Override
    public boolean saveStoryModeLeaderboardRecord(LeaderboardRecord record) {
        return this.save(record, STORY_MODE_TABLE_NAME);
    }

    @Override
    public boolean saveSurvivalModeLeaderboardRecord(LeaderboardRecord record) {
        return this.save(record, SURVIVAL_MODE_TABLE_NAME);
    }

}
