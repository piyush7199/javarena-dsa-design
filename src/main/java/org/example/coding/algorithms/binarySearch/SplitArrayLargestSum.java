package org.example.coding.algorithms.binarySearch;

public class SplitArrayLargestSum {
    /**
     * Problem: Split Array Largest Sum
     * <p>
     * Intuition:
     * -----------
     * We want to split the array into 'k' subarrays such that the maximum sum
     * among these subarrays is minimized.
     * - If we think about possible answers, the minimum possible largest sum
     * is the maximum element in the array (since no subarray can have sum less than its largest element).
     * - The maximum possible largest sum is the sum of the entire array (if we don't split at all).
     * <p>
     * So, the problem boils down to searching within this range [maxElement, totalSum]
     * for the minimum largest sum that allows a valid split into ≤ k subarrays.
     * <p>
     * <p>
     * Approach:
     * ----------
     * 1. Binary Search on Answer:
     * - Let low = max(nums) and high = sum(nums).
     * - Perform binary search on this range.
     * <p>
     * 2. Feasibility Check (isValid):
     * - For a given mid (candidate largest sum), check if it's possible to split the array
     * into at most 'k' subarrays such that each subarray's sum ≤ mid.
     * - If feasible, try smaller values (move left).
     * - Otherwise, move right (increase the allowed largest sum).
     * <p>
     * 3. The binary search converges to the minimum largest sum that satisfies the condition.
     * <p>
     * <p>
     * Time Complexity:
     * -----------------
     * - Binary search runs on the range [maxElement, totalSum].
     * Let `S = sum(nums)`, and `M = max(nums)`.
     * The search space size is (S - M), so ~ O(log S).
     * - For each mid, we do a linear scan of nums → O(n).
     * - Total = O(n * log S)
     * <p>
     * Space Complexity:
     * ------------------
     * - O(1) (only a few variables, no extra data structures).
     */
    public int splitArray(int[] nums, int k) {
        int low = nums[0];
        int high = 0;
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

    /**
     * Helper function to check if it's possible to split the array into
     * at most 'k' subarrays where each subarray has sum ≤ mid.
     */
    private boolean isValid(int[] nums, int k, int mid) {
        int cnt = 1;
        int sm = 0;
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
