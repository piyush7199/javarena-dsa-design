package com.javarena.dsa.datastructures.hashMapAndSet;

/**
 * Divide Array Into Equal Pairs
 *
 * <p><b>Problem Statement:</b><br>
 * Check if array can be divided into pairs where both elements in each pair are equal.
 * Array length is even.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Frequency-based validation:
 * - Valid division possible only if every number appears even number of times
 * - Use frequency array to count occurrences
 * - Track pairs formed: when frequency becomes even, pair completed
 * - If total pairs = n/2, division possible
 * 
 * Alternative: Check if all frequencies are even.
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through array
 * <br><b>Space Complexity:</b> O(1) - Fixed array size (501)
 */
public class DivideArrayIntoEqualPairs {
    /**
     * Checks if array can be divided into equal pairs.
     */
    public boolean divideArray(int[] nums) {
        int[] freq = new int[501];
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (freq[nums[i]] % 2 == 1) {
                count++;
            }
            freq[nums[i]]++;
        }

        return count == n / 2;
    }

}
