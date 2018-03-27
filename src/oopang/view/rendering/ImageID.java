package oopang.view.rendering;

/**
 * Identifies an image stored on the disk and its path.
 */
public enum ImageID {
    /**
     * The image for the player character.
     */
    PLAYER("/player/default"),
    /**
     * The image for the balls.
     */
    BALL("/ball/allball"),
    /**
     * The image for the HookShot.
     */
    HOOKSHOT("/shot/hook"),
    /**
     * The image for the StickyShot.
     */
    STICKYSHOT("/shot/sticky"),
    /**
     * The image for the walls.
     */
    WALL("/wall/walltexture"),
    /**
     * The image for Pickup.
     */
    PICKUP("/powers/Powers"),
    /**
     * The background with Angkor Wat.
     */
    ANGKOR_WAT("/background/AngkorWat.png"),
    /**
     * The background with Antartica.
     */
    ANTARTICA("/background/Antartica"),
    /**
     * The background with Athens.
     */
    ATHENS("/background/Athens"),
    /**
     * The background with Barcelona.
     */
    BARCELONA("/background/Barcelona"),
    /**
     * The background with EasterIsland.
     */
    EASTER_ISLAND("/background/EasterIsland"),
    /**
     * The background with Egypt.
     */
    EGYPT("/background/Egypt"),
    /**
     * The background with EmeraldBuddhaTemple.
     */
    EMERALD_BUDDHA("/background/EmeraldBuddhaTemple"),
    /**
     * The background with Fuji.
     */
    FUJI("/background/Fuji"),
    /**
     * The background with Kerin.
     */
    KERIN("/background/Kerin"),
    /**
     * The background with Kilimanjaro.
     */
    KILIMANJARO("/background/Kilimanjaro"),
    /**
     * The background with Leningrad.
     */
    LENINGRAD("/background/Leningrad"),
    /**
     * The background with London.
     */
    LONDON("/background/London"),
    /**
     * The background with MayaRuins.
     */
    MAYA_RUINS("/background/MayaRuins"),
    /**
     * The background with NewYork.
     */
    NEW_YORK("/background/NewYork"),
    /**
     * The background with Paris.
     */
    PARIS("/background/Paris"),
    /**
     * The background with TajMahal.
     */
    TAJ_MAHAL("/background/TajMahal"),
    /**
     * The background with Uluru.
     */
    ULURU("/background/Uluru");

    private static final String IMAGE_PATH = "/images";
    private static final String FILE_EXTENSION = ".png";
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
        return IMAGE_PATH + this.path + FILE_EXTENSION;
    }
}
