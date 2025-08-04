package org.example.coding.algorithms.twoPointerAndSlidingWindow;

public class RescheduleMeetings {
    /**
     * Calculates the maximum total free time that can be obtained by selecting k+1
     * contiguous free intervals during an event with scheduled activities.
     *
     * <p><b>Intuition:</b>
     * Given a list of activities with start and end times, there are gaps between
     * activities (free time). We want to find the maximum total free time that can
     * be collected by picking any k+1 contiguous free time slots (including before
     * the first activity and after the last).
     *
     * <p><b>Approach:</b>
     * 1. Compute the free time intervals:
     * - Before the first event: from time 0 to startTime[0].
     * - Between events: startTime[i] - endTime[i - 1].
     * - After the last event: eventTime - endTime[n - 1].
     * 2. Use a sliding window of size k+1 on the freeSlot array to find the maximum sum.
     *
     * <p><b>Time Complexity:</b> O(n), where n is the number of scheduled activities.
     * We traverse the input once to calculate gaps and once more with a sliding window.
     *
     * <p><b>Space Complexity:</b> O(n), for storing the freeSlot array of size n+1.
     */
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] freeSlot = new int[n + 1];

        // Time before the first event
        freeSlot[0] = startTime[0];

        // Time between events
        for (int i = 1; i < n; i++) {
            freeSlot[i] = startTime[i] - endTime[i - 1];
        }

        // Time after the last event
        freeSlot[n] = eventTime - endTime[n - 1];

        // Initialize sum of first k+1 free slots
        int curSum = 0;
        for (int i = 0; i <= k; i++) {
            curSum += freeSlot[i];
        }

        int ans = curSum;

        // Sliding window to find maximum sum of k+1 consecutive free slots
        for (int i = 1; i + k <= n; i++) {
            curSum = curSum - freeSlot[i - 1] + freeSlot[i + k];
            ans = Math.max(ans, curSum);
        }

        return ans;
    }

}
