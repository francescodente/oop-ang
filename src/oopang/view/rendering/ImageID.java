package oopang.view.rendering;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Identifies an image stored on the disk and its path.
 * Can return a random Backround and a random WallTexture.
 */
public enum ImageID {
    /**
     * The image for the player 1 character.
     */
    PLAYER1("/player/player1.png", false),
    /**
     * The image for the player 1 character.
     */
    PLAYER2("/player/player2.png", false),
    /**
     * The image for the balls.
     */
    BALL("/ball/allball.png", false),
    /**
     * The image for the HookShot.
     */
    HOOKSHOT("/shot/hook.png", false),
    /**
     * The image for the StickyShot.
     */
    STICKYSHOT("/shot/sticky.png", false),
    /**
     * The image for blue walls.
     */
    BLUE_WALL("/wall/blue.png", false),
    /**
     * The image for red walls.
     */
    RED_WALL("/wall/red.png", false),
    /**
     * The image for yellow walls.
     */
    YELLOW_WALL("/wall/yellow.png", false),
    /**
     * The image for green walls.
     */
    GREEN_WALL("/wall/green.png", false),
    /**
     * The image for purple walls.
     */
    PURPLE_WALL("/wall/purple.png", false),
    /**
     * The image for orange walls.
     */
    ORANGE_WALL("/wall/orange.png", false),
    /**
     * The image for Pickup.
     */
    PICKUP("/powers/Powers.png", false),
    /**
     * The background with Angkor Wat.
     */
    ANGKOR_WAT("/background/AngkorWat.png", true),
    /**
     * The background with Antartica.
     */
    ANTARTICA("/background/Antartica.png", true),
    /**
     * The background with Athens.
     */
    ATHENS("/background/Athens.png", true),
    /**
     * The background with Barcelona.
     */
    BARCELONA("/background/Barcelona.png", true),
    /**
     * The background with EasterIsland.
     */
    EASTER_ISLAND("/background/EasterIsland.png", true),
    /**
     * The background with Egypt.
     */
    EGYPT("/background/Egypt.png", true),
    /**
     * The background with EmeraldBuddhaTemple.
     */
    EMERALD_BUDDHA("/background/EmeraldBuddhaTemple.png", true),
    /**
     * The background with Fuji.
     */
    FUJI("/background/Fuji.png", true),
    /**
     * The background with Kerin.
     */
    KERIN("/background/Kerin.png", true),
    /**
     * The background with Kilimanjaro.
     */
    KILIMANJARO("/background/Kilimanjaro.png", true),
    /**
     * The background with Leningrad.
     */
    LENINGRAD("/background/Leningrad.png", true),
    /**
     * The background with London.
     */
    LONDON("/background/London.png", true),
    /**
     * The background with MayaRuins.
     */
    MAYA_RUINS("/background/MayaRuins.png", true),
    /**
     * The background with NewYork.
     */
    NEW_YORK("/background/NewYork.png", true),
    /**
     * The background with Paris.
     */
    PARIS("/background/Paris.png", true),
    /**
     * The background with TajMahal.
     */
    TAJ_MAHAL("/background/TajMahal.png", true),
    /**
     * The background with Uluru.
     */
    ULURU("/background/Uluru.png", true),
    /**
     * The image for life.
     */
    HEART("/heart.png", false),
    /**
     * The image for the shield sprite.
     */
    SHIELD("/powers/bubbleshield.png", false),
    /**
     * The image for the ultraistinct sprite.
     */
    ULTRA_INSTINCT("/powers/auragif.gif", false),
    /**
     * The image for the frozen power effect.
     */
    FROZEN_FRAME("/powers/frozenFrame.png", false);

    private static final String IMAGE_PATH = "/images";
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
        return IMAGE_PATH + this.path;
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
