package com.javarena.dsa.datastructures.string;

/**
 * Binary String with Substrings Representing 1 to N
 *
 * <p><b>Problem Statement:</b><br>
 * Given binary string s and integer n, return true if binary representation
 * of every integer from 1 to n exists as substring in s.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Brute force check:
 * - For each number i from 1 to n:
 *   - Convert i to binary string
 *   - Check if binary string exists as substring in s
 *   - If any missing, return false
 * - If all found, return true
 * 
 * Optimization: Start from n downward (larger numbers harder to find).
 *
 * <p><b>Time Complexity:</b> O(N × M × log N) - N numbers, M string length, log N binary conversion
 * <br><b>Space Complexity:</b> O(log N) - Binary string storage
 */
public class BinaryString {
    /**
     * Checks if all binary representations 1 to n exist in s.
     */
    public boolean queryString(String s, int n) {
        for (int i = 1; i <= n; i++) {
            String str = binary(i);
            if (s.indexOf(str) == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts integer to binary string.
     */
    private String binary(int n) {
        StringBuilder s = new StringBuilder();
        while (n > 0) {
            int r = n % 2;
            s.insert(0, r);
            n = n / 2;
        }
        return s.toString();
    }
}
