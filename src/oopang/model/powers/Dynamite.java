package oopang.model.powers;

import oopang.model.gameobjects.AbstractGameObjectVisitor;
import oopang.model.gameobjects.Ball;
import oopang.model.gameobjects.Player;
import oopang.model.levels.LevelManager;

/**
 * This enhancement splits all the balls in the level.
 *
 */
public final class Dynamite extends PowerInstant {
    private static final PowerTag TAG = PowerTag.DYNAMITE;
    /**
     * The constructor for the dynamite class.
     */
    public Dynamite() {
        super(TAG);
    }

    @Override
    public void activate(final Player player) {
        super.activate(player);
        LevelManager.getCurrentLevel().getAllObjects().forEach(obj ->
        obj.accept(new AbstractGameObjectVisitor<Void>(null) {
            @Override
            public Void visit(final Ball ball) {
                ball.destroy();
                return null;
            }
        }));
    }
    /**
     * This method return the power upgrade based on level.
     * @param powerLevel
     *      The powerLevel.
     * @return
     *      The Dynamite.
     */
    public static Power create(final int powerLevel) {
        return new Dynamite();
    }
}

