package oopang.view.javafx;

import javafx.application.Platform;
import javafx.stage.Stage;
import oopang.controller.Controller;
import oopang.view.GameScene;
import oopang.view.View;
import oopang.view.dialogs.Dialog;
import oopang.view.dialogs.DialogFactory;
import oopang.view.dialogs.JavaFXDialogFactory;
import oopang.view.javafx.controllers.SceneController;

/**
 * This is the concrete implementation of the view Interface.
 */
public class JavaFXView implements View {

    private Controller control;
    private SceneController currentScene;
    private final Stage stage;
    private DialogFactory dialogfactory;

    /**
     * Creates a new javaFX specific view.
     * @param stage
     *      the primary stage of the application.
     */
    public JavaFXView(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public final void launch(final Controller controller) {
        this.control = controller;
        this.dialogfactory = new JavaFXDialogFactory(this);
        this.stage.show();
        this.loadScene(GameScene.MAIN_MENU);
    }

    @Override
    public final void render() {
        Platform.runLater(() -> this.currentScene.render());
    }

    @Override
    public final void loadScene(final GameScene scene) {
        Platform.runLater(() -> {
            try {
                final SceneWrapper wrapper = SceneLoader.getLoader().getScene(scene);
                stage.setScene(wrapper.getScene());
                wrapper.getController().init(control, this);
                this.currentScene = wrapper.getController();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void showPauseMenu() {
        // TODO Auto-generated method stub
    }

    @Override
    public void showDialog(final Dialog dialog) {
        Platform.runLater(dialog::show);
    }

    @Override
    public DialogFactory getDialogFactory() {
        return this.dialogfactory;
    }
}
