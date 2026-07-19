package oopang.controller;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import oopang.commons.Command;
import oopang.commons.PlayerTag;
import oopang.model.Model;
import oopang.model.input.InputWriter;
import oopang.view.View;

/**
 * Represents a game loop that starts in a different thread.
 */
public final class GameLoopThread extends Thread implements GameLoop {
    private static final long MS_BETWEEN_FRAMES = 20;
    private static final double MAX_UPDATE_BOUND = 0.04;
    private static final double MSEC_TO_SEC = 0.001;
    private static final int MAXINPUT = 20;

    private final View scene;
    private final Model world;
    private final Map<PlayerTag, BlockingQueue<Command>> inputQueue;
    private final Map<PlayerTag, InputWriter> input;
    private volatile boolean paused;
    private volatile boolean stopped;

    /**
     * Creates a new game loop that updates the given model and renders on the
     * given view.
     * @param view
     *      the view to render on each frame.
     * @param model
     *      the model to update each frame.
     * @param input
     *      the map with the input Writers.
     */
    public GameLoopThread(final View view, final Model model, final Map<PlayerTag, InputWriter> input) {
        super();
        this.setName("Game Loop");
        this.setDaemon(true);
        this.scene = view;
        this.world = model;
        this.paused = false;
        this.inputQueue = new EnumMap<>(PlayerTag.class);
        input.keySet().forEach(player -> this.inputQueue.put(player, new ArrayBlockingQueue<>(MAXINPUT)));
        this.input = input;
    }

    @Override
    public void run() {
        this.updateGame(0);
        this.render();
        long lastTime = System.currentTimeMillis();
        while (!this.stopped) {
            if (this.paused) {
                synchronized (this) {
                    while (this.paused) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) { }
                    }
                }
                lastTime = System.currentTimeMillis() - MS_BETWEEN_FRAMES;
            }
            final long current = System.currentTimeMillis();
            this.processInput();
            this.updateGame(Math.min((current - lastTime) * MSEC_TO_SEC, MAX_UPDATE_BOUND));
            this.render();
            this.waitForNextFrame(current);
            lastTime = current;
        }
    }

    @Override
    public synchronized void pauseLoop() {
        this.paused = true;
    }

    @Override
    public synchronized void resumeLoop() {
        this.paused = false;
        this.notifyAll();
    }

    @Override
    public synchronized void stopLoop() {
        this.stopped = true;
        this.interrupt();
    }

    @Override
    public synchronized boolean addCommand(final Command cmd, final PlayerTag player) {
        return this.inputQueue.get(player).offer(cmd);
    }

    private void processInput() {
        this.inputQueue.keySet().forEach(player -> {
            final BlockingQueue<Command> queue = inputQueue.get(player);
            Command cmd = queue.poll();
            while (cmd != null) {
                cmd.execute(input.get(player));
                cmd = queue.poll();
            }
        });
    }

    private void updateGame(final double deltaTime) {
        this.world.update(deltaTime);
    }

    private void render() {
        this.scene.render();
    }

    private void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < MS_BETWEEN_FRAMES) {
            try {
                Thread.sleep(MS_BETWEEN_FRAMES - dt);
            } catch (InterruptedException ex) { }
        }
    }
}
