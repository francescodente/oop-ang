package oopang.model.powers;

import oopang.commons.LevelManager;
import oopang.model.components.CollisionComponent;
import oopang.model.components.MovementComponent;
import oopang.model.gameobjects.Ball;
import oopang.model.gameobjects.Player;
/**
 * This enhancement freezes all the balls in the level for a certain period of time.
 */
public final class Freeze extends PowerTimed {
    private static final PowerTag TAG = PowerTag.FREEZE;
    private static final int INITIALVALUE = 3;
    private static final double TIMEFEE = 0.5;
/**
 * This constructor set time.
 * @param timeout
 *       Is the duration of enhancements.
 */
    private Freeze(final double timeout) {
        super(timeout, TAG);
    }

    @Override
    public void activate(final Player player) {
        super.activate(player);
        LevelManager.getCurrentLevel().getAllObjects()
            .filter(obj -> Ball.class.isInstance(obj))
            .forEach(obj -> obj.getComponent(MovementComponent.class).ifPresent(c -> c.disable()));
        player.getComponent(CollisionComponent.class).ifPresent(c -> c.disable());
    }

    @Override
    protected void deactivate() {
        super.deactivate();
        LevelManager.getCurrentLevel().getAllObjects()
        .filter(obj -> Ball.class.isInstance(obj))
        .forEach(obj -> obj.getComponent(MovementComponent.class).ifPresent(c -> c.enable()));
        this.getPlayer().getComponent(CollisionComponent.class).ifPresent(c -> c.enable());
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
       return  new Freeze(calculateTimeout(powerlevel));
    }

}
