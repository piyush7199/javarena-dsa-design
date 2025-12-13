package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

/**
 * Maximum Sum of Distinct Subarrays With Length K
 *
 * <p><b>Problem Statement:</b><br>
 * Given integer array nums and integer k, return maximum possible sum of
 * subarray of size k with all distinct elements. Return 0 if no such subarray exists.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Fixed-size sliding window with distinct element tracking:
 * - Use frequency array to track occurrences in window
 * - Maintain count of distinct elements
 * - Slide window of size k across array
 * - When window has k distinct elements: update max sum
 * 
 * Window management:
 * - Add new element: update frequency, check if new distinct
 * - Remove old element: update frequency, check if no longer distinct
 * - Track running sum of current window
 * 
 * Key insight: Only consider windows with all k elements distinct.
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass with fixed window
 * <br><b>Space Complexity:</b> O(U) - U is max value in nums (frequency array)
 */
public class MaximumSumOfDistinctSubarraysWithLengthK {
    
    /**
     * Finds maximum sum of subarray of size k with all distinct elements.
     */
    public long maximumSubarraySum(int[] nums, int k) {
        long sum = 0;
        long maxSum = 0;
        int distinctCount = 0;
        int[] freq = new int[100001];  // Frequency array
        
        for (int i = 0; i < nums.length; i++) {
            // Add new element to window
            if (freq[nums[i]] == 0) {
                distinctCount++;
            }
            freq[nums[i]]++;
            sum += nums[i];
            
            // Remove element from window if size > k
            if (i >= k) {
                int removed = nums[i - k];
                sum -= removed;
                freq[removed]--;
                if (freq[removed] == 0) {
                    distinctCount--;
                }
            }
            
            // Update max if window has all distinct elements
            if (i >= k - 1 && distinctCount == k) {
                maxSum = Math.max(maxSum, sum);
            }
        }
        
        return maxSum;
    }
}
