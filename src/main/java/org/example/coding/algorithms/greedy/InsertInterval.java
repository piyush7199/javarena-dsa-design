package org.example.coding.algorithms.greedy;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {

    /**
     * Inserts a new interval into a list of non-overlapping intervals and merges if necessary.
     * <p>
     * Intuition:
     * - Add all intervals ending before the new interval.
     * - Merge overlapping intervals with the new interval.
     * - Add the remaining intervals after merging.
     * <p>
     * Time Complexity: O(n) — Single pass through the intervals.
     * Space Complexity: O(n) — Additional list used to store the result.
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        List<int[]> res = new ArrayList<>();
        int i = 0;
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }

        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        res.add(newInterval);

        while (i < n) {
            res.add(intervals[i]);
            i++;
        }

        return res.toArray(new int[res.size()][]);
    }
}
