package oopang.view.rendering;

import oopang.model.gameobjects.Ball;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.GameObjectVisitor;
import oopang.model.gameobjects.HookShot;
import oopang.model.gameobjects.Pickup;
import oopang.model.gameobjects.Player;
import oopang.model.gameobjects.Wall;
import oopang.model.powers.TimedPower;
import oopang.view.rendering.gameobject.BallRenderer;
import oopang.view.rendering.gameobject.HookShotRenderer;
import oopang.view.rendering.gameobject.PickupRenderer;
import oopang.view.rendering.gameobject.PlayerRenderer;
import oopang.view.rendering.gameobject.WallRenderer;
import oopang.view.rendering.powers.FrozenFrameRenderer;
import oopang.view.rendering.powers.ShieldRenderer;
import oopang.view.rendering.powers.UltraInstinctRenderer;

/**
 * Provides a base implementation of the {@link RendererFactory} interface.
 */
public abstract class AbstractRendererFactory implements RendererFactory {

    private final CanvasDrawer canvasDrawer;
    private final GameObjectVisitor<Renderer> rendererGenerator;

    /**
     * Create a new factory and sets the texture.
     * @param wallTexture
     *      the wall texture to be used
     *@param canvasDrawer
     *      the canvas drawer to be used
     */
    public AbstractRendererFactory(final ImageID wallTexture, final CanvasDrawer canvasDrawer) {
        this.canvasDrawer = canvasDrawer;
        this.rendererGenerator = new GameObjectVisitor<Renderer>() {
            @Override
            public Renderer visit(final Player player) {
                return new PlayerRenderer(generateSprite(), player, canvasDrawer);
            }

            @Override
            public Renderer visit(final Ball ball) {
                return new BallRenderer(generateSprite(), ball);
            }

            @Override
            public Renderer visit(final Wall wall) {
                return new WallRenderer(generateSprite(), wall, wallTexture);
            }

            @Override
            public Renderer visit(final HookShot shot) {
                return new HookShotRenderer(generateSprite(), shot);
            }

            @Override
            public Renderer visit(final Pickup pickup) {
                return new PickupRenderer(generateSprite(), pickup);
            }
        };
    }

    @Override
    public final Renderer createGameObjectRenderer(final GameObject obj) {
        final Renderer renderer = obj.accept(this.rendererGenerator);
        this.canvasDrawer.addRenderer(renderer);
        return renderer;
    }

    /**
     * Generate a sprite without adding it to canvas.
     * @return
     *      a sprite
     */
    protected abstract Sprite generateSprite();

    @Override
    public final Sprite createSprite() {
        final Sprite sprite = this.generateSprite();
        this.canvasDrawer.addRenderer(sprite);
        return sprite;
    }

    @Override
    public final Renderer createTimedPowerRenderer(final TimedPower power, final Player player) {
        Renderer renderer;
        switch (power.getPowertag()) {
        case TIMEDSHIELD: 
            renderer = new ShieldRenderer(this.generateSprite(), player, power, this.canvasDrawer);
            break;
        case DOUBLESPEED: 
            renderer = new UltraInstinctRenderer(this.generateSprite(), player, power, this.canvasDrawer);
            break;
        case FREEZE: 
            renderer = new FrozenFrameRenderer(this.generateSprite(), player, power, this.canvasDrawer);
            break;
        default:
            renderer = null;
            break;
        }
        this.canvasDrawer.addRenderer(renderer);
        return renderer;
    }
}
