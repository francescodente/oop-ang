package oopang.model.gameobjects;

import oopang.commons.PlayerTag;
import oopang.commons.space.Vector2D;
import oopang.model.BallColor;
import oopang.model.powers.Power;

/**
 * Describes a decorator for GameObjectFactory objects. Every method of the {@link GameObjectFactory} interface
 * is implemented by delegating the operation to the decorated factory.
 */
public abstract class GameObjectFactoryDecorator implements GameObjectFactory {

    private final GameObjectFactory innerFactory;

    /**
     * Creates a new factory that decorates the given one.
     * @param baseFactory
     *      the factory to decorate.
     */
    public GameObjectFactoryDecorator(final GameObjectFactory baseFactory) {
        this.innerFactory = baseFactory;
    }

    @Override
    public GameObject createPlayer(final PlayerTag tag) {
        return this.innerFactory.createPlayer(tag);
    }

    @Override
    public GameObject createBall(final int size, final Vector2D velocity, final BallColor color) {
        return this.innerFactory.createBall(size, velocity, color);
    }

    @Override
    public Shot createHookShot() {
        return this.innerFactory.createHookShot();
    }

    @Override
    public Shot createStickyShot() {
        return this.innerFactory.createStickyShot();
    }

    @Override
    public GameObject createWall(final double width, final double height) {
        return this.innerFactory.createWall(width, height);
    }

    @Override
    public GameObject createPickup(final Power power) {
        return this.innerFactory.createPickup(power);
    }

}
