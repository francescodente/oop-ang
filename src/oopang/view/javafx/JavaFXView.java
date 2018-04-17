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
    private static final String TITLE = "OOPang";
    private Controller control;
    private SceneController currentScene;
    private final Stage stage;
    private final DialogFactory dialogfactory;
    private boolean viewStarted;

    /**
     * Creates a new javaFX specific view.
     * @param stage
     *      the primary stage of the application.
     */
    public JavaFXView(final Stage stage) {
        this.stage = stage;
        this.dialogfactory = new JavaFXDialogFactory();
        this.viewStarted = false;
    }

    @Override
    public final void launch(final Controller controller) {
        this.control = controller;
        this.stage.setMinWidth(MIN_WIDTH);
        this.stage.setMinHeight(MIN_HEIGHT);
        this.stage.setMaximized(true);
        this.stage.setTitle(TITLE);
        this.loadScene(GameScene.MAIN_MENU);
    }

    @Override
    public final void render() {
        Platform.runLater(() -> this.currentScene.render());
    }

    @Override
    public final void loadScene(final GameScene scene) {
        try {
            final SceneWrapper wrapper = SceneLoader.getLoader().getScene(scene);
            wrapper.getController().init(control, this);
            this.currentScene = wrapper.getController();
            final Parent root = wrapper.getScene().getRoot();
            root.requestFocus();
            root.setOnKeyPressed(wrapper.getController()::onKeyPressed);
            Platform.runLater(() -> {
                final double oldWidth = this.stage.getWidth();
                final double oldHeight = this.stage.getHeight();
                this.stage.setScene(wrapper.getScene());
                this.stage.setWidth(oldWidth);
                this.stage.setHeight(oldHeight);
                if (!this.viewStarted) {
                    this.stage.show();
                    this.viewStarted = true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public DialogFactory getDialogFactory() {
        return this.dialogfactory;
    }
}
