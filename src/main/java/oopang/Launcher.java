package oopang;

/**
 * Launcher used by executable jars on modern Java runtimes.
 */
public final class Launcher {

    private Launcher() {
        // Utility class.
    }

    /**
     * Starts the JavaFX application.
     * @param args
     *      CLI arguments.
     */
    public static void main(final String[] args) {
        OOPang.main(args);
    }
}
