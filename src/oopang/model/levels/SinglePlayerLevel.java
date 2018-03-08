package oopang.model.levels;

import oopang.model.gameobjects.GameObject;
import oopang.model.input.InputReader;

/**
 * Represents a decorator for levels that makes the decorated level single player.
 */
public class SinglePlayerLevel extends LevelDecorator {

    /**
     * Creates a new single player level based on the given layer.
     * @param baseLevel
     *      the {@link Level} object to decorate.
     * @param playerInput
     *      the {@link InputReader} from which the player of this level will retrieve input.
     */
    public SinglePlayerLevel(final Level baseLevel, final InputReader playerInput) {
        super(baseLevel);
        final GameObject player = this.getGameObjectFactory().createPlayer();
        //player.getComponent(InputComponent.class).get().setInputReader(playerInput);
    }
}
