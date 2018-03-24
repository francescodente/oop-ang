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
}
