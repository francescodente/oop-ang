package oopang;

import javafx.application.Application;
import javafx.stage.Stage;
import oopang.controller.Controller;
import oopang.controller.ControllerImpl;
import oopang.model.Model;
import oopang.model.World;
import oopang.view.View;
import oopang.view.javafx.JavaFXView;

/**
 * THE GOD CLASS.
 */
public class OOPang extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final View view = new JavaFXView(primaryStage);
        final Model model = new World();
        final Controller controller = new ControllerImpl(model, view);
        view.launch(controller);
    }

}
