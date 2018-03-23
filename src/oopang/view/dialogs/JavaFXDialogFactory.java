package oopang.view.dialogs;


import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import oopang.view.View;

/**
 * This is a JavaFX implementation of the DialogFactory.
 * Note: most of the code was taken from <a> href="http://code.makery.ch/blog/javafx-dialogs-official/"</a>
 */
public class JavaFXDialogFactory implements DialogFactory {

    private final View view;

    /**
     * Create a new JavaFXDialogFactory.
     * @param view
     *      the view Reference.
     */
    public JavaFXDialogFactory(final View view) {
        this.view = view;
    }

    @Override
    public Dialog createLevelNotLoaded(final Exception ex) {
        return new JavaFXDialog(this.view) {
            private final Alert alert = new Alert(AlertType.ERROR);

            @Override
            public void show() {
                alert.setTitle("Fatal Error");
                alert.setHeaderText("Loader has stopped working");
                alert.setContentText("Oooops something went wrong while loading the selected level");

                final StringWriter sw = new StringWriter();
                final PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                final String exceptionText = sw.toString();
                final TextArea textArea = new TextArea(exceptionText);
                textArea.setEditable(false);
                textArea.setWrapText(true);
                final Label label = new Label("Exception thrown: " + ex.getClass().getName());
                textArea.setMaxWidth(Double.MAX_VALUE);
                textArea.setMaxHeight(Double.MAX_VALUE);
                GridPane.setVgrow(textArea, Priority.ALWAYS);
                GridPane.setHgrow(textArea, Priority.ALWAYS);
                final GridPane expContent = new GridPane();
                expContent.setMaxWidth(Double.MAX_VALUE);
                expContent.add(label, 0, 0);
                expContent.add(textArea, 0, 1);
                alert.getDialogPane().setExpandableContent(expContent);
                alert.showAndWait();
            }
        };
    }

}
