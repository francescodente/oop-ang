package oopang.view.rendering;

import oopang.commons.space.Points2D;
import oopang.commons.space.Vectors2D;

/**
 * Represents an Utility class for managing sprite-sheets, i.e. images that contain multiple sub-images
 * arranged in a grid. The sprite should have its source image already loaded when an instance of this
 * class is created.
 */
public final class SpriteSheet {
    private final Sprite sprite;
    private final double cellWidth;
    private final double cellHeight;

    /**
     * Creates a new sprite sheet helper given the number of rows and columns to divide the source image.
     * @param sprite
     *      The {@link Sprite} object to work on.
     * @param columns
     *      The number of columns.
     * @param rows
     *      The number of rows.
     */
    public SpriteSheet(final Sprite sprite, final int columns, final int rows) {
        this.sprite = sprite;
        this.cellWidth = sprite.getSourceWidth() / columns;
        this.cellHeight = sprite.getSourceHeight() / rows;
    }

    /**
     * Sets the source window to be a cell defined by its coordinates.
     * @param x
     *      the x coordinate of the cell.
     * @param y
     *      the y coordinate of the cell.
     */
    public void setCell(final int x, final int y) {
        this.sprite.setSourceWindow(
                Points2D.of(x * this.cellWidth, y * this.cellHeight),
                Vectors2D.of(this.cellWidth, this.cellHeight));
    }
}
