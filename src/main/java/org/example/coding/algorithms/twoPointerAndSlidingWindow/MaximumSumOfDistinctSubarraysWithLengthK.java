package org.example.coding.algorithms.twoPointerAndSlidingWindow;

public class MaximumSumOfDistinctSubarraysWithLengthK {
    /**
     * Finds the maximum possible sum of a subarray of size `k` such that all elements
     * in that subarray are distinct.
     * <p>
     * Intuition:
     * - We use a sliding window of size `k` to maintain the current subarray.
     * - We track the sum of elements and the count of distinct elements inside the window.
     * - To efficiently check distinct elements, we use a frequency array `temp` where
     * temp[x] represents how many times `x` occurs in the current window.
     * - As we slide the window, we update:
     * - The sum of elements.
     * - The distinct element count (`dis`).
     * - Whenever the number of distinct elements equals `k`, it means the window has all
     * unique elements, so we check and update the maximum sum.
     * <p>
     * Time Complexity:
     * - O(n), where n = length of nums.
     * Each element is processed once when entering the window and once when leaving.
     * <p>
     * Space Complexity:
     * - O(U), where U = maximum value in nums (bounded by 1,000,000 as given in problem).
     * We use a frequency array `temp` of size 1,000,001.
     */
    public long maximumSubarraySum(int[] nums, int k) {
        long sum = 0;   // running sum of elements in the current window
        long max = 0;   // maximum sum found so far
        int dis = 0;    // number of distinct elements in the current window

        // frequency array to track occurrences of numbers in the window
        int[] temp = new int[1000001];

        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                // Expanding the initial window of size k
                if (temp[nums[i]] == 0) // new distinct element
                    dis++;
                temp[nums[i]]++;        // increment frequency
                sum += nums[i];         // add to running sum
            } else {
                // Sliding the window: remove the outgoing element
                temp[nums[i - k]]--;    // decrement frequency of outgoing element
                if (temp[nums[i - k]] == 0)
                    dis--;              // lost a distinct element
                sum -= nums[i - k];     // subtract outgoing element from sum

                // Add the new incoming element
                temp[nums[i]]++;
                if (temp[nums[i]] == 1) // if it's a new distinct element
                    dis++;
                sum += nums[i];
            }

            // If window has exactly k distinct elements, check max sum
            if (dis == k)
                max = Math.max(sum, max);
        }

        return max;
    }

}
