package oopang.model;

/**
 * Describes the way a level ended.
 */
public enum LevelResult {
    /**
     * Indicates that the level ended because the time expired.
     */
    OUT_OF_TIME,
    /**
     * Indicates that the level ended because the player got hit by a ball.
     */
    PLAYER_DEAD,
    /**
     * Indicates that the level ended because the player shot all balls.
     */
    LEVEL_COMPLETE,
    /**
     * Indicates that the user decided to end the level through the pause menu.
     */
    FORCE_EXIT
}
