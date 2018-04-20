package oopang.view.rendering.gameobject;

import oopang.commons.Timeable;
import oopang.model.gameobjects.Pickup;
import oopang.model.powers.PowerTag;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.Layers;
import oopang.view.rendering.Sprite;
import oopang.view.rendering.SpriteSheet;

/**
 * Represents a Renderer for {@link Ball} {@link GameObject}s.
 */
public class PickupRenderer extends GameObjectRenderer<Pickup> {

    private static final int COLUMNS = 3;
    private static final int ROWS = 2;
    private static final double BLINK_START = 2;
    private static final double BLINK_RATE = 0.2;
    private boolean blink;

    /**
     * Creates a new {@link Pickup} Renderer given its Wall {@link GameObject}.
     * @param sprite
     *      The {@link Sprite} used to render.
     * @param gameObject
     *      The {@link Pickup} {@link GameObject}.
     */
    public PickupRenderer(final Sprite sprite, final Pickup gameObject) {
        super(sprite, gameObject);
        this.blink = true;
        ((Timeable) this.getGameObject()).getTimeChangedEvent().register(t -> {
            final double time = gameObject.getRemainingTime();
            if (time <= BLINK_START) {
                blink = Math.round(BLINK_START - time / BLINK_RATE) % 2 != 0;
            } else {
                this.blink = false;
            }
        });
        this.setLayer(Layers.PICKUP_LAYER);
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
        } else if (tag == PowerTag.DYNAMITE) {
            spriteSheet.setCell(1, 1);
        }
    }
    @Override
    public void render() {
        if (!blink) {
            super.render();
        }
    }
}

