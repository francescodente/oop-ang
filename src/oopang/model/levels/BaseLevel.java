package oopang.model.levels;

import java.util.LinkedList;

import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

import oopang.commons.LevelManager;
import oopang.commons.events.Event;
import oopang.commons.events.EventHandler;
import oopang.commons.space.Points2D;
import oopang.model.GameOverStatus;
import oopang.model.Model;
import oopang.model.gameobjects.BasicFactory;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.GameObjectFactory;
import oopang.model.physics.CollisionManager;
import oopang.model.physics.SimpleCollisionManager;

/**
 * The class BaseLevel implements the Level interface.
 * It manages the updating of the GameObject.
 */
public class BaseLevel implements Level {

    private static final int INITIAL_SCORE = 0;

    private final Queue<GameObject> startQueue;
    private final Queue<GameObject> destroyQueue;
    private final List<GameObject> gameObjects;
    private int score;
    private final GameObjectFactory factory;
    private final CollisionManager collisionManager;
    private final Event<GameObject> objectCreatedEvent;

    /**
     * Creates a new BaseLevel object.
     */
    public BaseLevel() {
        this.startQueue = new LinkedList<>();
        this.destroyQueue = new LinkedList<>();
        this.gameObjects = new LinkedList<>();
        this.score = INITIAL_SCORE;
        this.factory = new BasicFactory(this);
        this.collisionManager = new SimpleCollisionManager();
        this.objectCreatedEvent = new Event<>();
    }

    @Override
    public void start() {
        this.createWalls();
    }

    @Override
    public void update(final double deltaTime) {
        while (startQueue.size() > 0) {
            final GameObject obj = startQueue.poll();
            obj.start();
            this.gameObjects.add(obj);
            objectCreatedEvent.trigger(obj);
        }
        this.gameObjects.forEach(e -> e.update(deltaTime));
        this.collisionManager.step();
        while (destroyQueue.size() > 0) {
            final GameObject obj = destroyQueue.poll();
            this.gameObjects.remove(obj);
        }
    }

    @Override
    public Stream<GameObject> getAllObjects() {
        return this.gameObjects.stream();
    }

    @Override
    public void addGameObject(final GameObject obj) {
        this.startQueue.add(obj);
    }

    @Override
    public void removeGameObject(final GameObject obj) {
        this.destroyQueue.add(obj);
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void addScore(final int amount) {
        this.score += amount;
    }

    @Override
    public GameObjectFactory getGameObjectFactory() {
        return this.factory;
    }

    @Override
    public CollisionManager getCollisionManager() {
        return this.collisionManager;
    }

    @Override
    public void registerObjectCreatedEvent(final EventHandler<GameObject> handler) {
        this.objectCreatedEvent.registerHandler(handler);
    }

    @Override
    public void unregisterObjectCreatedEvent(final EventHandler<GameObject> handler) {
        this.objectCreatedEvent.unregisterHandler(handler);
    }

    @Override
    public void registerGameOverEvent(final EventHandler<GameOverStatus> handler) {
        // A base level is not able to end.
    }

    private void createWalls() {
        final GameObject horizontal1 = LevelManager.getCurrentLevel().getGameObjectFactory().createWall(Model.WORLD_WIDTH, Model.WALL_WIDTH);
        horizontal1.setPosition(Points2D.of(0, -Model.WALL_WIDTH / 2));
        final GameObject horizontal2 = LevelManager.getCurrentLevel().getGameObjectFactory().createWall(Model.WORLD_WIDTH, Model.WALL_WIDTH);
        horizontal2.setPosition(Points2D.of(0, Model.WORLD_HEIGHT + (Model.WALL_WIDTH / 2)));
        final GameObject vertical1 = LevelManager.getCurrentLevel().getGameObjectFactory().createWall(Model.WALL_WIDTH, Model.WORLD_HEIGHT);
        vertical1.setPosition(Points2D.of(-((Model.WORLD_WIDTH / 2) + (Model.WALL_WIDTH / 2)), Model.WORLD_HEIGHT / 2));
        final GameObject vertical2 = LevelManager.getCurrentLevel().getGameObjectFactory().createWall(Model.WALL_WIDTH, Model.WORLD_HEIGHT);
        vertical2.setPosition(Points2D.of((Model.WORLD_WIDTH / 2) + (Model.WALL_WIDTH / 2), Model.WORLD_HEIGHT / 2));
    }
}
