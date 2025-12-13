package com.javarena.dsa.datastructures.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Divide Intervals Into Minimum Number of Groups
 *
 * <p><b>Problem Statement:</b><br>
 * Given a 2D integer array intervals where intervals[i] = [left_i, right_i], divide the intervals 
 * into minimum number of groups such that no two intervals in the same group intersect.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Convert to event-based approach: treat start/end as events
 * - For each interval [start, end], create:
 *   - Start event at time 'start' with value +1
 *   - End event at time 'end+1' with value -1 (end+1 to handle inclusive intervals)
 * - Sort events by time (if same time, start before end)
 * - Sweep through events, tracking concurrent intervals
 * - Maximum concurrent intervals = minimum groups needed
 * - This is essentially finding maximum overlapping intervals at any point
 *
 * <p><b>Time Complexity:</b> O(N log N) - Sorting events
 * <br><b>Space Complexity:</b> O(N) - Storing all events
 */
public class DivideIntervals {
    /**
     * Finds minimum number of groups to divide non-overlapping intervals.
     */
    public int minGroups(int[][] intervals) {
        List<int[]> events = new ArrayList<>();
        for (int[] interval : intervals) {
            events.add(new int[]{interval[0], 1}); // Start event
            events.add(new int[]{interval[1] + 1, -1}); // End event (interval[1] + 1)
        }

        Collections.sort(events, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]); // Sort by type (1 before -1)
            } else {
                return Integer.compare(a[0], b[0]); // Sort by time
            }
        });

        int concurrentIntervals = 0;
        int maxConcurrentIntervals = 0;
        for (int[] event : events) {
            concurrentIntervals += event[1]; // Track currently active intervals
            maxConcurrentIntervals = Math.max(
                    maxConcurrentIntervals,
                    concurrentIntervals
            ); // Update max
        }

        return maxConcurrentIntervals;
    }
}
