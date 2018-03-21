package oopang.view.rendering;

/**
 * Identifies an image stored on the disk and its path.
 */
public enum ImageID {
    /**
     * The image for the player character.
     */
    PLAYER(""),
    /**
     * The image for the balls.
     */
    BALL(""),
    /**
     * The image for the HookShot.
     */
    HOOKSHOT(""),
    /**
     * The image for the StickyShot.
     */
    STICKYSHOT(""),
    /**
     * The image for the walls.
     */
    WALL(""),
    /**
     * The image for the backgrounds.
     */
    BACKGROUND("");

    private final String path;

    ImageID(final String path) {
        this.path = path;
    }

    /**
     * Returns the path for the image on disk.
     * @return
     *      the path.
     */
    public String getPath() {
        return this.path;
    }
}
