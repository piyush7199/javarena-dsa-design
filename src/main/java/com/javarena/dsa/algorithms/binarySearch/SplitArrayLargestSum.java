package com.javarena.dsa.algorithms.binarySearch;

/**
 * Split Array Largest Sum
 *
 * <p><b>Problem Statement:</b><br>
 * Split array nums into k non-empty contiguous subarrays.
 * Minimize the largest sum among these subarrays.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Binary search on answer (largest subarray sum):
 * - Search space: [max(nums), sum(nums)]
 * - Lower bound: at least one subarray contains max element
 * - Upper bound: single subarray contains all elements
 * - For candidate max X:
 *   1. Greedily split: accumulate until adding next exceeds X
 *   2. Count number of splits needed
 *   3. If splits ≤ k: try smaller max
 *   4. Else: need larger max
 * - Similar to book allocation problem
 *
 * <p><b>Time Complexity:</b> O(N log S) - N elements, S = sum(nums)
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class SplitArrayLargestSum {
    
    /**
     * Finds minimum possible largest sum among k splits.
     */
    public int splitArray(int[] nums, int k) {
        int low = findMax(nums);
        int high = findSum(nums);

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int splits = countSplits(nums, mid);
            
            if (splits <= k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Counts number of splits needed with max sum maxSum.
     */
    private int countSplits(int[] nums, int maxSum) {
        int splits = 1;
        int currentSum = 0;

        for (int num : nums) {
            if (currentSum + num <= maxSum) {
                currentSum += num;
            } else {
                splits++;
                currentSum = num;
            }
        }
        return splits;
    }

    /**
     * Finds maximum element in array.
     */
    private int findMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        return max;
    }

    /**
     * Calculates sum of all elements.
     */
    private int findSum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}
