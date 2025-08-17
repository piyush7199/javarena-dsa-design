package org.example.coding.datastructures.arrays;

public class WaysToSplitArrayIntoThreeSubarrays {

    /**
     * Given an integer array nums, returns the number of ways to split it into
     * three contiguous non-empty subarrays (left, mid, right) such that:
     * - sum(left) <= sum(mid)
     * - sum(mid) <= sum(right)
     * <p>
     * Intuition:
     * ----------
     * We want to count valid partitions into three subarrays.
     * Using a prefix sum array, we can quickly compute subarray sums.
     * For each possible first cut index i (end of left subarray),
     * we binary search for the valid range of indices j (end of mid subarray):
     * 1. The smallest j where midSum >= leftSum.
     * 2. The largest j where midSum <= rightSum.
     * The count of valid splits for this i is (last - first + 1).
     * <p>
     * Approach:
     * ---------
     * 1. Build prefix sum array for O(1) sum queries.
     * 2. Iterate over i (end of left subarray).
     * 3. For each i, binary search twice:
     * - Find the first valid j.
     * - Find the last valid j.
     * 4. Add the number of valid j’s to the total answer.
     * 5. Return result modulo 1e9+7.
     * <p>
     * Complexity:
     * -----------
     * Time:  O(n log n)
     * - O(n) for prefix sums
     * - O(n log n) for n iterations with 2 binary searches each
     * <p>
     * Space: O(n)
     * - Prefix sum array
     *
     * @param nums input array of positive integers
     * @return number of valid ways to split the array (modulo 1_000_000_007)
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

