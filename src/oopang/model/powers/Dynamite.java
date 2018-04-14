package oopang.model.powers;

import oopang.model.gameobjects.AbstractGameObjectVisitor;
import oopang.model.gameobjects.Ball;
import oopang.model.gameobjects.Player;
import oopang.model.levels.LevelManager;

/**
 * This enhancement splits all the balls in the level.
 *
 */
public final class Dynamite extends AbstractPower {
    private static final PowerTag TAG = PowerTag.DYNAMITE;
    private static final double WAIT_TIME = 0.5;
    private int remainingHits;
    private double timeToNextDestroy;

    /**
     * The constructor for the dynamite class.
     * @param powerlevel
     *      The number of times to destroy balls.
     */
    public Dynamite(final int powerlevel) {
        super(TAG);
        this.remainingHits = powerlevel;
    }

    @Override
    public void activate(final Player player) {
        super.activate(player);
        this.activateDynamite();
    }
    /**
     * This method return the power upgrade based on level.
     * @param powerLevel
     *      The powerLevel.
     * @return
     *      The Dynamite.
     */
    public static Power create(final int powerLevel) {
        return new Dynamite(powerLevel);
    }

    private void activateDynamite() {
        LevelManager.getCurrentLevel().getAllObjects().forEach(obj ->
        obj.accept(new AbstractGameObjectVisitor<Void>(null) {
            @Override
            public Void visit(final Ball ball) {
                ball.destroy();
                return null;
            }
        }));
        this.remainingHits--;
        if (this.remainingHits == 0) {
            this.deactivate();
        }
        this.timeToNextDestroy += WAIT_TIME;
    }

    @Override
    public void update(final double deltaTime) {
        timeToNextDestroy -= deltaTime;
        if (timeToNextDestroy <= 0) {
            this.activateDynamite();
            timeToNextDestroy += WAIT_TIME;
        }
    }
}

