package oopang.model.input;
/**
 * Interface which controls the Input.
 */
public interface InputWriter {
    /**
     * Set the direction of the moving object.
     * @param dir
     *      the direction
     */
    void setDirection(InputDirection dir);
    /**
     * Removes the specified direction.
     * @param dir
     *      the direction to remove.
     */
    void removeDirection(InputDirection dir);
    /**
     * Set the shoot status.
     * @param status
     *      the status of the shoot
     */
    void setShooting(boolean status);
}
