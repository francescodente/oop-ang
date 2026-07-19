package oopang.controller.gamesession;

import java.io.IOException;
import java.util.Optional;

import oopang.controller.loader.LevelData;
import oopang.controller.loader.LevelLoader;
import oopang.model.GameOverStatus;
import oopang.model.LevelResult;
import oopang.model.Model;
import oopang.model.gameobjects.AbstractGameObjectVisitor;
import oopang.model.gameobjects.Ball;
import oopang.model.gameobjects.GameObjectVisitor;
import oopang.model.levels.LevelBuilder;
import oopang.view.View;

/**
 * Class representing a game session which lasts until the player dies. 
 * It allows the player to play in survival mode and automatically asks the loader for the Infinite Level.
 */
public final class InfiniteGameSession extends GameSession {
    private boolean levelStarted;
    private int stageCounter;
    private static final int BALL_SIZE = 3;

    /**
     * Constructor of the infinite {@link GameSession).
     * @param view
     *      The {@link view} of the Game
     * @param model
     *      The {@link Model) of the Game
     * @param isMultiPlayer
     *      Boolean representing if is or not multi player session.
     * @param loader
     *      the object used to create levels
     */
    public InfiniteGameSession(final View view, final Model model, final boolean isMultiPlayer, final LevelLoader loader) {
        super(view, model, isMultiPlayer, loader);
        this.levelStarted = false;
    }

    @Override
    public void handleGameOver(final GameOverStatus status) {
        final LevelResult result = status.getResult();
        if (result == LevelResult.PLAYER_DEAD) {
            super.addScore(status.getScore());
        }
        super.handleGameOver(status);
    }

    @Override
    protected Optional<LevelData> getNextLevel(final LevelBuilder builder) throws IOException {
        if (!this.hasNextLevel()) {
            return Optional.empty();
        }
        this.levelStarted = true;
        final GameObjectVisitor<Void> stageCheck = new AbstractGameObjectVisitor<Void>(null) {
            @Override
            public Void visit(final Ball ball) {
                if (ball.getSize() == BALL_SIZE) {
                    stageCounter++;
                }
                return super.visit(ball);
            }
        };
        final LevelData lvl = this.getLoader().loadInfiniteLevel(builder);
        lvl.getLevel().getObjectCreatedEvent().register(obj -> {
            obj.accept(stageCheck);
        });
        return Optional.of(lvl);
    }

    @Override
    public boolean hasNextLevel() {
        return !this.levelStarted;
    }

    @Override
    public int getStage() {
        return stageCounter;
    }
}
