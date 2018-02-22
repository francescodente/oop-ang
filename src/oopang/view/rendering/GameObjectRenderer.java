package oopang.view.rendering;

import oopang.model.gameobjects.GameObject;

/**
 * A decorator for sprite objects that adds {@link GameObject} behavior like automatic position update.
 * @param <G>
 *      the specific type of {@link GameObject} to render.
 */
public abstract class GameObjectRenderer<G extends GameObject> extends GenericRenderer {

    private final Sprite sprite;
    private final G gameObject;

    /**
     * Initializes generic data of this renderer.
     * @param sprite
     *      the sprite used to render the {@link GameObject}.
     * @param gameObject
     *      the {@link GameObject} to render.
     */
    public GameObjectRenderer(final Sprite sprite, final G gameObject) {
        super();
        this.sprite = sprite;
        this.gameObject = gameObject;
    }

    @Override
    public void render() {
        this.sprite.setPosition(this.gameObject.getPosition());
        this.sprite.render();
    }

    /**
     * Returns the {@link GameObject} that this object will render.
     * @return
     *      the {@link GameObject}.
     */
    protected G getGameObject() {
        return this.gameObject;
    }
}
