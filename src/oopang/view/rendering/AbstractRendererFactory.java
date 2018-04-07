package oopang.view.rendering;

import oopang.model.gameobjects.Ball;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.GameObjectVisitor;
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
    private final CanvasDrawer canvasDrawer;

    /**
     * Create a new factory and sets the texture.
     * @param walltexture
     *      the wall texture to be used
     *@param canvasDrawer
     *      the canvas drawer to be used
     */
    public AbstractRendererFactory(final ImageID walltexture, final CanvasDrawer canvasDrawer) {
        this.walltexture = walltexture;
        this.canvasDrawer = canvasDrawer;
    }

    @Override
    public final Renderer createGameObjectRenderer(final GameObject obj) {
        final Renderer renderer = obj.accept(new GameObjectVisitor<Renderer>() {
            @Override
            public Renderer visit(final Player player) {
                return new PlayerRenderer(createSprite(), player, canvasDrawer);
            }

            @Override
            public Renderer visit(final Ball ball) {
                return new BallRenderer(createSprite(), ball);
            }

            @Override
            public Renderer visit(final Wall wall) {
                return new WallRenderer(createSprite(), wall, walltexture);
            }

            @Override
            public Renderer visit(final HookShot shot) {
                return new HookShotRenderer(createSprite(), shot);
            }

            @Override
            public Renderer visit(final Pickup pickup) {
                return new PickupRenderer(createSprite(), pickup);
            }
        });
        this.canvasDrawer.addRenderer(renderer);
        return renderer;
    }
}
