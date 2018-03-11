package oopang.controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import oopang.commons.Command;
import oopang.model.Model;
import oopang.view.View;

/**
 * Represents an engine that periodically updates the world and renders it on the view.
 */
public class GameLoop extends Thread {

    private static final long MS_BETWEEN_FRAMES = 20;
    private static final double MSEC_TO_SEC = 0.001;
    private static final int MAXINPUT = 20;

    private final View scene;
    private final Model world;
    private volatile boolean paused;
    private volatile boolean stopped;
    private BlockingQueue<Command> inputQueue;

    /**
     * Creates a new game loop that updates the given model and renders on the
     * given view.
     * @param view
     *      the view to render on each frame.
     * @param model
     *      the model to update each frame.
     */
    public GameLoop(final View view, final Model model) {
        super();
        this.scene = view;
        this.world = model;
        this.paused = false;
        this.inputQueue = new ArrayBlockingQueue<>(MAXINPUT);
    }

    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        while (!this.stopped) {
            if (this.paused) {
                while (this.paused) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) { }
                }
                lastTime = System.currentTimeMillis() - MS_BETWEEN_FRAMES;
            }
            final long current = System.currentTimeMillis();
            this.processInput();
            this.updateGame((current - lastTime) * MSEC_TO_SEC);
            this.render();
            this.waitForNextFrame(current);
            lastTime = current;
        }
    }

    /**
     * Temporarily stops the loop until resume() is called.
     */
    public synchronized void pauseLoop() {
        this.paused = true;
    }

    /**
     * Makes the loop restart if it was previously paused.
     */
    public synchronized void resumeLoop() {
        this.paused = false;
        this.notifyAll();
    }

    /**
     * Stops the loop and the running thread.
     */
    public synchronized void stopLoop() {
        this.stopped = true;
        this.interrupt();
    }

    /**
     * Try to add a new Command to the commandQueue.
     * @param cmd
     *      the new command to be added
     * @return 
     *      true if the command is successful added
     */
    public boolean addCommand(final Command cmd) {
        return this.inputQueue.offer(cmd);
    }

    private void processInput() {
        Command toBeExec = this.inputQueue.poll();
        while (toBeExec != null) {
            toBeExec.execute();
            toBeExec = this.inputQueue.poll();
        }
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
