package oopang.model.levels;

import oopang.commons.PlayerTag;
import oopang.commons.space.Vector2D;
import oopang.model.BallColor;
import oopang.model.components.CollisionComponent;
import oopang.model.gameobjects.AbstractGameObjectVisitor;
import oopang.model.gameobjects.Ball;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.GameObjectFactory;
import oopang.model.gameobjects.GameObjectFactoryDecorator;
import oopang.model.gameobjects.GameObjectVisitor;
import oopang.model.gameobjects.Player;
import oopang.model.gameobjects.Shot;
import oopang.model.gameobjects.Wall;

/**
 * This class represent the score decorator.
 *
 */
public class ScoreLevelDecorator extends LevelDecorator {
    private static final int SCORE_MULTIPLIER = 100;
    private static final int MAX_COMBO = 4;

    private final GameObjectVisitor<Void> comboVisitor;
    private int ballserie;

    /**
     * Creates a new score Decorator based on the given level instance.
     * @param baseLevel
     *      The combo baseLevel.
     */
    public ScoreLevelDecorator(final Level baseLevel) {
        super(baseLevel);
        this.ballserie = 0;
        this.comboVisitor = new AbstractGameObjectVisitor<Void>(null) {
            @Override
            public Void visit(final Ball ball) {
                ballserie++;
                return super.visit(ball);
            }
            @Override
            public Void visit(final Wall wall) {
                ballserie = 0;
                return super.visit(wall);
            }
        };
    }

    private int computeScore() {
        if (ballserie > 0) {
            return (int) (SCORE_MULTIPLIER * Math.pow(2, ballserie <= MAX_COMBO ? ballserie : MAX_COMBO));
        }
        return SCORE_MULTIPLIER;
    }

    @Override
    public final GameObjectFactory getGameObjectFactory() {
        return new GameObjectFactoryDecorator(super.getGameObjectFactory()) {
            @Override
            public GameObject createPlayer(final PlayerTag tag) {
                final Player player = (Player) super.createPlayer(tag);
                player.getPickupCollectedEvent().register(obj ->
                addScore(SCORE_MULTIPLIER)
                );
                return player;
            }

            @Override
            public GameObject createBall(final int size, final Vector2D velocity, final BallColor color) {
                final Ball ball = (Ball) super.createBall(size, velocity, color);
                ball.getDestroyedEvent().register(obj -> 
                addScore(computeScore())
                );
                return ball;
            }

            @Override
            public Shot createHookShot() {
                final Shot shot = (Shot) super.createHookShot();
                shot.getComponent(CollisionComponent.class).ifPresent(c -> {
                    c.getCollisionEvent().register(coll -> {
                        coll.getOther().getAttachedGameObject().ifPresent(other -> {
                            other.accept(comboVisitor);
                        });
                    });
                });
                return shot;
            }
        };
    }

}
