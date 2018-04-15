package oopang.view.rendering.gameobject;

import oopang.commons.space.Points2D;
import oopang.commons.space.Vectors2D;
import oopang.model.gameobjects.HookShot;
import oopang.model.gameobjects.StickyShot;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.Layers;
import oopang.view.rendering.Sprite;

/**
 * 
 * Represents a Renderer for {@link Shot} {@link GameObject}s.
 *
 */
public class HookShotRenderer extends GameObjectRenderer<HookShot> {

    private static final double MAX_SHOT_HEIGHT = 100;
    private static final double OFFSET = 4;

    /**
     * Creates a new {@link Shot} Renderer given its Shot {@link GameObject}.
     * @param sprite
     *      The {@link Sprite} used to render.
     * @param gameObject
     *      The {@link Shot} {@link GameObject}.
     */
    public HookShotRenderer(final Sprite sprite, final HookShot gameObject) {
        super(sprite, gameObject);
        this.setLayer(Layers.SHOT_LAYER);

        if (gameObject instanceof StickyShot) {
            sprite.setSource(ImageID.STICKYSHOT);
        } else {
            sprite.setSource(ImageID.HOOKSHOT);
        }
        sprite.setPivot(Vectors2D.of(0, 1));
    }

    @Override
    public void render() {
        final double width = getSprite().getSourceWidth();
        final double height = getGameObject().getHeight() * getSprite().getSourceHeight() / MAX_SHOT_HEIGHT;
        getSprite().setSourceWindow(Points2D.ZERO, Vectors2D.of(width, height));
        this.getSprite().setHeight(this.getGameObject().getHeight());
        this.getSprite().setWidth(this.getGameObject().getWidth() + OFFSET);
        this.getSprite().setPosition(this.getGameObject().getPosition());
        this.getSprite().render();
    }

}
