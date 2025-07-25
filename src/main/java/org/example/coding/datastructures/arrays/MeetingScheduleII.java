package org.example.coding.datastructures.arrays;

import java.util.List;
import java.util.PriorityQueue;

public class MeetingScheduleII {
    static class Interval {
        public int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * The goal is to find the minimum number of meeting rooms required to schedule all meetings without overlap.
     * <p>
     * Intuition:
     * - For each meeting, we need to check if a room is available (i.e., if the meeting's start time is after or equal to the
     * end time of an ongoing meeting).
     * - A naive approach would be to compare each meeting with all others to find a suitable room, which would lead to
     * O(n^2) time complexity.
     * <p>
     * Optimized Approach:
     * - Sort all meetings by their start time.
     * - Use a min-heap (priority queue) to keep track of the end times of ongoing meetings.
     * - If the earliest ending meeting finishes before the current meeting starts, we can reuse that room.
     * - Otherwise, we need a new room.
     * - The number of elements in the priority queue at the end represents the minimum number of rooms required.
     * <p>
     * Time Complexity: O(n log n), due to sorting and heap operations.
     * Space Complexity: O(n), for storing end times in the heap.
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
