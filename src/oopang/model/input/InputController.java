package oopang.model.input;

import java.util.LinkedList;
import java.util.List;

/**
 * Describes an object that can act as a {@link InputReader} and as an {@link InputWriter} and stores
 * informations about the current input received.
 */
public final class InputController implements InputReader, InputWriter {

    private boolean shooting;
    private final List<InputDirection> direction;

    /**
     * Initializes a new InputController with direction NONE and no shooting.
     */
    public InputController() {
        this.shooting = false;
        this.direction = new LinkedList<>();
        this.direction.add(InputDirection.NONE);
    }

    @Override
    public synchronized InputDirection getDirection() {
        return this.direction.get(this.direction.size() - 1);
    }

    @Override
    public synchronized boolean isShooting() {
        return shooting;
    }

    @Override
    public synchronized void setDirection(final InputDirection dir) {
        this.removeDirection(dir);
        this.direction.add(dir);
    }

    @Override
    public synchronized void setShooting(final boolean status) {
        this.shooting = status;
    }

    @Override
    public synchronized void removeDirection(final InputDirection dir) {
        if (dir != InputDirection.NONE && this.direction.contains(dir)) {
            this.direction.remove(dir);
        }
    }
}
