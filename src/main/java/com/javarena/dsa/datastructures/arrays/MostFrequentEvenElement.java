package com.javarena.dsa.datastructures.arrays;

/**
 * Most Frequent Even Element
 *
 * <p><b>Problem Statement:</b><br>
 * Return the smallest even element that occurs most frequently in the array. If there is a tie in frequency, 
 * return the smallest even number. Return -1 if no even element exists.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use frequency array of size 100001 (given constraint)
 * - Iterate through array, skip odd numbers ((x & 1) == 1)
 * - For each even number, increment its frequency count
 * - Update best answer if:
 *   - Current frequency > max frequency, OR
 *   - Current frequency == max frequency AND current number < best
 * - This ensures we get smallest number when frequencies tie
 * - Single pass solution with constant space lookup
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through array
 * <br><b>Space Complexity:</b> O(1) - Fixed size array (100001 is constant)
 */
public class MostFrequentEvenElement {
    /**
     * Finds most frequent even element, smallest if tie.
     */
    public int mostFrequentEven(int[] nums) {
        int[] cnt = new int[100001];
        int best = -1, freq = 0;
        
        for (int x : nums) {
            if ((x & 1) == 1) continue;  // Skip odd numbers
            int f = ++cnt[x];
            if (f > freq || (f == freq && (best == -1 || x < best))) {
                best = x;
                freq = f;
            }
        }
        
        return best;
    }
}
