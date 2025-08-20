package org.example.coding.algorithms.bitManupulation;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int ele : nums) {
            ans ^= ele;
        }
        return ans;
    }
}
