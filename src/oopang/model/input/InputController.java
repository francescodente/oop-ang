package oopang.model.input;

/**
 * Class which implements the 2 interfaces.
 */
public final class InputController implements InputReader, InputWriter {

    private boolean isShooting;
    private InputDirection direction;
    @Override
    public InputDirection getDirection() {
        return this.direction;
    }

    @Override
    public boolean isShooting() {
        return isShooting;
    }

    @Override
    public void setDirection(final InputDirection dir) {
        this.direction = dir;
    }

    @Override
    public void setShooting(final boolean status) {
        this.isShooting = status;
    }

}
