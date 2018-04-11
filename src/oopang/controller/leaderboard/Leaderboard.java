package oopang.controller.leaderboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
/**
 * Class Leaderboard.
 */
public class Leaderboard implements Serializable {
    private static final long serialVersionUID = -904319433538008187L;
    private final List<LeaderboardRecord> recordList;
    /**
     * 
     */
    public Leaderboard() {
        this.recordList = new ArrayList<>();
    }
    /**
     * Add the Record if is grater than other on top 10 records.
     * @param record
     *      The Record of the User.
     * @return
     *      True if the Record is greater than last, False in the other case.
     */
    public boolean addRecord(final LeaderboardRecord record) {
        if (record.compareTo(recordList.get(recordList.size() - 1)) > 0) {
            recordList.remove(recordList.size() - 1);
            recordList.add(record);
            Collections.sort(recordList);
            return true;
        }
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
}

