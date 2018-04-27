package oopang.model.levels;

import oopang.commons.PlayerTag;
import oopang.model.LevelResult;
import oopang.model.components.InputComponent;
import oopang.model.gameobjects.GameObject;
import oopang.model.input.InputReader;

/**
 * Represents a decorator for levels that makes the decorated level single player.
 */
public final class SinglePlayerLevel extends GameOverLevelDecorator {

    private final InputReader playerInput;
    private final PlayerTag playerTag;

    /**
     * Creates a new single player level based on the given layer.
     * @param baseLevel
     *      the {@link Level} object to decorate.
     *@param playerTag
     *      the {@link PlayerTag} tag to assign to the player.
     * @param playerInput
     *      the {@link InputReader} from which the player of this level will retrieve input.
     */
    public SinglePlayerLevel(final Level baseLevel, final InputReader playerInput, final PlayerTag playerTag) {
        super(baseLevel);
        this.playerInput = playerInput;
        this.playerTag = playerTag;
    }

    @Override
    public void start() {
        final GameObject player = this.getGameObjectFactory().createPlayer(this.playerTag);
        player.getComponent(InputComponent.class).get().setInputReader(this.playerInput);
        player.getDestroyedEvent().register(p -> this.endLevel(LevelResult.PLAYER_DEAD));
        super.start();
    }
}
