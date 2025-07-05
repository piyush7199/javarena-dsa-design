package org.example.coding.algorithms;

import java.util.Arrays;

public class DynamicProgramming {
    /**
     * Determines if the input array can be partitioned into two subsets with equal sums.
     *
     * <p><b>Problem:</b> Given a non-empty array of positive integers, determine whether the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
     *
     * <p><b>Intuition:</b><br>
     * - If the total sum is odd, partitioning is impossible.                                           <br>
     * - Reduce the problem to subset sum: can we find a subset with sum = totalSum / 2?                <br>
     * - Use bottom-up DP to fill a table tracking reachable sums.
     *
     * <p><b>Time Complexity:</b> O(n * sum/2)
     * <br><b>Space Complexity:</b> O(n * sum/2)
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int ele : nums) {
            sum += ele;
        }
        if (sum % 2 == 1) return false;
        int n = nums.length;
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[nums.length][target];
    }

    /**
     * Recursive + memoized solution to the same partitioning problem above.
     *
     * <p><b>Time Complexity:</b> O(n * target)
     * <br><b>Space Complexity:</b> O(n * target) for memoization table
     */
    private boolean findTarget(int[] nums, int ind, int target, int[][] dp) {
        if (target == 0) return true;
        if (target < 0 || ind == nums.length) return false;
        if (dp[ind][target] != -1) return dp[ind][target] == 1;
        boolean notTake = findTarget(nums, ind + 1, target, dp);
        boolean take = false;
        if (!notTake && nums[ind] <= target) {
            take = findTarget(nums, ind + 1, target - nums[ind], dp);
        }
        dp[ind][target] = notTake || take ? 1 : 2;
        return notTake || take;
    }

    /**
     * Checks if there's a subset of the given array with a sum equal to the given value.
     *
     * <p><b>Problem:</b> Given an array of integers and a sum, determine if there is a subset of the array that adds up to the given sum.
     *
     * <p><b>Intuition:</b><br>
     * - Use DP to determine whether each sum from 1 to given sum can be formed using the array elements.
     *
     * <p><b>Time Complexity:</b> O(n * sum)
     * <br><b>Space Complexity:</b> O(n * sum)
     */
    public Boolean isSubsetSum(int[] arr, int sum) {
        int n = arr.length;
        boolean[][] dp = new boolean[n][sum + 1];
        for (int i = 0; i < n; i++) dp[i][0] = true;
        if (arr[0] <= sum) {
            dp[0][arr[0]] = true;
        }

        for (int ind = 1; ind < n; ind++) {
            for (int target = 1; target <= sum; target++) {
                boolean notTaken = dp[ind - 1][target];
                boolean taken = false;
                if (arr[ind] <= target) {
                    taken = dp[ind - 1][target - arr[ind]];
                }
                dp[ind][target] = notTaken || taken;
            }
        }
        return dp[n - 1][sum];
    }

    /**
     * Recursive + memoized solution to subset sum problem above.
     *
     * <p><b>Time Complexity:</b> O(n * sum)
     * <br><b>Space Complexity:</b> O(n * sum) for memoization table
     */
    private boolean subsetSumUtil(int ind, int target, int[] arr, int[][] dp) {
        if (target == 0)
            return true;
        if (ind == 0)
            return arr[0] == target;

        if (dp[ind][target] != -1) return dp[ind][target] == 1;
        boolean notTaken = subsetSumUtil(ind - 1, target, arr, dp);
        boolean taken = false;
        if (arr[ind] <= target)
            taken = subsetSumUtil(ind - 1, target - arr[ind], arr, dp);

        dp[ind][target] = notTaken || taken ? 1 : 0;
        return notTaken || taken;
    }

    /**
     * Finds the maximum product of a contiguous subarray.
     *
     * <p>🔍 Intuition:
     * - Track the product from both left and right ends to handle negative numbers and zeros.
     * - Reset the running product to 1 when encountering zero.
     * - At each step, update the maximum with both left and right product.
     * <p>
     * ⏱ Time Complexity: O(n)
     * 🧠 Space Complexity: O(1)
     */
    public int maxProduct(int[] nums) {

        int n = nums.length;
        int l = 1, r = 1;
        int ans = nums[0];

        for (int i = 0; i < n; i++) {

            //if any of l or r become 0 then update it to 1
            l = l == 0 ? 1 : l;
            r = r == 0 ? 1 : r;

            l *= nums[i];   //prefix product
            r *= nums[n - 1 - i];    //suffix product

            ans = Math.max(ans, Math.max(l, r));

        }

        return ans;
    }

    /**
     * Calculates the number of distinct ways to climb to the top (step `n`)
     * when at each step you can climb either 1 or 2 steps.
     * <p>
     * Intuition:
     * - This is a classic DP problem similar to Fibonacci sequence.
     * - To reach step `i`, you can come from `i-1` or `i-2`.
     * <p>
     * Recurrence: dp[i] = dp[i-1] + dp[i-2]
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int climbStairs(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int prev = 1;
        int prev2 = 2;
        for (int i = 3; i <= n; i++) {
            int temp = prev + prev2;
            prev = prev2;
            prev2 = temp;
        }
        return prev2;
    }

    /**
     * Returns the minimum cost to reach the last step by jumping either 1 or 2 steps at a time.
     * Cost of a jump is the absolute height difference.
     * <p>
     * Intuition:
     * - At each step, decide whether to jump from one step before or two steps before, and take the minimum.
     * - Classic DP on 1D array with 2-step dependency.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    int minCost(int[] height) {
        // code here
        int n = height.length;
        if (n <= 1) return 0;

        int prev2 = 0;
        int prev = Math.abs(height[0] - height[1]);
        for (int i = 2; i < n; i++) {
            int jumpOne = prev + Math.abs(height[i] - height[i - 1]);
            int jumpTwo = prev2 + Math.abs(height[i] - height[i - 2]);
            int cur = Math.min(jumpOne, jumpTwo);
            prev2 = prev;
            prev = cur;
        }
        return prev;
    }

    /**
     * Solves the House Robber problem — find the maximum amount you can rob
     * from non-adjacent houses.
     * <p>
     * Intuition:
     * - At each house, decide to rob it (and skip previous), or skip it.
     * - Uses DP with state compression.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int rob(int[] nums) {
        return robHelper(nums);
    }

    /**
     * Solves the House Robber II problem (circular street) — can't rob both first and last houses.
     * <p>
     * Intuition:
     * - Break the circle into two linear arrays:
     * 1. From house 0 to n-2
     * 2. From house 1 to n-1
     * - Take the max of the two cases.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int rob2(int[] nums) {
        int n = nums.length;
        return Math.max(robHelper(Arrays.copyOfRange(nums, 0, n - 1)), robHelper(Arrays.copyOfRange(nums, 1, n)));
    }

    public int robHelper(int[] nums) {
        int prev = nums[0];
        int prev2 = 0;
        for (int i = 1; i < nums.length; i++) {
            int pick = nums[i] + prev2;
            int notPick = prev;
            int cur = Math.max(pick, notPick);
            prev2 = prev;
            prev = cur;
        }
        return prev;
    }
}
