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
import java.util.Optional;

import oopang.controller.InstallManager;

/**
 * This class implement the Leaderboard manager interface, and add two method Load and Save, 
 * load read from file, save write on file.
 *
 */
public final class FileSystemLeaderboardManager implements LeaderboardManager {
    /**
     * Constructor of class FileSystemLeaderboardManager.
     */
    public FileSystemLeaderboardManager() {
    }

    private void save(final Leaderboard leaderboard, final String path) throws IOException {
        try (OutputStream file = new FileOutputStream(path);
                OutputStream bstream = new BufferedOutputStream(file);
                ObjectOutputStream ostream = new ObjectOutputStream(bstream)) {
                   ostream.writeObject(leaderboard);
           } catch (IOException e) {
               throw new IOException();
           }
    }

    private Leaderboard load(final String path) throws IOException {
        try (
                InputStream file = new FileInputStream(path);
                InputStream bstream = new BufferedInputStream(file);
                ObjectInputStream ostream = new ObjectInputStream(bstream);
              ) {
                 return (Leaderboard) ostream.readObject();
             } catch (IOException | ClassNotFoundException e) {
                 throw new IOException();
             }
    }
    @Override
    public Optional<Leaderboard> loadStoryModeLeaderboard() {
        try {
            return Optional.of(this.load(InstallManager.STORY_FILE));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Leaderboard> loadSurvivalModeLeaderboard() {
        try {
            return Optional.of(this.load(InstallManager.SURVIVAL_FILE));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean saveStoryModeLeaderboard(final Leaderboard leaderboard) {
        try {
            this.save(leaderboard, InstallManager.STORY_FILE);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean saveSurvivalModeLeaderboard(final Leaderboard leaderboard) {
        try {
            this.save(leaderboard, InstallManager.SURVIVAL_FILE);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
