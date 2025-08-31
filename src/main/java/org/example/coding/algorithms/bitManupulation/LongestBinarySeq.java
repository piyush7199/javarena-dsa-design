package org.example.coding.algorithms.bitManupulation;

/**
 * Problem:
 * Given a binary string s and an integer k,
 * find the length of the longest subsequence of s
 * whose decimal value (when treated as a binary number)
 * is <= k.
 * <p>
 * ------------------------------------------------------
 * Brute Force Recursive Intuition:
 * - At each index, we have two choices:
 * 1) Skip the character.
 * 2) Take the character and update the binary value.
 * - At the end, check if value <= k, else discard.
 * - Answer = max length among all valid subsequences.
 * <p>
 * Brute Force Approach:
 * - Try all subsequences using recursion (2^n possibilities).
 * - Correct but infeasible for large n (n can be up to 10^5).
 * <p>
 * Time Complexity (Brute Force): O(2^n)
 * Space Complexity (Brute Force): O(n) recursion stack
 * <p>
 * ------------------------------------------------------
 * Observations to Improve:
 * 1) Any subsequence of only '0's has value = 0.
 * → So we can always take ALL zeros safely.
 * 2) Ones contribute to numeric value:
 * - The leftmost '1' contributes a huge power of 2.
 * - The rightmost '1' contributes the smallest power of 2.
 * → To maximize subsequence length, include ones from the right first.
 * 3) Greedy Insight:
 * - Take all zeros (free length).
 * - Then, going right to left, keep adding '1's
 * as long as the total value <= k.
 * - Stop when adding another '1' would exceed k.
 * <p>
 * ------------------------------------------------------
 * Optimal Approach (Greedy + Counting):
 * - Step 1: Count all zeros in s (add to length).
 * - Step 2: Traverse s from right → left.
 * For each '1', compute its contribution (2^pos).
 * If total value <= k, include it.
 * Else, stop.
 * - Answer = zeros + number of ones included.
 * <p>
 * Time Complexity (Optimal): O(n)
 * → one pass for zeros, one pass for greedy selection.
 * Space Complexity (Optimal): O(1)
 * → only counters and accumulators used.
 */
public class LongestBinarySeq {
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        int countZeros = 0;

        // Step 1: Count all zeros (free subsequence length)
        for (char c : s.toCharArray()) {
            if (c == '0') countZeros++;
        }

        // Step 2: Try to include ones from right → left
        int value = 0;
        int pow = 1;
        int countOnes = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                if (value + pow <= k) {
                    value += pow;
                    countOnes++;
                }
            }
            // prevent overflow (since pow grows fast)
            if (pow <= k) {
                pow <<= 1; // multiply by 2
            } else {
                break;
            }
        }

        return countZeros + countOnes;
    }
}
