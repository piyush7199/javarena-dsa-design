package org.example.coding.datastructures.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DivideIntervals {
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
