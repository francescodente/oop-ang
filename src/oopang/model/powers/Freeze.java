package oopang.model.powers;

import java.util.function.Supplier;

import oopang.commons.events.EventHandler;
import oopang.model.gameobjects.Ball;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.GameObjectVisitor;
import oopang.model.gameobjects.AbstractGameObjectVisitor;
import oopang.model.gameobjects.Player;
import oopang.model.levels.LevelManager;

/**
 * This enhancement freezes all the balls in the level for a certain period of time.
 */
public final class Freeze extends TimedPower {
    private static final PowerTag TAG = PowerTag.FREEZE;
    private static final int INITIALVALUE = 3;
    private static final double TIMEFEE = 0.5;
    private static final Supplier<Double> FREEZE_MULTIPLIER = () -> 0.0;
    private final GameObjectVisitor<Void> activator;
    private final GameObjectVisitor<Void> deactivator;
    private final EventHandler<GameObject> frieza;


    /**
     * This constructor set time.
     * @param timeout
     *       Is the duration of enhancements.
     */
    private Freeze(final double timeout) {
        super(timeout, TAG);
        this.activator = new AbstractGameObjectVisitor<Void>(null) {
            @Override
            public Void visit(final Ball ball) {
                freezeBall(ball);
                return null;
            }
            @Override
            public Void visit(final Player player) {
                player.setInvulnerable(true);
                return null;
            }
        };
        this.deactivator = new AbstractGameObjectVisitor<Void>(null) {
            @Override
            public Void visit(final Ball ball) {
                unlockBall(ball);
                return null;
            }
            @Override
            public Void visit(final Player player) {
                player.setInvulnerable(false);
                return null;
            }
        };
        this.frieza = obj -> obj.accept(activator);
    }

    @Override
    public void activate(final Player player) {
        super.activate(player);
        LevelManager.getCurrentLevel()
            .getAllObjects()
            .forEach(o -> o.accept(activator));
        LevelManager.getCurrentLevel().getObjectCreatedEvent().register(frieza);
    }

    @Override
    public void deactivate() {
        super.deactivate();
        LevelManager.getCurrentLevel()
            .getAllObjects()
            .forEach(o -> o.accept(deactivator));
        LevelManager.getCurrentLevel().getObjectCreatedEvent().unregister(frieza);
    }

    private void freezeBall(final Ball ball) {
        ball.addTimeMultiplier(FREEZE_MULTIPLIER);
    }

    private void unlockBall(final Ball ball) {
        ball.removeTimeMultiplier(FREEZE_MULTIPLIER);
    }

    private static double calculateTimeout(final int powerlevel) {
        return INITIALVALUE + (TIMEFEE * (powerlevel - 1));
    }

    /**
     * This method return the power upgrade based on level.
     * @param powerlevel
     *      The power level.
     * @return 
     *      The freeze power.
     */
    public static Power create(final int powerlevel) {
       return new Freeze(calculateTimeout(powerlevel));
    }

}
