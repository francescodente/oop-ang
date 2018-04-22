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
     * Add the Record if is grater than other on top 10 records.
     * @param record
     *      The Record of the User.
     * @return
     *      True if the Record is greater than last, False in the other case.
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
     * This getter return the stream of records.
     * @return
     *      The stream of Record.
     */
    public Stream<LeaderboardRecord> getRecords() {
        return recordList.stream();
    }
    /**
     * This getter returns the index of the last Leaderboard record.
     * @return
     *      The index of the last Leaderboard record.
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

