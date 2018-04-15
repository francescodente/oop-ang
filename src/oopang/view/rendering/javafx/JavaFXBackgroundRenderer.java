package oopang.view.rendering.javafx;

import oopang.commons.space.Points2D;
import oopang.controller.DayTime;
import oopang.model.Model;
import oopang.view.rendering.GenericBackgroundRenderer;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.Sprite;
import oopang.view.rendering.SpriteSheet;

/**
 * This is a Renderer for background in javaFX.
 */
public class JavaFXBackgroundRenderer extends GenericBackgroundRenderer {

    private static final int ROWS = 1;
    private static final int COLUMS = 3;
    private static final int BACKGROUND_LAYER = 0;

    /**
     * Create a new BackgroundRenderer and sets the correct image.
     * @param sprite
     *      the sprite to be used.
     * @param time
     *      the dayTime to choose which cell should be displayed.
     * @param id
     *      the imageId
     */
    public JavaFXBackgroundRenderer(final Sprite sprite, final DayTime time, final ImageID id) {
        super(sprite);
        sprite.setSource(id);
        final SpriteSheet sheet = new SpriteSheet(sprite, COLUMS, ROWS);
        if (time == DayTime.MORNING) {
            sheet.setCell(0, 0);
        } else if (time == DayTime.AFTERNOON) {
            sheet.setCell(1, 0);
        } else if (time == DayTime.NIGHT) {
            sheet.setCell(2, 0);
        }
        this.setLayer(BACKGROUND_LAYER);
        sprite.setPosition(Points2D.of(0, Model.WORLD_HEIGHT / 2));
        sprite.setWidth(Model.TOTAL_WIDTH);
        sprite.setHeight(Model.TOTAL_HEIGHT);
    }

}
