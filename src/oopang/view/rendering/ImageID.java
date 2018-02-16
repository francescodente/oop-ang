package oopang.view.rendering;

/**
 * Identifies an image stored on the disk and its path.
 */
public enum ImageID {
    /**
     * The image for the player character.
     */
    PLAYER("");

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
