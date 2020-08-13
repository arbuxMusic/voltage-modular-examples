package au.com.arbuxmusic.corelib.performance;

import java.util.ArrayList;

/**
 * Allows the timing of code
 */
public class StopWatch {

    /**
     * Storage for an individual lap
     */
    public class StopWatchLap {
        private String name;
        private long startTime;
        private long endTime;

        /**
         * Get the name of the stop watch
         * @return the end time of the stop watch
         */
        public String getName() {
            return name;
        }

        /**
         * Get the start time of the stop watch
         * @return the end time of the stop watch
         */
        public double getStartTimeMS() {
            return (double) startTime / 1000000D;
        }

        /**
         * Get the end time of the stop watch
         * @return the end time of the stop watch
         */
        public double getEndTimeMS() {
            return (double) endTime / 1000000D;
        }

        /**
         * Get the duration of the stopwatch
         * @return Get the duration of the stopwatch
         */
        public double getDurationMS() {
            return getEndTimeMS() - getStartTimeMS();
        }

        /**
         * Create a new stopwatch lap
         *
         * @param name The name of the lap
         * @param startTime The start time of the lap
         * @param endTime The end time of the lap
         */
        public StopWatchLap(String name, long startTime, long endTime) {
            this.name = name;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    /**
     * The start time of the current timer
     */
    private long totalStartTime;

    /**
     * The current lap start time
     */
    private long lastStartTime;

    /**
     * The end time of the current timer
     */
    private long totalEndTime;

    /**
     * The stopwatch laps
     */
    ArrayList<StopWatchLap> laps = new ArrayList<>();

    /**
     * Get the current duration of the stopwatch
     * @return The duration of the stopwatch in milliseconds
     */
    public double getDurationMS(){
        long endTime = (totalEndTime == 0 ? System.nanoTime() : totalEndTime);
        return (double)(endTime - totalStartTime) / 1000000D;
    }

    /**
     * Start the stop watch
     */
    public void start() {
        totalStartTime = System.nanoTime();
        lastStartTime = totalStartTime;
    }

    /**
     * Mark the current lap
     */
    public void markLap() {
        long currentTime = System.nanoTime();
        laps.add(new StopWatchLap("Lap:" + currentTime, lastStartTime, currentTime));
        lastStartTime = currentTime;
    }

    /**
     * Mark the current lap with the specified name
     */
    public void markLap(String lapName) {
        long currentTime = System.nanoTime();
        laps.add(new StopWatchLap(lapName, lastStartTime, currentTime));
        lastStartTime = currentTime;
    }

    /**
     * Stop the stopwatch
     */
    public void stop(){
        totalEndTime = System.nanoTime();
    }

    /**
     * Reset the stopwatch
     */
    public void reset() {
        laps.clear();
        totalStartTime = 0;
        lastStartTime = 0;
        totalEndTime = 0;
    }
}
