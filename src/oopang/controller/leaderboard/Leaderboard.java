package oopang.controller.leaderboard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Class Leaderboard that keep track of users score.
 */
public class Leaderboard implements Serializable {
    private static final long serialVersionUID = -904319433538008187L;
    private static final int MAX_SCORES = 10;
    private final List<LeaderboardRecord> recordList;
    private transient Optional<Integer> lastIndex;

    /**
     * Create a new Leaderboard.
     */
    public Leaderboard() {
        this.recordList = new ArrayList<>();
        this.lastIndex = Optional.empty();
    }

    /**
     * Add the {@link LeaderboardRecord} if is grater than other on top 10 records.
     * @param record
     *      The last record submitted.
     * @return
     *      True if the record is greater than last, false in the other case.
     */
    public boolean addRecord(final LeaderboardRecord record) {
        if (recordList.size() >= MAX_SCORES && record.compareTo(recordList.get(recordList.size() - 1)) > 0) {
            recordList.remove(recordList.size() - 1);
        }
        if (recordList.size() < MAX_SCORES) {
            recordList.add(record);
            Collections.sort(recordList, (a, b) -> b.compareTo(a));
            this.lastIndex = Optional.of(recordList.indexOf(record));
            return true;
        } 
        this.lastIndex = Optional.empty();
        return false;
    }

    /**
     * Returns a stream of {@link LeaderboardRecord}.
     * @return
     *      a stream of Record.
     */
    public Stream<LeaderboardRecord> getRecords() {
        return recordList.stream();
    }

    /**
     * Returns an optional for the index of the last Leaderboard record added.
     * @return
     *      The index of the last Leaderboard record, empty if last record was lower than the top ten.
     */
    public Optional<Integer> getLastIndex() {
        return this.lastIndex;
    }

    /**
     * Method used to read the object without losing transient fields.
     * @param in
     *      input stream.
     * @throws IOException
     *      if a problem occurred during I/O operations.
     * @throws ClassNotFoundException
     *      if the class is not present.
     */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.lastIndex = Optional.empty();
    }

    /**
     * Method used to write the object on an output stream.
     * @param out
     *      output stream.
     * @throws IOException
     *      if a problem occurred during I/O operations.
     */
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }
}

