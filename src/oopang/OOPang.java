package oopang;

import javafx.application.Application;
import javafx.stage.Stage;
import oopang.controller.Controller;
import oopang.controller.ControllerImpl;
import oopang.controller.InstallManager;
import oopang.model.Model;
import oopang.model.World;
import oopang.view.View;
import oopang.view.javafx.JavaFXView;

/**
 * The class containing the main method.
 */
public class OOPang extends Application {

    /**
     * The main entry point of the application.
     * @param args
     *      CLI arguments.
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        InstallManager.setupApplication();
        final View view = new JavaFXView(primaryStage);
        final Model model = new World();
        final Controller controller = new ControllerImpl(model, view);
        view.launch(controller);
    }

}
