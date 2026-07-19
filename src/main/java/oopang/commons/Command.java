package oopang.commons;

import oopang.model.input.InputWriter;

/**
 * A command to be executed from the {@link Controller}.
 */
@FunctionalInterface
public interface Command {

    /**
     * Runs the command.
     * @param input
     *      the {@link InputWriter} to be modified.
     */
    void execute(InputWriter input);
}
