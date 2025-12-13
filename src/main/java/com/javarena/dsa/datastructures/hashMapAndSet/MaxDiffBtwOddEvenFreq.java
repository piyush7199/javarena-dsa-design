package com.javarena.dsa.datastructures.hashMapAndSet;

/**
 * Maximum Difference Between Odd and Even Frequency
 *
 * <p><b>Problem Statement:</b><br>
 * Given string, find maximum difference between character with highest odd frequency
 * and character with lowest even frequency.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Frequency analysis:
 * - Count frequency of each character
 * - Track maximum odd frequency
 * - Track minimum even frequency
 * - Return difference: maxOdd - minEven
 * 
 * Edge cases: Only odd frequencies or only even frequencies.
 *
 * <p><b>Time Complexity:</b> O(N) - Count frequencies once
 * <br><b>Space Complexity:</b> O(1) - Fixed array size (26)
 */
public class MaxDiffBtwOddEvenFreq {
    /**
     * Finds max difference between odd and even frequencies.
     */
    public int maxDifference(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int maxOdd = 1, minEven = s.length();
        
        for (int value : freq) {
            if (value == 0) continue;
            if (value % 2 == 1) {
                maxOdd = Math.max(maxOdd, value);
            } else {
                minEven = Math.min(minEven, value);
            }
        }
        return maxOdd - minEven;
    }
}
