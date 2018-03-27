package oopang;

import oopang.controller.Controller;
import oopang.controller.ControllerImpl;
import oopang.model.Model;
import oopang.model.World;
import oopang.view.View;
import oopang.view.javafx.JavaFXView;

/**
 * THE GOD CLASS.
 */
public class OOPang {

    public static void main(String[] args) {
        final View view = new JavaFXView();
        final Model model = new World();
        final Controller controller = new ControllerImpl(model, view);
        view.launch(controller);
    }

}
