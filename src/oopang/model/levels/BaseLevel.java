package oopang.model.levels;

import java.util.Arrays;
import java.util.LinkedList;

import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

import oopang.commons.LevelManager;
import oopang.commons.events.Event;
import oopang.commons.events.EventHandler;
import oopang.commons.space.Points2D;
import oopang.model.GameOverStatus;
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
    private static final double WORLD_WIDTH = 200;
    private static final double WORLD_HEIGHT = 100;
    private static final double WALL_WIDTH = 4;

    private final Queue<GameObject> startQueue;
    private final List<GameObject> gameObjects;
    private int score;
    private final GameObjectFactory factory;
    private final CollisionManager collisionManager;
    private final Event<GameObject> objectCreatedEvent;
    private final List<GameObject> externalwalls;

    /**
     * Creates a new BaseLevel object.
     */
    public BaseLevel() {
        this.startQueue = new LinkedList<>();
        this.gameObjects = new LinkedList<>();
        this.score = INITIAL_SCORE;
        this.factory = new BasicFactory(this);
        this.collisionManager = new SimpleCollisionManager();
        this.objectCreatedEvent = new Event<>();
        this.externalwalls = this.createWalls();
    }

    @Override
    public void start() {
        this.externalwalls.forEach(w -> this.gameObjects.add(w));
        this.gameObjects.forEach(e -> e.start());
    }

    @Override
    public void update(final double deltaTime) {
        while (startQueue.size() > 0) {
            final GameObject obj = startQueue.poll();
            objectCreatedEvent.trigger(obj);
            obj.start();
        }
        this.gameObjects.forEach(e -> e.update(deltaTime));
    }

    @Override
    public Stream<GameObject> getAllObjects() {
        return this.gameObjects.stream();
    }

    @Override
    public void addGameObject(final GameObject obj) {
        this.gameObjects.add(obj);
        this.startQueue.add(obj);
    }

    @Override
    public void removeGameObject(final GameObject obj) {
        this.gameObjects.remove(obj);
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

    private List<GameObject> createWalls() {
        final GameObject horizontal1 = LevelManager.getCurrentLevel().getGameObjectFactory().createWall(WORLD_WIDTH, WALL_WIDTH);
        horizontal1.setPosition(Points2D.of(0, -WALL_WIDTH / 2));
        final GameObject horizontal2 = LevelManager.getCurrentLevel().getGameObjectFactory().createWall(WORLD_WIDTH, WALL_WIDTH);
        horizontal2.setPosition(Points2D.of(0, WORLD_HEIGHT + (WALL_WIDTH / 2)));
        final GameObject vertical1 = LevelManager.getCurrentLevel().getGameObjectFactory().createWall(WALL_WIDTH, WORLD_HEIGHT);
        vertical1.setPosition(Points2D.of(-((WORLD_WIDTH / 2) + (WALL_WIDTH / 2)), WORLD_HEIGHT / 2));
        final GameObject vertical2 = LevelManager.getCurrentLevel().getGameObjectFactory().createWall(WALL_WIDTH, WORLD_HEIGHT);
        vertical2.setPosition(Points2D.of((WORLD_WIDTH / 2) + (WALL_WIDTH / 2), WORLD_HEIGHT / 2));
        return Arrays.asList(horizontal1, horizontal2, vertical1, vertical2);
    }
}
