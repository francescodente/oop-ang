package oopang.view.rendering;

import oopang.commons.space.Points2D;
import oopang.controller.DayTime;
import oopang.model.Model;

/**
 * This is a Renderer for background in javaFX.
 */
public final class BackgroundRenderer extends GenericRenderer {

    private static final int ROWS = 1;
    private static final int COLUMS = 3;
    private static final int BACKGROUND_LAYER = 0;

    private final Sprite sprite;

    /**
     * Create a new BackgroundRenderer and sets the correct image.
     * @param sprite
     *      the sprite to be used.
     * @param time
     *      the dayTime to choose which cell should be displayed.
     * @param id
     *      the imageId
     */
    public BackgroundRenderer(final Sprite sprite, final DayTime time, final ImageID id) {
        super();
        this.sprite = sprite;
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

    @Override
    public void render() {
        this.sprite.render();
    }

}
