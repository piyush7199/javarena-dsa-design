package org.example.coding.algorithms.dynamicProgramming;

import java.util.Arrays;

public class LIS {
    public int lengthOfLISBrute(int[] nums) {
        int n = nums.length;

        return bruteHelper(nums, 0, -1);
    }

    private int bruteHelper(int[] nums, int ind, int lastInd) {
        if (ind == nums.length) return 0;

        int ans = bruteHelper(nums, ind + 1, lastInd);
        if (lastInd == -1 || nums[lastInd] < nums[ind]) {
            ans = Math.max(ans, 1 + bruteHelper(nums, ind + 1, ind));
        }
        return ans;
    }

    public int lengthOfLISMemo(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return solveMemo(nums, 0, -1, dp);
    }

    static int solveMemo(int[] nums, int i, int j, int[][] dp) {
        if (i == nums.length) return 0;
        if (dp[i][j + 1] != -1) return dp[i][j + 1];
        int len = solveMemo(nums, i + 1, j, dp);
        if (j == -1 || nums[i] > nums[j]) {
            len = Math.max(len, 1 + solveMemo(nums, i + 1, i, dp));
        }
        return dp[i][j + 1] = len;
    }
}
