package oopang.controller;

import java.util.Optional;

import oopang.controller.loader.LevelData;
import oopang.model.GameOverStatus;
import oopang.model.LevelResult;
import oopang.model.Model;
import oopang.view.View;

/**
 * Class representing a InfiniteGameSession.
 */
public final class InfiniteGameSession extends GameSession {
    private boolean levelStarted;

    /**
     * Constructor of the infinite {@link GameSession).
     * @param view
     *      The {@link view} of the Game
     * @param model
     *      The {@link Model) of the Game
     * @param isMultiPlayer
     *      Boolean representing if is or not multi player session.
     */
    public InfiniteGameSession(final View view, final Model model, final boolean isMultiPlayer) {
        super(view, model, isMultiPlayer);
        this.levelStarted = false;
    }

    @Override
    protected void handleGameOver(final GameOverStatus status) {
       final LevelResult result = status.getResult();
       if (result == LevelResult.PLAYER_DEAD) {
           super.addScore(status.getScore());
       }
    }

    @Override
    protected Optional<LevelData> getNextLevel() throws Exception {
        if (this.levelStarted) {
            return Optional.empty();
        }
        this.levelStarted = true;
        return Optional.of(this.getLoader().loadInfiniteLevel());
    }

}
