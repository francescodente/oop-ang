package oopang.model.levels;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import oopang.commons.space.Point2D;
import oopang.commons.space.Vector2D;
import oopang.model.BallColor;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.GameObjectFactory;
import oopang.model.gameobjects.GameObjectFactoryDecorator;
import oopang.model.powers.Power;

/**
 * Describes a decorator for levels that adds pick up generation functionality.
 */
public final class PickUpGeneratingLevel extends LevelDecorator {

    private static final int MIN_BALLS = 4;
    private static final int MAX_BALLS = 10;

    private final List<Supplier<Power>> availablePowers;
    private int ballsToNextPickUp;

    /**
     * Creates a new PickUp generating level based on the given one.
     * @param baseLevel
     *      the {@link Level} object to decorate.
     * @param availablePowers
     *      the list of all available power for this level.
     */
    public PickUpGeneratingLevel(final Level baseLevel, final List<Supplier<Power>> availablePowers) {
        super(baseLevel);
        this.availablePowers = availablePowers;
        this.resetPickUpTimer();
    }

    private void resetPickUpTimer() {
        final Random rng = new Random();
        this.ballsToNextPickUp = rng.nextInt(MAX_BALLS - MIN_BALLS + 1) + MIN_BALLS;
    }

    private void spawnPickUp(final Point2D position) {
        final GameObject pickUp = LevelManager.getCurrentLevel()
                .getGameObjectFactory()
                .createPickup(this.chooseRandomPower());
        pickUp.setPosition(position);
    }

    private Power chooseRandomPower() {
        final Random rng = new Random();
        return this.availablePowers.get(rng.nextInt(this.availablePowers.size())).get();
    }

    @Override
    public GameObjectFactory getGameObjectFactory() {
        return new GameObjectFactoryDecorator(super.getGameObjectFactory()) {
            @Override
            public GameObject createBall(final int size, final Vector2D velocity, final BallColor color) {
                final GameObject ball = super.createBall(size, velocity, color);
                ball.getDestroyedEvent().register(b -> {
                    ballsToNextPickUp--;
                    if (ballsToNextPickUp <= 0) {
                        resetPickUpTimer();
                        spawnPickUp(b.getPosition());
                    }
                });
                return ball;
            }
        };
    }

}
