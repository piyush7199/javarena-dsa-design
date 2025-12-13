package com.javarena.dsa.datastructures.arrays;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Meeting Schedule II
 *
 * <p><b>Problem Statement:</b><br>
 * Find the minimum number of meeting rooms required to schedule all meetings without overlap.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Sort meetings by start time to process in chronological order
 * - Use min-heap to track end times of ongoing meetings
 * - For each meeting:
 *   - If earliest ending meeting finishes before current starts, reuse that room (poll from heap)
 *   - Add current meeting's end time to heap (allocate room)
 * - Heap size represents number of simultaneously active meetings
 * - Final heap size = minimum rooms needed
 * - Greedy approach: always reuse earliest available room
 *
 * <p><b>Time Complexity:</b> O(N log N) - Sorting + N heap operations
 * <br><b>Space Complexity:</b> O(N) - Heap storing end times
 */
public class MeetingScheduleII {
    /**
     * Interval class to represent meeting times.
     */
    static class Interval {
        public int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * Finds minimum number of meeting rooms required.
     */
    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) return 0;
        if (intervals.size() == 1) return 1;

        // Step 1: Sort meetings by start time
        intervals.sort((a, b) -> Integer.compare(a.start, b.start));

        // Step 2: Use a min-heap to track end times of ongoing meetings
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (Interval interval : intervals) {
            // If the room due to free earliest is available, reuse it
            if (!minHeap.isEmpty() && minHeap.peek() <= interval.start) {
                minHeap.poll();  // Room becomes free
            }

            // Allocate the current meeting to a room (new or reused)
            minHeap.offer(interval.end);
        }

        // The size of the heap tells us the minimum number of rooms required
        return minHeap.size();
    }
}
