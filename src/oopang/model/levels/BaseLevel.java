package oopang.model.levels;

import java.util.LinkedList;

import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

import oopang.commons.events.EventSource;
import oopang.commons.events.Event;
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
    private final EventSource<GameObject> objectCreatedEvent;

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
        this.objectCreatedEvent = new EventSource<>();
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

    private void createWalls() {
        final GameObjectFactory factory = LevelManager.getCurrentLevel().getGameObjectFactory();
        final GameObject horizontal1 = factory.createWall(Model.TOTAL_WIDTH, Model.WALL_WIDTH);
        horizontal1.setPosition(Points2D.of(0, -Model.WALL_WIDTH / 2));
        final GameObject horizontal2 = factory.createWall(Model.TOTAL_WIDTH, Model.WALL_WIDTH);
        horizontal2.setPosition(Points2D.of(0, Model.WORLD_HEIGHT + (Model.WALL_WIDTH / 2)));
        final GameObject vertical1 = factory.createWall(Model.WALL_WIDTH, Model.WORLD_HEIGHT);
        vertical1.setPosition(Points2D.of(-((Model.WORLD_WIDTH / 2) + (Model.WALL_WIDTH / 2)), Model.WORLD_HEIGHT / 2));
        final GameObject vertical2 = factory.createWall(Model.WALL_WIDTH, Model.WORLD_HEIGHT);
        vertical2.setPosition(Points2D.of((Model.WORLD_WIDTH / 2) + (Model.WALL_WIDTH / 2), Model.WORLD_HEIGHT / 2));
    }

    @Override
    public Event<GameObject> getObjectCreatedEvent() {
        return this.objectCreatedEvent;
    }

    @Override
    public Event<GameOverStatus> getGameOverStatusEvent() {
        //A base level is not able to end so it returns a useless event.
        return new EventSource<>();
    }

    @Override
    public double getRemainingTimePercentage() {
        return 0;
    }

    @Override
    public Event<Void> getTimeOutEvent() {
        return new EventSource<Void>();
    }
}
