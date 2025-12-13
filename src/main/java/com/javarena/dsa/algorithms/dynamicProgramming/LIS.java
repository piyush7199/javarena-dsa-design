package com.javarena.dsa.algorithms.dynamicProgramming;

import java.util.Arrays;

/**
 * Longest Increasing Subsequence (LIS)
 *
 * <p><b>Problem Statement:</b><br>
 * Given integer array, return length of longest strictly increasing subsequence.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Multiple approaches with different complexities:
 * 
 * 1. Recursion O(2^N): For each element, try include/skip
 *    - Include if current > last
 *    - Track last included element to maintain order
 * 
 * 2. Memoization O(N²): Cache results using 2D DP[i][j]
 *    - i = current index, j = last included index
 * 
 * 3. Tabulation O(N²): Bottom-up DP
 *    - dp[i] = length of LIS ending at index i
 *    - For each i, check all j < i where nums[j] < nums[i]
 * 
 * 4. Binary Search O(N log N): Optimal approach
 *    - Maintain array of smallest ending values for each length
 *    - Use binary search to find position for current element
 *
 * <p><b>Time Complexity:</b> O(N²) for DP, O(N log N) for binary search
 * <br><b>Space Complexity:</b> O(N²) for memoization, O(N) for tabulation
 */
public class LIS {
    
    /**
     * Tabulation approach - most commonly used.
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        int maxLength = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        
        return maxLength;
    }
    
    /**
     * Binary search approach - optimal O(N log N).
     */
    public int lengthOfLISBinarySearch(int[] nums) {
        int[] tails = new int[nums.length];
        int len = 0;
        
        for (int num : nums) {
            int left = 0, right = len;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            tails[left] = num;
            if (left == len) len++;
        }
        
        return len;
    }
}
