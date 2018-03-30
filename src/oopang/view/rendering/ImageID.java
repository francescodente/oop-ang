package oopang.view.rendering;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Identifies an image stored on the disk and its path.
 */
public enum ImageID {
    /**
     * The image for the player character.
     */
    PLAYER("/player/default", false),
    /**
     * The image for the balls.
     */
    BALL("/ball/allball", false),
    /**
     * The image for the HookShot.
     */
    HOOKSHOT("/shot/hook", false),
    /**
     * The image for the StickyShot.
     */
    STICKYSHOT("/shot/sticky", false),
    /**
     * The image for the walls.
     */
    WALL("/wall/walltexture", false),
    /**
     * The image for Pickup.
     */
    PICKUP("/powers/Powers", false),
    /**
     * The background with Angkor Wat.
     */
    ANGKOR_WAT("/background/AngkorWat", true),
    /**
     * The background with Antartica.
     */
    ANTARTICA("/background/Antartica", true),
    /**
     * The background with Athens.
     */
    ATHENS("/background/Athens", true),
    /**
     * The background with Barcelona.
     */
    BARCELONA("/background/Barcelona", true),
    /**
     * The background with EasterIsland.
     */
    EASTER_ISLAND("/background/EasterIsland", true),
    /**
     * The background with Egypt.
     */
    EGYPT("/background/Egypt", true),
    /**
     * The background with EmeraldBuddhaTemple.
     */
    EMERALD_BUDDHA("/background/EmeraldBuddhaTemple", true),
    /**
     * The background with Fuji.
     */
    FUJI("/background/Fuji", true),
    /**
     * The background with Kerin.
     */
    KERIN("/background/Kerin", true),
    /**
     * The background with Kilimanjaro.
     */
    KILIMANJARO("/background/Kilimanjaro", true),
    /**
     * The background with Leningrad.
     */
    LENINGRAD("/background/Leningrad", true),
    /**
     * The background with London.
     */
    LONDON("/background/London", true),
    /**
     * The background with MayaRuins.
     */
    MAYA_RUINS("/background/MayaRuins", true),
    /**
     * The background with NewYork.
     */
    NEW_YORK("/background/NewYork", true),
    /**
     * The background with Paris.
     */
    PARIS("/background/Paris", true),
    /**
     * The background with TajMahal.
     */
    TAJ_MAHAL("/background/TajMahal", true),
    /**
     * The background with Uluru.
     */
    ULURU("/background/Uluru", true);

    private static final String IMAGE_PATH = "/images";
    private static final String FILE_EXTENSION = ".png";
    private final String path;
    private final boolean backgroundOK;

    ImageID(final String path, final boolean isBackground) {
        this.path = path;
        this.backgroundOK = isBackground;
    }

    /**
     * Returns the path for the image on disk.
     * @return
     *      the path.
     */
    public String getPath() {
        return IMAGE_PATH + this.path + FILE_EXTENSION;
    }

    /**
     * True if the ImageID is a background.
     * @return 
     *      a boolean representing if the Image is a background or not.
     */
    public boolean isBackground() {
        return this.backgroundOK;
    }

    /**
     * Returns a random ImageID representig a background.
     * @return
     *      a random backround imageId
     */
    public static ImageID getRandomBackground() {
        final Random rand = new Random();
        final List<ImageID> list = Stream.of(ImageID.values()).filter(id -> id.isBackground()).collect(Collectors.toList());
        return list.get(rand.nextInt(list.size()));
    }
}
