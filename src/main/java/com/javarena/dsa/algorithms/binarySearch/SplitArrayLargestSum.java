package com.javarena.dsa.algorithms.binarySearch;

/**
 * 410. Split Array Largest Sum
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/split-array-largest-sum/">LeetCode - Split Array Largest Sum</a>
 *
 * <p><b>Difficulty:</b> Hard
 *
 * <p><b>Topics:</b> Binary Search, Array, Dynamic Programming, Greedy
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Split array nums into k non-empty contiguous subarrays. Minimize the largest sum among these subarrays.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: nums = [7,2,5,10,8], k = 2
 * Output: 18
 * Explanation: [7,2,5] and [10,8]. Largest sum = max(14, 18) = 18.
 *
 * Input: nums = [1,2,3,4,5], k = 2
 * Output: 9
 * Explanation: [1,2,3,4] and [5]. Largest = 9.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Binary search on answer (largest subarray sum). For candidate max X, greedily check if can split
 * into ≤k subarrays with each sum ≤X. Search space: [max(nums), sum(nums)].
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Binary search: low=max(nums), high=sum(nums)</li>
 *   <li>For mid, greedily split: accumulate until adding next element exceeds mid</li>
 *   <li>If splits ≤k: try smaller max (high=mid-1)</li>
 *   <li>Else: need larger max (low=mid+1)</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n log S)<br>
 * Where S = sum(nums). Binary search O(log S), each check O(n).
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>k = 1: Return sum of array</li>
 *   <li>k = n: Return max element</li>
 *   <li>All elements equal: Even distribution</li>
 * </ul>
 */
public class SplitArrayLargestSum {
    
    public int splitArray(int[] nums, int k) {
        int low = nums[0], high = 0;
        for (int ele : nums) {
            low = Math.max(low, ele);
            high += ele;
        }

        while (low <= high) {
            int mid = (low + high) / 2;
            if (isValid(nums, k, mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean isValid(int[] nums, int k, int mid) {
        int cnt = 1, sm = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sm + nums[i] <= mid) {
                sm += nums[i];
            } else {
                sm = nums[i];
                cnt++;
            }
            if (cnt > k) return false;
        }
        return cnt <= k;
    }
}
