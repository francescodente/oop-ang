package oopang.view.javafx;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.stage.Stage;
import oopang.controller.Controller;
import oopang.view.GameScene;
import oopang.view.View;
import oopang.view.dialogs.DialogFactory;
import oopang.view.dialogs.JavaFXDialogFactory;
import oopang.view.javafx.controllers.SceneController;

/**
 * This is the concrete implementation of the view Interface.
 */
public class JavaFXView implements View {

    private static final double MIN_WIDTH = 384;
    private static final double MIN_HEIGHT = 200;
    private Controller control;
    private SceneController currentScene;
    private final Stage stage;
    private final DialogFactory dialogfactory;

    /**
     * Creates a new javaFX specific view.
     * @param stage
     *      the primary stage of the application.
     */
    public JavaFXView(final Stage stage) {
        this.stage = stage;
        this.dialogfactory = new JavaFXDialogFactory();
    }

    @Override
    public final void launch(final Controller controller) {
        this.control = controller;
        this.stage.show();
        this.stage.setMinWidth(MIN_WIDTH);
        this.stage.setMinHeight(MIN_HEIGHT);
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
                final double oldWidth = this.stage.getWidth();
                final double oldHeight = this.stage.getHeight();
                this.stage.setScene(wrapper.getScene());
                this.stage.setWidth(oldWidth);
                this.stage.setHeight(oldHeight);
                wrapper.getController().init(control, this);
                this.currentScene = wrapper.getController();
                final Parent root = wrapper.getScene().getRoot();
                root.requestFocus();
                root.setOnKeyPressed(wrapper.getController()::onKeyPressed);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public DialogFactory getDialogFactory() {
        return this.dialogfactory;
    }
}
