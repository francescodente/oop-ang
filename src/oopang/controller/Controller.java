package oopang.controller;

import java.util.Optional;

import oopang.commons.Command;
import oopang.commons.PlayerTag;
import oopang.commons.events.EventHandler;
import oopang.controller.leaderboard.Leaderboard;
import oopang.controller.loader.LevelData;
import oopang.controller.users.User;

/**
 * Controller of the application.
 */
public interface Controller {

    /**
     * Starts a new story game session from a given level.
     * @param levelIndex
     *      the index of the starting level.
     * @param isMultiPlayer
     *      true if multiPlayer is active.
     */
    void startStoryGameSession(int levelIndex, boolean isMultiPlayer);

    /**
     * Starts a new infinite mode game session.
     * @param isMultiPlayer
     *      true if multiPlayer is active.
     */
    void startInifiniteGameSession(boolean isMultiPlayer);

    /**
     * Pauses the game interrupting the game loop.
     */
    void pauseGame();

    /**
     * Resume the game if it has been paused.
     */
    void resume();

    /**
     * Asks the {@link GameSession} to go on with a new level.
     */
    void continueGameSession();

    /**
     * Forces the closing of the current instance of the {@link GameSession} from outside.
     */
    void forceCloseGameSession();

    /**
     * Asks the controller to enqueue a new command to be executed.
     * @param cmd
     *      the command.
     * @param player
     *      the player the command is applied to.
     */
    void sendCommand(Command cmd, PlayerTag player);

    /**
     * Registers a new {@link EventHandler} to the level started event.
     * @param handler
     *      the {@link EventHandler} object.
     */
    void registerLevelStartedEvent(EventHandler<LevelData> handler);

    /**
     * Returns the life count.
     * @return
     *      the number of lives
     */
    int getLifeCount();

    /**
     * Register a new user.
     * @param userName
     *      name of the user.
     * @param password
     *      password of the user.
     * @return 
     *      true if the user is correctly registered.
     */
    boolean registerUser(String userName, String password);

    /**
     * Login a user that already exist.
     * @param userName
     *      name of the user.
     * @param password
     *      password of the user.
     * @return 
     *      true if the user is correctly loaded.
     */
    boolean loginUser(String userName, String password);

    /**
     * Logout the user.
     */
    void logoutUser();

    /**
     * Return the {@link User} active {@link Leaderboard}.
     * @return
     *      The {@link Leaderboard}
     */
    Leaderboard getLeaderboard();

    /**
     * Method that get the user.
     * @return
     *      an Optional of user.
     */
    Optional<User> getUser();

    /**
     * Return the current total Score taken from the current GameSession.
     * @return
     *      the current total score.
     */
    int getCurrentTotalScore();

    /**
     * Get current stage.
     * @return
     *      the current stage.
     */
    int getCurrentStage();
}
