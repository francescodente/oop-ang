package oopang.controller;

import java.nio.file.Paths;

/**
 * 
 */
public class InstallManager {

    private static final String SEPARATOR = System.getProperty("file.separator");

    /**
     * 
     */
    public static final String APP_PATH = System.getProperty("user.home")
            + SEPARATOR
            + ".oopang"
            + SEPARATOR;

    /**
     * 
     */
    public static final String USER_LIST_FILE = APP_PATH
            + "users"
            + SEPARATOR
            + ".user_list";

    /**
     * 
     */
    public static final String STORY_FILE = APP_PATH
            + "leaderbord"
            + SEPARATOR
            + "story.bin";

    /**
     * 
     */
    public static final String SURVIVAL_FILE = APP_PATH
            + "leaderboard"
            + SEPARATOR
            + "survaival.bin";

    /**
     * Static method that get user file.
     * @param userName
     *       name of the user.
     * @return
     *      the 
     */
    public static String getUserFile(final String userName) {
        return APP_PATH
                + "users"
                + SEPARATOR
                + userName + ".bin";
    }
}
