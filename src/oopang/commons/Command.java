package oopang.commons;

/**
 * A command to be executed from the Controller.
 */
@FunctionalInterface
public interface Command {

    /**
     * Runs the command.
     */
    void execute();
}
