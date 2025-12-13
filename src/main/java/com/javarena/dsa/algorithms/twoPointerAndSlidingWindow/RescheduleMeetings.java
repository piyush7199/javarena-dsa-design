package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

/**
 * Reschedule Meetings for Maximum Free Time
 *
 * <p><b>Problem Statement:</b><br>
 * Given eventTime, k, and arrays startTime/endTime of scheduled activities,
 * find maximum total free time by selecting k+1 contiguous free intervals.
 * Free intervals include time before first activity, between activities, and after last.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Fixed-size sliding window on free time intervals:
 * - Calculate all free time slots (before first, between, after last)
 * - Use sliding window of size (k+1) on free slots array
 * - Find maximum sum of k+1 contiguous free intervals
 * 
 * Free time calculation:
 * - Before first: 0 to startTime[0]
 * - Between activities: startTime[i] - endTime[i-1]
 * - After last: eventTime - endTime[n-1]
 * 
 * Sliding window: Track sum of current k+1 intervals, update maximum.
 *
 * <p><b>Time Complexity:</b> O(N) - Calculate gaps + sliding window
 * <br><b>Space Complexity:</b> O(N) - Array to store free time slots
 */
public class RescheduleMeetings {
    
    /**
     * Finds maximum free time by selecting k+1 contiguous free intervals.
     */
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] freeSlots = new int[n + 1];
        
        // Calculate free time before first activity
        freeSlots[0] = startTime[0];
        
        // Calculate free time between activities
        for (int i = 1; i < n; i++) {
            freeSlots[i] = startTime[i] - endTime[i - 1];
        }
        
        // Calculate free time after last activity
        freeSlots[n] = eventTime - endTime[n - 1];
        
        // Sliding window of size (k+1) to find max sum
        int windowSum = 0;
        
        // Calculate sum of first (k+1) intervals
        for (int i = 0; i <= k; i++) {
            windowSum += freeSlots[i];
        }
        
        int maxFreeTime = windowSum;
        
        // Slide window and update max
        for (int i = k + 1; i <= n; i++) {
            windowSum += freeSlots[i] - freeSlots[i - k - 1];
            maxFreeTime = Math.max(maxFreeTime, windowSum);
        }
        
        return maxFreeTime;
    }
}
