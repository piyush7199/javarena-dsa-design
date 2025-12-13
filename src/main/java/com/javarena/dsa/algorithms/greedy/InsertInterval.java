package com.javarena.dsa.algorithms.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * Insert Interval
 *
 * <p><b>Problem Statement:</b><br>
 * Given list of non-overlapping intervals sorted by start time and new interval,
 * insert new interval and merge if necessary. Return sorted list.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Three-phase linear scan:
 * 
 * Phase 1: Add non-overlapping intervals before newInterval
 * - While interval ends before newInterval starts: add to result
 * 
 * Phase 2: Merge overlapping intervals
 * - While interval starts before/at newInterval end: merge
 * - Expand newInterval bounds: min(starts), max(ends)
 * - Add merged interval after processing all overlaps
 * 
 * Phase 3: Add remaining intervals
 * - Add all intervals after merged interval
 * 
 * Greedy: Process intervals in order, merge on-the-fly.
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through intervals
 * <br><b>Space Complexity:</b> O(N) - Result list storage
 */
public class InsertInterval {
    
    /**
     * Inserts and merges interval using greedy single-pass.
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        List<int[]> res = new ArrayList<>();
        int i = 0;
        
        // Phase 1: Add all intervals ending before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }

        // Phase 2: Merge overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);

        // Phase 3: Add remaining intervals
        while (i < n) {
            res.add(intervals[i]);
            i++;
        }

        return res.toArray(new int[res.size()][]);
    }
}
