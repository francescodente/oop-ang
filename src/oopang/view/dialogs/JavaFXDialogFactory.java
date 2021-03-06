package oopang.view.dialogs;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * This is a JavaFX implementation of the DialogFactory.
 * Note: most of the code was taken from <a> href="http://code.makery.ch/blog/javafx-dialogs-official/"</a>
 */
public final class JavaFXDialogFactory implements DialogFactory {

    @Override
    public Dialog createLevelNotLoaded(final Exception ex) {
        return new Dialog() {
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

    @Override
    public Dialog createUserNotFound(final String username) {
        return new Dialog() {
            private final Alert alert = new Alert(AlertType.ERROR);
            @Override
            public void show() {
                alert.setTitle("Login Error");
                alert.setHeaderText("User not found!");
                alert.setContentText("Could not load user " + username);
                alert.showAndWait();
            }
        };
    }

    @Override
    public Dialog createFailedToRegisterUser(final String username) {
        return new Dialog() {
            private final Alert alert = new Alert(AlertType.ERROR);
            @Override
            public void show() {
                alert.setTitle("Registration Error");
                alert.setHeaderText("Failed to register " + username);
                alert.setContentText("Can not register, maybe the username is already taken");
                alert.showAndWait();
            }
        };
    }

    @Override
    public Dialog createEmptyFieldError(final String fieldName) {
        return new Dialog() {
            private final Alert alert = new Alert(AlertType.WARNING);
            @Override
            public void show() {
                alert.setTitle("Field Empty");
                alert.setHeaderText("You left  " + fieldName + " empty");
                alert.setContentText("If you want to login yo should insert a " + fieldName);
                alert.showAndWait();
            }
        };
    }

    @Override
    public Dialog createNotEnoughCoins() {
        return new Dialog() {
            private final Alert alert = new Alert(AlertType.WARNING);
            @Override
            public void show() {
                alert.setTitle("Not enough coins");
                alert.setHeaderText("You don't have enough coins");
                alert.setContentText("You can't upgrade this power because you don't have the right amount of coins");
                alert.showAndWait();
            }

        };
    }

    @Override
    public Dialog createUserNotSaved(final String username) {
        return new Dialog() {
            private final Alert alert = new Alert(AlertType.ERROR);
            @Override
            public void show() {
                alert.setTitle("Saving error");
                alert.setHeaderText("Failed to save " + username);
                alert.setContentText("Last changes to user may not be effective in next run");
                alert.showAndWait();
            }
        };
    }
}
