package oopang.view.rendering.gameobject;

import oopang.model.BallColor;
import oopang.model.gameobjects.Ball;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.Sprite;
import oopang.view.rendering.SpriteSheet;

/**
 * Represents a Renderer for {@link Ball} {@link GameObject}s.
 */
public class BallRenderer extends GameObjectRenderer<Ball> {

    private static final int BALL_LAYER = 4;
    private static final int COLUMNS = 3;
    private static final int ROWS = 2;

    /**
     * Creates a new {@link Ball} Renderer given its Wall {@link GameObject}.
     * @param sprite
     *      The {@link Sprite} used to render.
     * @param gameObject
     *      The {@link Ball} {@link GameObject}.
     */
    public BallRenderer(final Sprite sprite, final Ball gameObject) {
        super(sprite, gameObject);
        this.setLayer(BALL_LAYER);
        final BallColor color = gameObject.getColor();
        sprite.setSource(ImageID.BALL);
        final SpriteSheet spriteSheet = new SpriteSheet(sprite, COLUMNS, ROWS);
        if (color == BallColor.BLUE) {
            spriteSheet.setCell(0, 0);
        } else if (color == BallColor.YELLOW) {
            spriteSheet.setCell(0, 1);
        } else if (color == BallColor.RED) {
            spriteSheet.setCell(0, 2);
        } else if (color == BallColor.GREEN) {
            spriteSheet.setCell(1, 2);
        } else if (color == BallColor.ORANGE) {
            spriteSheet.setCell(1, 0);
        } else if (color == BallColor.PURPLE) {
            spriteSheet.setCell(1, 1);
        }
    }

}
