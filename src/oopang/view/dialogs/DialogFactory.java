package oopang.view.dialogs;

/**
 * This is an interface for a Dialog Factory.
 */
public interface DialogFactory {

    /**
     * Create a LevelNotLoaded dialog.
     * @param ex
     *      the exception to be shown.
     * @return
     *      a Dialog specific for the levelNotLoaded error.
     */
    Dialog createLevelNotLoaded(Exception ex);

    /**
     * Create a user not found dialog.
     * @param username
     *      the user name tried to be loaded
     * @return
     *      a dialog specific for the user not found error.
     */
    Dialog createUserNotFound(String username);


    /**
     * Create a user not registered dialog.
     * @param username
     *      the user name tried to be registered
     * @return
     *      a dialog specific for the user registered error.
     */
    Dialog createFailedToRegisterUser(String username);

    /**
     * Create an Empty Field error dialog.
     * @param fieldName
     *      the name of the field left empty
     * @return
     *       a dialog specific for the empty field error.
     */
    Dialog createEmptyFieldError(String fieldName);
}
