package oopang.view.rendering.gameobject;

import oopang.model.gameobjects.Pickup;
import oopang.model.powers.PowerTag;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.Sprite;
import oopang.view.rendering.SpriteSheet;

/**
 * Represents a Renderer for {@link Ball} {@link GameObject}s.
 */
public class PickupRenderer extends GameObjectRenderer<Pickup> {

    private static final int PICKUP_LAYER = 5;
    private static final int COLUMNS = 3;
    private static final int ROWS = 2;

    /**
     * Creates a new {@link Pickup} Renderer given its Wall {@link GameObject}.
     * @param sprite
     *      The {@link Sprite} used to render.
     * @param gameObject
     *      The {@link Pickup} {@link GameObject}.
     */
    public PickupRenderer(final Sprite sprite, final Pickup gameObject) {
        super(sprite, gameObject);
        this.setLayer(PICKUP_LAYER);
        final PowerTag tag = gameObject.getPower().getPowertag();
        sprite.setSource(ImageID.PICKUP);
        final SpriteSheet spriteSheet = new SpriteSheet(sprite, COLUMNS, ROWS);
        if (tag == PowerTag.FREEZE) {
            spriteSheet.setCell(2, 0);
        } else if (tag == PowerTag.DOUBLESHOT) {
            spriteSheet.setCell(0, 0);
        } else if (tag == PowerTag.ADHESIVESHOT) {
            spriteSheet.setCell(0, 1);
        } else if (tag == PowerTag.DOUBLESPEED) {
            spriteSheet.setCell(2, 1);
        } else if (tag == PowerTag.TIMEDSHIELD) {
            spriteSheet.setCell(1, 0);
        } /*else if (tag == PowerTag.DYNAMITE) {
            spriteSheet.setCell(1, 1);*/
        }
    }

