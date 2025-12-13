package com.javarena.dsa.datastructures.hashMapAndSet;

/**
 * Sum of Maximum Consonant and Maximum Vowel Frequencies
 *
 * <p><b>Problem Statement:</b><br>
 * Given string, find sum of maximum consonant frequency and maximum vowel frequency.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Separate frequency tracking:
 * - Count frequency of each character
 * - Track maximum frequency among vowels (a, e, i, o, u)
 * - Track maximum frequency among consonants
 * - Return sum of both maximums
 * 
 * Simple frequency analysis with categorization.
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through string
 * <br><b>Space Complexity:</b> O(1) - Fixed array size (26)
 */
public class SumOfMaxCAndMaxV {
    /**
     * Calculates sum of max consonant and vowel frequencies.
     */
    public int maxFreqSum(String s) {
        int[] freq = new int[26];
        int maxV = 0;
        int maxC = 0;
        
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                maxV = Math.max(freq[ch - 'a'], maxV);
            } else {
                maxC = Math.max(freq[ch - 'a'], maxC);
            }
        }
        return maxC + maxV;
    }
}
