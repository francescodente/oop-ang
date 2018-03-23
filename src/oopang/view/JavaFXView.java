package oopang.view;

import javafx.application.Application;
import javafx.stage.Stage;
import oopang.controller.Controller;
import oopang.view.dialogs.Dialog;

/**
 * This is the concrete implementation of the view Interface.
 */
public class JavaFXView extends Application implements View {

    private Controller control;
    private SceneController currentScene;
    private Stage stage;

    @Override
    public final void launch(final Controller controller) {
        this.control = controller;
        launch(new String[0]);
    }

    @Override
    public final void render() {
        this.currentScene.render();
    }

    @Override
    public final void loadScene(final GameScene scene) {
        try {
            final SceneWrapper wrapper = SceneLoader.getLoader().getScene(scene);
            stage.setScene(wrapper.getScene());
            wrapper.getController().init(control, this);
            this.currentScene = wrapper.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showPauseMenu() {
        // TODO Auto-generated method stub
    }

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        this.stage = primaryStage; 
        this.loadScene(GameScene.MAIN_MENU);
    }

    @Override
    public void showDialog(final Dialog dialog) {
        dialog.show();
    }
}
