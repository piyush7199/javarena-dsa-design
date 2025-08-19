package org.example.coding.datastructures.arrays;

public class NumberOfZeroFilledSubarrays {
    public static long zeroFilledSubarray(int[] nums) {
        long count = 0;
        long max = 0;
        for (int num : nums) {
            if (num == 0) {
                count++;
                max += count;
            } else count = 0;
        }
        return max;
    }
}
