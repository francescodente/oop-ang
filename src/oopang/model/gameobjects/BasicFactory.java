package oopang.model.gameobjects;

import oopang.commons.LevelManager;
import oopang.commons.space.Vector2D;
import oopang.model.levels.Level;

/**
 * 
 *
 */
public class BasicFactory implements GameObjectFactory {

    private final Level currentLevel;

    public BasicFactory(Level level) {
        this.currentLevel = level;
    }

    @Override
    public GameObject createPlayer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameObject createBall(final double radius, final Vector2D velocity) {
        Ball ball = new Ball(radius, velocity);
        this.currentLevel.addGameObject(ball);
        return ball;
    }

    @Override
    public GameObject createHookShot() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameObject createWall(double width, double height) {
        // TODO Auto-generated method stub
        return null;
    }

}
