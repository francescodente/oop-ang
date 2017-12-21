package oopang.model;

public interface Model {

    /**
     * Return the current level being played
     * @return
     *      the current level
     */
    Level getCurrentLevel();
    
    /**
     * Set the current level to the given one.
     * @param level
     *      the new level
     */
    void setCurrentLevel(final Level level);
    
    /**
     * Updates the model's state
     * @param deltaTime
     *      time elapsed since last update
     */
    void update(final double deltaTime);
}
