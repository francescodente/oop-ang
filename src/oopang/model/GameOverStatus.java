package oopang.model;

/**
 * Describes information about the level termination.
 */
public final class GameOverStatus {
    private int score;
    private LevelResult result;

    /**
     * Creates a new GameOverStatus given the score and the result.
     * @param score
     *      the score value.
     * @param result
     *      the result of the level.
     */
    public GameOverStatus(final int score, final LevelResult result) {
        super();
        this.score = score;
        this.result = result;
    }

    /**
     * Returns the score after the end of the level.
     * @return
     *      the score value.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the result of the level.
     * @return
     *      the {@link LevelResult} of the level.
     */
    public LevelResult getResult() {
        return result;
    }
}
