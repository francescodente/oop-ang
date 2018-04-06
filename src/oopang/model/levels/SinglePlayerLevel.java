package oopang.model.levels;

import oopang.model.LevelResult;
import oopang.model.components.InputComponent;
import oopang.model.gameobjects.GameObject;
import oopang.model.input.InputReader;

/**
 * Represents a decorator for levels that makes the decorated level single player.
 */
public class SinglePlayerLevel extends GameOverLevelDecorator {

    private final InputReader playerInput;

    /**
     * Creates a new single player level based on the given layer.
     * @param baseLevel
     *      the {@link Level} object to decorate.
     * @param playerInput
     *      the {@link InputReader} from which the player of this level will retrieve input.
     */
    public SinglePlayerLevel(final Level baseLevel, final InputReader playerInput) {
        super(baseLevel);
        this.playerInput = playerInput;
    }

    @Override
    public void start() {
        final GameObject player = this.getGameObjectFactory().createPlayer();
        player.getComponent(InputComponent.class).get().setInputReader(this.playerInput);
        player.getDestroyedEvent().register(p -> this.endLevel(LevelResult.PLAYER_DEAD));
        super.start();
    }
}
