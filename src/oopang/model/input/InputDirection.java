package oopang.model.input;

/**
 * Enum which define the direction of the input.
 */
public enum InputDirection {

    /**
     * -1 associated with left direction.
     */
    LEFT(-1),

    /**
     * 1 associated with left direction.
     */
    RIGHT(1),

    /**
     * 0 associated with left direction.
     */
    NONE(0);

    private final int direction;

    InputDirection(final int dir) {
        this.direction = dir;
    }

    /**
     * Returns the integer associated to the direction.
     * @return
     *      the integer associated to the direction
     */
    public int getDirection() {
        return this.direction;
    }

}
