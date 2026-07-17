package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import oopang.controller.leaderboard.FallbackLeaderboardManager;
import oopang.controller.leaderboard.Leaderboard;
import oopang.controller.leaderboard.LeaderboardManager;
import oopang.controller.leaderboard.LeaderboardRecord;

/**
 * Tests for online-first leaderboard fallback behavior.
 */
public class TestFallbackLeaderboardManager {

    private final LeaderboardRecord record = new LeaderboardRecord("player", 100, 2);

    /**
     * Test online load is preferred when available.
     */
    @Test
    public void testOnlineLoadPreferred() {
        final Leaderboard onlineLeaderboard = new Leaderboard();
        final Leaderboard localLeaderboard = new Leaderboard();
        final FakeLeaderboardManager online = new FakeLeaderboardManager(Optional.of(onlineLeaderboard), true);
        final FakeLeaderboardManager local = new FakeLeaderboardManager(Optional.of(localLeaderboard), true);
        final LeaderboardManager fallback = new FallbackLeaderboardManager(online, local);

        assertSame(onlineLeaderboard, fallback.loadStoryModeLeaderboard().get());
        assertFalse(local.storyLoaded);
    }

    /**
     * Test local load is used when online load fails.
     */
    @Test
    public void testLocalLoadFallback() {
        final Leaderboard localLeaderboard = new Leaderboard();
        final FakeLeaderboardManager online = new FakeLeaderboardManager(Optional.empty(), false);
        final FakeLeaderboardManager local = new FakeLeaderboardManager(Optional.of(localLeaderboard), true);
        final LeaderboardManager fallback = new FallbackLeaderboardManager(online, local);

        assertSame(localLeaderboard, fallback.loadStoryModeLeaderboard().get());
        assertTrue(local.storyLoaded);
    }

    /**
     * Test local save is used when online save fails.
     */
    @Test
    public void testLocalSaveFallback() {
        final FakeLeaderboardManager online = new FakeLeaderboardManager(Optional.empty(), false);
        final FakeLeaderboardManager local = new FakeLeaderboardManager(Optional.empty(), true);
        final LeaderboardManager fallback = new FallbackLeaderboardManager(online, local);

        assertTrue(fallback.saveStoryModeLeaderboardRecord(this.record));
        assertTrue(online.storySaved);
        assertTrue(local.storySaved);
    }

    /**
     * Test local save is skipped when online save succeeds.
     */
    @Test
    public void testLocalSaveSkippedWhenOnlineSucceeds() {
        final FakeLeaderboardManager online = new FakeLeaderboardManager(Optional.empty(), true);
        final FakeLeaderboardManager local = new FakeLeaderboardManager(Optional.empty(), true);
        final LeaderboardManager fallback = new FallbackLeaderboardManager(online, local);

        assertTrue(fallback.saveStoryModeLeaderboardRecord(this.record));
        assertTrue(online.storySaved);
        assertFalse(local.storySaved);
    }

    private static final class FakeLeaderboardManager implements LeaderboardManager {
        private final Optional<Leaderboard> leaderboard;
        private final boolean saveResult;
        private boolean storyLoaded;
        private boolean storySaved;

        FakeLeaderboardManager(final Optional<Leaderboard> leaderboard, final boolean saveResult) {
            this.leaderboard = leaderboard;
            this.saveResult = saveResult;
        }

        @Override
        public Optional<Leaderboard> loadStoryModeLeaderboard() {
            this.storyLoaded = true;
            return this.leaderboard;
        }

        @Override
        public Optional<Leaderboard> loadSurvivalModeLeaderboard() {
            return this.leaderboard;
        }

        @Override
        public boolean saveStoryModeLeaderboardRecord(final LeaderboardRecord record) {
            this.storySaved = true;
            return this.saveResult;
        }

        @Override
        public boolean saveSurvivalModeLeaderboardRecord(final LeaderboardRecord record) {
            return this.saveResult;
        }
    }
}
