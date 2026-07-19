package oopang.view;

/**
 * Identifies a scene that can be loaded.
 */
public enum GameScene {
    /**
     * The opening scene of the game.
     */
    MAIN_MENU,
    /**
     * The scene where the user can choose between single and multi player.
     */
    SELECT_PLAYERS,
    /**
     * The scene where the user can select between survival or arcade mode.
     */
    SELECT_MODE,
    /**
     * The scene where the user can select the level from which to start the arcade mode.
     */
    SELECT_LEVEL,
    /**
     * The game scene.
     */
    GAME_GUI,
    /**
     * The game over scene.
     */
    GAMEOVER,
    /**
     * The scene to be showed after the player has won an arcade mode level.
     */
    LEVEL_STEP,
    /**
     * The scene to be showed after the player has lost an arcade mode level.
     */
    LEVEL_RESET,
    /**
     * The scene where the user can login, register or continue as guest.
     */
    LOGIN,
    /**
     * The scene that shows the leaderboard for the active mode.
     */
    LEADERBOARD,
    /**
     * The scene where the user can look at his statistics and upgrade his powers.
     */
    USER_PROFILE;
}
