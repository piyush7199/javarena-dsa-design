package org.example.coding.datastructures.arrays;

public class SquareSumOfSpecialEle {
    public int sumOfSquares(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (n % (i + 1) == 0) {
                ans += (nums[i] * nums[i]);
            }
        }

        return ans;
    }
}
