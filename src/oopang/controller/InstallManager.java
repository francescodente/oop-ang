package oopang.controller;

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
            + ".oopang"
            + SEPARATOR;

    /**
     * Static field that contains the file of the users list.
     */
    public static final String USER_LIST_FILE = APP_PATH
            + "users"
            + SEPARATOR
            + ".user_list";

    /**
     * Static field that contains the file with points of the Story Mode of the user.
     */
    public static final String STORY_FILE = APP_PATH
            + "leaderbord"
            + SEPARATOR
            + "story.bin";

    /**
     * Static field that contains the file with points of the user of the Survival Mode.
     */
    public static final String SURVIVAL_FILE = APP_PATH
            + "leaderboard"
            + SEPARATOR
            + "survaival.bin";

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
        return APP_PATH
                + "users"
                + SEPARATOR
                + userName + ".bin";
    }
}
