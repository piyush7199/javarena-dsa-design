package com.javarena.dsa.datastructures.arrays;

/**
 * Ways to Split Array Into Three Subarrays
 *
 * <p><b>Problem Statement:</b><br>
 * Given an integer array, count ways to split into three contiguous non-empty subarrays (left, mid, right)
 * such that sum(left) <= sum(mid) <= sum(right). Return result mod 10^9+7.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Use prefix sums + binary search:
 * - Build prefix sum array for O(1) subarray sum queries
 * - For each possible end of left subarray (index i):
 *   - Use binary search to find valid range for end of mid subarray (j)
 *   - Find first valid j where midSum >= leftSum
 *   - Find last valid j where midSum <= rightSum
 *   - Count = (lastJ - firstJ + 1)
 * - Sum all valid counts
 * - Key insight: Binary search makes it efficient
 *
 * <p><b>Time Complexity:</b> O(N log N) - N iterations with 2 binary searches each
 * <br><b>Space Complexity:</b> O(N) - Prefix sum array
 */
public class WaysToSplitArrayIntoThreeSubarrays {

    /**
     * Counts ways to split array with sum constraints.
     */
    public int waysToSplit(int[] nums) {
        int MOD = 1_000_000_007;
        int n = nums.length;

        // Step 1: Build prefix sum array
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        int totalWays = 0;

        // Step 2: Iterate through the first cut position (i)
        for (int i = 0; i < n - 2; i++) {
            int leftSum = prefix[i];

            // Step 3a: Binary search to find first valid j
            int low = i + 1, high = n - 2, first = -1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int midSum = prefix[mid] - prefix[i];
                if (midSum >= leftSum) {
                    first = mid;
                    high = mid - 1; // try earlier
                } else {
                    low = mid + 1;
                }
            }
            if (first == -1) continue; // no valid j

            // Step 3b: Binary search to find last valid j
            low = first;
            high = n - 2;
            int last = -1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int midSum = prefix[mid] - prefix[i];
                int rightSum = prefix[n - 1] - prefix[mid];
                if (midSum <= rightSum) {
                    last = mid;
                    low = mid + 1; // try later
                } else {
                    high = mid - 1;
                }
            }

            // Step 4: Count valid splits for this i
            if (last != -1) {
                totalWays = (totalWays + (last - first + 1)) % MOD;
            }
        }

        return totalWays;
    }
}

