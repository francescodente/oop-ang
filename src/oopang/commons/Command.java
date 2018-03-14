package oopang.commons;

import oopang.model.input.InputWriter;

/**
 * A command to be executed from the Controller.
 */
@FunctionalInterface
public interface Command {

    /**
     * Runs the command.
     * @param input
     *      the inputWriter to be modified.
     */
    void execute(InputWriter input);
}
