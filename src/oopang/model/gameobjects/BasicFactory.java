package oopang.model.gameobjects;

import oopang.commons.space.Vector2D;
import oopang.model.BallColor;
import oopang.model.levels.Level;
import oopang.model.powers.Power;

/**
 * This class is a basic implementation of GameObjectFactory.
 */
public class BasicFactory implements GameObjectFactory {
    private static final int SCORE = 100;
    private final Level currentLevel;

    /**
     * Create a new factory instance.
     * @param level
     *      the current level reference
     */
    public BasicFactory(final Level level) {
        this.currentLevel = level;
    }

    @Override
    public GameObject createPlayer() {
        final Player player = new Player();
        this.currentLevel.addGameObject(player); 
        player.registerPickupCollectedEvent(obj -> this.currentLevel.addScore(SCORE));
        return player;
    }

    @Override
    public GameObject createBall(final int size, final Vector2D velocity, final BallColor color) {
        final GameObject ball = new Ball(size, velocity, color);
        this.currentLevel.addGameObject(ball);
        ball.registerDestroyedEvent(obj -> this.currentLevel.addScore(SCORE));
        return ball;
    }

    @Override
    public Shot createHookShot() {
        final Shot shot = new HookShot();
        this.currentLevel.addGameObject(shot);
        return shot;
    }

    @Override
    public Shot createStickyShot() {
        final Shot shot = new StickyShot();
        this.currentLevel.addGameObject(shot);
        return shot;
    }

    @Override
    public GameObject createWall(final double width, final double height) {
        final GameObject wall = new Wall(width, height);
        this.currentLevel.addGameObject(wall);
        return wall;
    }

    @Override
    public GameObject createPickup(final Power power) {
        final GameObject pickup = new Pickup(power);
        this.currentLevel.addGameObject(pickup);
        return pickup;
    }
  }

