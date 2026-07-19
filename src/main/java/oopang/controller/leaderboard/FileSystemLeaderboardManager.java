package oopang.controller.leaderboard;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import oopang.controller.InstallManager;

/**
 * This class implement the Leaderboard manager interface, and add two method Load and Save, 
 * load read from file, save write on file.
 */
public final class FileSystemLeaderboardManager implements LeaderboardManager {

    private boolean save(final LeaderboardRecord record, final String path) {
        final Leaderboard leaderboard = this.load(path).orElse(new Leaderboard());
        try (
            OutputStream file = new FileOutputStream(path);
            OutputStream bstream = new BufferedOutputStream(file);
            ObjectOutputStream ostream = new ObjectOutputStream(bstream)
        ) {
            leaderboard.addRecord(record);
            ostream.writeObject(leaderboard);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private Optional<Leaderboard> load(final String path) {
        if (!Files.exists(Paths.get(path))) {
            return Optional.of(new Leaderboard());
        }
        try (
            InputStream file = new FileInputStream(path);
            InputStream bstream = new BufferedInputStream(file);
            ObjectInputStream ostream = new ObjectInputStream(bstream)
        ) {
            return Optional.of((Leaderboard) ostream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            return Optional.empty();
        }
    }
    @Override
    public Optional<Leaderboard> loadStoryModeLeaderboard() {
        return this.load(InstallManager.STORY_FILE);
    }

    @Override
    public Optional<Leaderboard> loadSurvivalModeLeaderboard() {
        return this.load(InstallManager.SURVIVAL_FILE);
    }

    @Override
    public boolean saveStoryModeLeaderboardRecord(final LeaderboardRecord record) {
        return this.save(record, InstallManager.STORY_FILE);
    }

    @Override
    public boolean saveSurvivalModeLeaderboardRecord(final LeaderboardRecord record) {
        return this.save(record, InstallManager.SURVIVAL_FILE);
    }
}
