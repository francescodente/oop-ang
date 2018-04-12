package oopang.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class that contains constants used for the User.
 */
public final class InstallManager {

    private static final String SEPARATOR = System.getProperty("file.separator");

    /**
     * Static field that contains the route path of the user.
     */
    public static final String APP_PATH = System.getProperty("user.home")
            + SEPARATOR
            + ".oopang";

    /**
     * The path to the leaderboard directory.
     */
    public static final String LEADERBOARD_DIR = APP_PATH
            + SEPARATOR
            + "leaderboard";

    /**
     * The path to the users directory.
     */
    public static final String USERS_DIR = APP_PATH
            + SEPARATOR
            + "users";

    /**
     * Static field that contains the file of the users list.
     */
    public static final String USER_LIST_FILE = USERS_DIR
            + SEPARATOR
            + ".user_list";

    /**
     * Static field that contains the file with points of the Story Mode of the user.
     */
    public static final String STORY_FILE = LEADERBOARD_DIR
            + SEPARATOR
            + "story.bin";

    /**
     * Static field that contains the file with points of the user of the Survival Mode.
     */
    public static final String SURVIVAL_FILE = LEADERBOARD_DIR
            + SEPARATOR
            + "survival.bin";

    private InstallManager() {
        // can not create a InstallManager object.
    }

    /**
     * Static method that get user file.
     * @param userName
     *       name of the user.
     * @return
     *      the couple userName and hashcode_password of the user.
     */
    public static String getUserFile(final String userName) {
        return USERS_DIR
                + SEPARATOR
                + userName + ".bin";
    }

    /**
     * Sets up the application by creating app directories an utility files.
     */
    public static void setupApplication() {
        try {
            Files.createDirectories(Paths.get(LEADERBOARD_DIR));
            Files.createDirectories(Paths.get(USERS_DIR));
            if (!Files.exists(Paths.get(USER_LIST_FILE))) {
                Files.createFile(Paths.get(USER_LIST_FILE));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
