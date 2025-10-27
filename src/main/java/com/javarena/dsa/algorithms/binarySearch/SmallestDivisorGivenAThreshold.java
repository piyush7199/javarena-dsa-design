package com.javarena.dsa.algorithms.binarySearch;

/**
 * 1283. Find the Smallest Divisor Given a Threshold
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/">LeetCode - Smallest Divisor</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Binary Search, Array
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given array nums and integer threshold, find the smallest divisor such that the result of
 * dividing all elements by it (rounded up) and summing them is ≤ threshold.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: nums = [1,2,5,9], threshold = 6
 * Output: 5
 * Explanation: Divisor 5: ceil(1/5)+ceil(2/5)+ceil(5/5)+ceil(9/5) = 1+1+1+2 = 5 ≤ 6
 *
 * Input: nums = [44,22,33,11,1], threshold = 5
 * Output: 44
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Binary search on divisor value. Smaller divisor → larger sum, larger divisor → smaller sum.
 * Monotonic property allows binary search. Search space: [1, max(nums)].
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Binary search: low=1, high=max(nums)</li>
 *   <li>For mid divisor, calculate sum of ceil(nums[i]/mid)</li>
 *   <li>Use trick: ceil(a/b) = (a+b-1)/b</li>
 *   <li>If sum ≤ threshold: try smaller divisor (high=mid-1)</li>
 *   <li>Else: need larger divisor (low=mid+1)</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n log M)<br>
 * Where M = max(nums). Binary search O(log M), each check O(n).
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>threshold = n: Divisor must be at least max(nums)</li>
 *   <li>All elements same: Simple calculation</li>
 *   <li>Large values: Use ceil trick to avoid overflow</li>
 * </ul>
 */
public class SmallestDivisorGivenAThreshold {
    
    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1, high = 0;
        for (int ele : nums) {
            high = Math.max(high, ele);
        }

        while (low <= high) {
            int mid = (low + high) / 2;
            if (helper(nums, mid, threshold)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean helper(int[] nums, int mid, int threshold) {
        int sum = 0;
        for (int ele : nums) {
            sum += (ele + mid - 1) / mid;  // ceil(ele/mid)
            if (sum > threshold) return false;
        }
        return sum <= threshold;
    }
}
