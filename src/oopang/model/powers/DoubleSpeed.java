package oopang.model.powers;

import java.util.function.Supplier;

import oopang.commons.events.EventHandler;
import oopang.model.gameobjects.AbstractGameObjectVisitor;
import oopang.model.gameobjects.Ball;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.GameObjectVisitor;
import oopang.model.gameobjects.Player;
import oopang.model.levels.LevelManager;
/**
 * 
 *
 */
public final class DoubleSpeed extends TimedPower {
    private static final double SLOW_TIME_MULTIPLIER = 0.3;
    private static final PowerTag TAG = PowerTag.DOUBLESPEED;
    private static final int INITIALVALUE = 4;
    private static final double TIMEFEE = 0.5;
    private static final Supplier<Double> MULTIPLIER = () -> SLOW_TIME_MULTIPLIER;
    private final GameObjectVisitor<Void> activator;
    private final GameObjectVisitor<Void> deactivator;
    private final EventHandler<GameObject> slower;
    /**
     * This constructor set time.
     * @param timeout 
     *       Is the duration of enhancements.
     */
    public DoubleSpeed(final double timeout) {
        super(timeout, TAG);
        activator = new AbstractGameObjectVisitor<Void>(null) {
            @Override
            public Void visit(final Ball ball) {
                slowBall(ball);
                return null;
            }
        };
        deactivator = new AbstractGameObjectVisitor<Void>(null) {
            @Override
            public Void visit(final Ball ball) {
                unlockBall(ball);
                return null;
            }
        };
        slower = obj -> obj.accept(activator);

    }
    @Override
    public void activate(final Player player) {
        super.activate(player);
        LevelManager.getCurrentLevel()
            .getAllObjects()
            .forEach(o -> o.accept(activator));
        LevelManager.getCurrentLevel().getObjectCreatedEvent().register(slower);
    }

    @Override
    public void deactivate() {
        super.deactivate();
        LevelManager.getCurrentLevel()
            .getAllObjects()
            .forEach(o -> o.accept(deactivator));
        LevelManager.getCurrentLevel().getObjectCreatedEvent().unregister(slower);
    }

    private void slowBall(final Ball ball) {
        ball.addTimeMultiplier(MULTIPLIER);
    }

    private void unlockBall(final Ball ball) {
        ball.removeTimeMultiplier(MULTIPLIER);
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
       return  new DoubleSpeed(calculateTimeout(powerlevel));
    }
}
