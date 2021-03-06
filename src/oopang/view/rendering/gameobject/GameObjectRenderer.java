package oopang.view.rendering.gameobject;

import oopang.model.gameobjects.GameObject;
import oopang.view.rendering.GenericRenderer;
import oopang.view.rendering.Sprite;

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

    /**
     * Sets the position and the size of this renderer based on the linked GameObject 
     * and renders the renderer.
     */
    @Override
    public void render() {
        this.sprite.setPosition(this.gameObject.getPosition());
        this.sprite.setWidth(this.gameObject.getWidth());
        this.sprite.setHeight(this.gameObject.getHeight());
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

    /**
     * Returns the {@link Sprite} used to render the {@link GameObject}.
     * @return
     *      the {@link Sprite} object.
     */
    protected Sprite getSprite() {
        return this.sprite;
    }
}
