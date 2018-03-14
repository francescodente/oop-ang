package oopang.model.levels;

import java.util.LinkedList;

import java.util.List;
import java.util.stream.Stream;

import oopang.commons.events.Event;
import oopang.commons.events.EventHandler;
import oopang.model.gameobjects.BasicFactory;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.GameObjectFactory;
import oopang.model.physics.CollisionManager;
import oopang.model.physics.SimpleCollisionManager;

/**
 * The class BaseLevel implements the Level interface.
 * It manage the updating of the GameObject.
 *
 */
public class BaseLevel implements Level {

    private static final int INITIAL_SCORE = 0;

    private List<GameObject> list;
    private int score;
    private GameObjectFactory factory;
    private CollisionManager collision;
    private Event<GameObject> event;

    /**
     * Creates a new BaseLevel object.
     */
    public BaseLevel() {
        this.list = new LinkedList<>();
        this.score = INITIAL_SCORE;
        this.factory = new BasicFactory(this);
        this.collision = new SimpleCollisionManager();
        this.event = new Event<>();
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
        list.forEach(e -> e.start());
    }

    @Override
    public void update(final double deltaTime) {
        // TODO Auto-generated method stub
        list.forEach(e -> e.update(deltaTime));
    }

    @Override
    public Stream<GameObject> getAllObjects() {
        // TODO Auto-generated method stub
        return list.stream();
    }

    @Override
    public void addGameObject(final GameObject obj) {
        // TODO Auto-generated method stub
        list.add(obj);
    }

    @Override
    public void removeGameObject(final GameObject obj) {
        // TODO Auto-generated method stub
        list.remove(obj);
    }

    @Override
    public int getScore() {
        // TODO Auto-generated method stub
        return this.score;
    }

    @Override
    public GameObjectFactory getGameObjectFactory() {
        // TODO Auto-generated method stub
        return this.factory;
    }

    @Override
    public CollisionManager getCollisionManager() {
        // TODO Auto-generated method stub
        return this.collision;
    }

    @Override
    public void registerObjectCreatedEvent(final EventHandler<GameObject> handler) {
        // TODO Auto-generated method stub
        this.event.registerHandler(handler);
    }

    @Override
    public void unregisterObjectCreatedEvent(final EventHandler<GameObject> handler) {
        // TODO Auto-generated method stub
        this.event.unregisterHandler(handler);
    }

}
