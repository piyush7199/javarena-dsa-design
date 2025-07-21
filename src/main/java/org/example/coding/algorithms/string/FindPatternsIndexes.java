package org.example.coding.algorithms.string;

import java.util.ArrayList;

public class FindPatternsIndexes {

    /**
     * Implements the Knuth-Morris-Pratt (KMP) algorithm to find all occurrences of a pattern in a text.
     * <p>
     * Intuition:
     * - A naive approach compares the pattern from the start at every position of the text (O(n*m)).
     * - KMP avoids redundant comparisons using the LPS (Longest Prefix which is also Suffix) array.
     * <p>
     * Steps:
     * 1. Preprocess the pattern to compute the LPS array.
     * 2. While scanning the text, if characters match, move both pointers.
     * 3. On mismatch:
     * - If pattern pointer `j > 0`, jump using `lps[j - 1]`.
     * - If `j == 0`, move the text pointer `i`.
     * 4. If full pattern matches (`j == m`), store the start index (`i - m`) and reset `j` using LPS.
     * <p>
     * Time Complexity: O(n + m)
     * - n = length of text, m = length of pattern.
     * - Building LPS takes O(m), matching takes O(n).
     * <p>
     * Space Complexity: O(m)
     * - LPS array of size equal to the pattern.
     */
    public ArrayList<Integer> search(String pat, String txt) {
        // code here
        int n = txt.length();
        int m = pat.length();
        int[] lps = calculateLPS(pat, m);
        int i = 0, j = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        while (i < n) {
            if (pat.charAt(j) == txt.charAt(i)) {
                i++;
                j++;

                if (j == m) {
                    ans.add(i - m);
                    j = lps[j - 1]; // move pattern pointer using LPS
                }
            } else if (j > 0) {
                j = lps[j - 1]; // mismatch: jump back in pattern
            } else {
                i++; // mismatch and j == 0: move text pointer
            }
        }
        return ans;
    }

    private int[] calculateLPS(String pat, int n) {
        int[] lps = new int[n];
        int i = 1;
        int len = 0;

        while (i < n) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }
        return lps;
    }
}
