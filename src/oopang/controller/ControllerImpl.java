package oopang.controller;

import oopang.commons.Command;
import oopang.model.Model;
import oopang.model.World;
import oopang.model.levels.Level;
import oopang.model.levels.SinglePlayerLevel;
import oopang.view.View;
import oopang.view.ViewImpl;

/**
 * This is the concrete implementation of the Controller.
 */
public class ControllerImpl implements Controller {

    private final Model model;
    private final View view;
    private final GameLoop loop;
    //private final LevelLoader loader;

    /**
     * Create a new Controller instance.
     */
    public ControllerImpl() {
        model = new World();
        view = new ViewImpl();
        loop = new GameLoop(view, model);
    }

    @Override
    public final void startGame(final int levelIndex) {
        Level level = loader.load(levelIndex);
        level.registerObjectCreatedEvent(obj -> view.notifyNewGameObject(obj));

        model.setCurrentLevel(level);
        loop.start();
    }

    @Override
    public void pauseGame() {
        loop.pauseLoop();
    }

    @Override
    public void closeGame() {
        loop.stopLoop();
    }

    @Override
    public void sendCommand(final Command cmd) {
        this.loop.addCommand(cmd);
    }
}
