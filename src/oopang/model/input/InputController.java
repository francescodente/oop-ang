package oopang.model.input;

/**
 * Describes an object that can act as a {@link InputReader} and as an {@link InputWriter}.
 */
public final class InputController implements InputReader, InputWriter {

    private boolean shooting;
    private InputDirection direction;

    /**
     * Initializes a new InputController with direction NONE and no shooting.
     */
    public InputController() {
        this.shooting = false;
        this.direction = InputDirection.NONE;
    }

    @Override
    public InputDirection getDirection() {
        return this.direction;
    }

    @Override
    public boolean isShooting() {
        return shooting;
    }

    @Override
    public void setDirection(final InputDirection dir) {
        this.direction = dir;
    }

    @Override
    public void setShooting(final boolean status) {
        this.shooting = status;
    }
}
