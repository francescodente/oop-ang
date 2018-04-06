package oopang.view.rendering;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Identifies an image stored on the disk and its path.
 */
public enum ImageID {
    /**
     * The image for the player 1 character.
     */
    PLAYER1("/player/player1", false),
    /**
     * The image for the player 1 character.
     */
    PLAYER2("/player/player2", false),
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
     * The image for blue walls.
     */
    BLUE_WALL("/wall/blue", false),
    /**
     * The image for red walls.
     */
    RED_WALL("/wall/red", false),
    /**
     * The image for yellow walls.
     */
    YELLOW_WALL("/wall/yellow", false),
    /**
     * The image for green walls.
     */
    GREEN_WALL("/wall/green", false),
    /**
     * The image for purple walls.
     */
    PURPLE_WALL("/wall/purple", false),
    /**
     * The image for orange walls.
     */
    ORANGE_WALL("/wall/orange", false),
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
    ULURU("/background/Uluru", true),
    /**
     * The image for life.
     */
    HEART("/heart", false);

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

    /**
     * Returns a random ImageID representig a wall texture.
     * @return
     *      a random wall texture imageId
     */
    public static ImageID getRandomWallTexture() {
        final Random rand = new Random();
        final List<ImageID> list = Arrays.asList(BLUE_WALL, RED_WALL, YELLOW_WALL, GREEN_WALL, ORANGE_WALL, PURPLE_WALL);
        return list.get(rand.nextInt(list.size()));
    }
}
