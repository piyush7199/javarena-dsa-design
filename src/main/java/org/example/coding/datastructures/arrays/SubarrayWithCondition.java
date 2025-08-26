package org.example.coding.datastructures.arrays;

public class SubarrayWithCondition {
    public int countSubarrays(int[] nums) {
        int cnt = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int first = nums[i];
            int sec = nums[i + 1];
            int th = nums[i + 2];
            if (2 * (first + th) == sec) cnt++;

        }

        return cnt;
    }
}
