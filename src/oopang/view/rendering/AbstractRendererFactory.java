package oopang.view.rendering;

import oopang.model.gameobjects.Ball;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.HookShot;
import oopang.model.gameobjects.Pickup;
import oopang.model.gameobjects.Player;
import oopang.model.gameobjects.Wall;
import oopang.view.rendering.gameobject.BallRenderer;
import oopang.view.rendering.gameobject.HookShotRenderer;
import oopang.view.rendering.gameobject.PickupRenderer;
import oopang.view.rendering.gameobject.PlayerRenderer;
import oopang.view.rendering.gameobject.WallRenderer;

/**
 * Provides a base implementation of the {@link RendererFactory} interface.
 */
public abstract class AbstractRendererFactory implements RendererFactory {

    private final ImageID walltexture;

    /**
     * Create a new factory and sets the texture.
     * @param walltexture
     *      the wall texture to be used
     */
    public AbstractRendererFactory(final ImageID walltexture) {
        this.walltexture = walltexture;
    }

    @Override
    public final Renderer createGameObjectRenderer(final GameObject obj) {
        if (obj instanceof Player) {
            return new PlayerRenderer(this.createSprite(), (Player) obj);
        } else if (obj instanceof Ball) {
            return new BallRenderer(this.createSprite(), (Ball) obj);
        } else if (obj instanceof Wall) {
            return new WallRenderer(this.createSprite(), (Wall) obj, this.walltexture);
        } else if (obj instanceof HookShot) {
            return new HookShotRenderer(this.createSprite(), (HookShot) obj);
        } else if (obj instanceof Pickup) {
            return new PickupRenderer(this.createSprite(), (Pickup) obj);
        }
        return null;
    }
}
