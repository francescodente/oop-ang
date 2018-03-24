package oopang.view.dialogs;

import oopang.view.View;

/**
 * This is an abstract class for Dialogs.
 */
public abstract class JavaFXDialog implements Dialog {

    private final View view;

    /**
     * Create a new JavaFXDialog.
     * @param view
     *      the view Reference.
     */
    public JavaFXDialog(final View view) {
        this.view = view;
    }

    /**
     * Returns the view reference for children.
     * @return
     *      the view reference.
     */
    public View getView() {
        return view;
    }
}
