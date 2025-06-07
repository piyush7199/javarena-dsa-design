package org.example.coding.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    /**
     * Problem
     * <p>
     * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
     * <p>
     * Intuition
     * <p></p>
     * We are given a list of intervals. If any intervals overlap, we want to merge them into one.
     * Sorting by start time allows us to scan through and merge overlapping intervals on the go.
     * <p>
     * Complexity
     * <p>
     * Time complexity:{@code O(nlogn)} (for sorting)
     * <p></p>
     * Space complexity:{@code O(n)} (for storing merged intervals)
     */
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (res.getLast()[1] >= intervals[i][0]) {
                res.getLast()[1] = Math.max(intervals[i][1], res.getLast()[1]);
            } else {
                res.add(intervals[i]);
            }
        }

        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }

        return ans;
    }
}
