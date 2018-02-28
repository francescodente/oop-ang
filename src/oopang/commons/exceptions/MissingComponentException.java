package oopang.commons.exceptions;

import oopang.model.gameobjects.GameObject;

/**
 * An exception thrown when a {@link GameObject} is asked for a component that is not present.
 */
public class MissingComponentException extends Error {

    private static final long serialVersionUID = -4784172616647259549L;

    /**
     * Creates a new exception for the search of a component on the given {@link GameObject}.
     * @param obj
     *      the {@link GameObject}.
     */
    public MissingComponentException(final GameObject obj) {
        super("The requested Component is not present in the object " + obj);
    }
}
