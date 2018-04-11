package oopang.model.powers;

import oopang.commons.events.EventHandler;
import oopang.model.components.GravityComponent;
import oopang.model.components.MovementComponent;
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
    private final EventHandler<GameObject> freezer;

    /**
     * This constructor set time.
     * @param timeout
     *       Is the duration of enhancements.
     */
    private Freeze(final double timeout) {
        super(timeout, TAG);
        this.freezer = obj -> {
            if (obj instanceof Ball) {
                this.freezeBall(obj);
            }
        };
    }


    @Override
    public void activate(final Player player) {
        super.activate(player);
        final GameObjectVisitor<Void> activator = new AbstractGameObjectVisitor<Void>(null) {
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
        LevelManager.getCurrentLevel()
            .getAllObjects()
            .forEach(o -> o.accept(activator));
        LevelManager.getCurrentLevel().getObjectCreatedEvent().register(this.freezer);
    }

    @Override
    public void deactivate() {
        super.deactivate();
        final GameObjectVisitor<Void> deactivator = new AbstractGameObjectVisitor<Void>(null) {
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
        LevelManager.getCurrentLevel()
            .getAllObjects()
            .forEach(o -> o.accept(deactivator));
        LevelManager.getCurrentLevel().getObjectCreatedEvent().unregister(freezer);
    }

    private void freezeBall(final GameObject ball) {
        ball.getComponent(MovementComponent.class).ifPresent(c -> c.disable());
        ball.getComponent(GravityComponent.class).ifPresent(c -> c.disable());
    }

    private void unlockBall(final GameObject ball) {
        ball.getComponent(MovementComponent.class).ifPresent(c -> c.enable());
        ball.getComponent(GravityComponent.class).ifPresent(c -> c.enable());
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
