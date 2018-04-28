package oopang.model.input;
/**
 * Interface which give the possibility to read input informations.
 */
public interface InputReader {

    /**
     * Method which return the Enum direction.
     * @return
     *      the Enum direction
     */
    InputDirection getDirection();

    /**
     * Method which return the status of the shoot.
     * @return
     *      the boolean result
     */
    boolean isShooting();
}
