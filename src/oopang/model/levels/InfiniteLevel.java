package oopang.model.levels;

import java.util.Random;

import oopang.commons.PlayerTag;
import oopang.commons.space.Point2D;
import oopang.commons.space.Points2D;
import oopang.commons.space.Vector2D;
import oopang.commons.space.Vectors2D;
import oopang.model.BallColor;
import oopang.model.Model;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.GameObjectFactory;
import oopang.model.gameobjects.GameObjectFactoryDecorator;
import oopang.model.gameobjects.Player;
import oopang.model.powers.PowerTag;
import oopang.model.powers.TimedPower;

/**
 * Represents a decorator for level that spawns ball constantly.
 */
public class InfiniteLevel extends LevelDecorator {

    private static final int BALL_START_SIZE = 3;
    private static final Vector2D BALL_START_VELOCITY = Vectors2D.of(17, 0);
    private static final double INITIAL_WAIT_TIME = 12;
    private static final double MIN_WAIT_TIME = 2;
    private static final double DECREASE_RATE = 0.95;
    private static final double SPAWN_HEIGHT = 90;
    private static final double WORLD_OFFSET = 20;
    private static final double ENABLE_TIMEOUT = 1;
    private static final double START_SPAWN_TIME = .5;

    private double currentWaitTime;
    private double nextBallTimeLeft;
    private double enableTime;
    private boolean frozen;
    private int ballNumber;
    private GameObject nextBall;

    /**
     * Creates a new infinite level based on the given level instance.
     * @param baseLevel
     *      the {@link Level} object to decorate.
     */
    public InfiniteLevel(final Level baseLevel) {
        super(baseLevel);
        frozen = false;
        this.currentWaitTime = this.computeWaitTime();
        this.nextBallTimeLeft = START_SPAWN_TIME;
        this.enableTime = ENABLE_TIMEOUT;
    }

    @Override
    public void update(final double deltaTime) {
        super.update(deltaTime);
        if (!frozen) {
            if (this.nextBall != null) {
                this.enableTime -= deltaTime;
                if (this.enableTime <= 0) {
                    this.nextBall.getAllComponents().forEach(c -> c.enable());
                    enableTime = ENABLE_TIMEOUT;
                    nextBall = null;
                }
            }

            this.nextBallTimeLeft -= deltaTime;
            if (this.nextBallTimeLeft <= 0) {
                this.spawnBall();
                this.currentWaitTime = this.computeWaitTime();
                this.nextBallTimeLeft = this.currentWaitTime;
            }
        }
    }

    private double computeWaitTime() {
        return INITIAL_WAIT_TIME * Math.pow(DECREASE_RATE, this.ballNumber) + MIN_WAIT_TIME;
    }

    private void spawnBall() {
        final Random random = new Random();
        final Vector2D velocity = random.nextInt(2) == 0 ? BALL_START_VELOCITY : BALL_START_VELOCITY.flipX();
        this.nextBall = LevelManager.getCurrentLevel()
                .getGameObjectFactory()
                .createBall(BALL_START_SIZE, velocity, BallColor.randomColor());
        nextBall.setPosition(this.randomPosition());
        nextBall.getAllComponents().forEach(c -> c.disable());
        this.ballNumber++;
    }

    private Point2D randomPosition() {
        final Random rand = new Random();
        final double xvalue = (rand.nextDouble() * 2 - 1) * (Model.WORLD_WIDTH / 2 - WORLD_OFFSET);
        return Points2D.of(xvalue, SPAWN_HEIGHT);
    }

    @Override
    public GameObjectFactory getGameObjectFactory() {
        return new GameObjectFactoryDecorator(super.getGameObjectFactory()) {
            @Override
            public GameObject createPlayer(final PlayerTag tag) {
                final Player player = (Player) super.createPlayer(tag);
                player.getPickupCollectedEvent().register(p -> {
                    if (p.getPowertag() == PowerTag.FREEZE) {
                        frozen = true;
                        ((TimedPower) p).getTimeOutEvent().register(n -> frozen = false);
                    }
                });
                return player;
            }
        };
    }

}
