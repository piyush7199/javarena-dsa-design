package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Find All Anagrams in a String
 *
 * <p><b>Problem Statement:</b><br>
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * An anagram is a word formed by rearranging the letters of another word using all original letters exactly once.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use sliding window of fixed size (length of p) over string s
 * - Build frequency array for pattern string p
 * - Maintain frequency array for current window in s
 * - Slide window: add new character, remove leftmost character when window exceeds size
 * - Compare frequency arrays: if they match, substring is an anagram
 * - Add starting index to result when frequencies match
 * - Use arrays of size 26 for lowercase English letters
 *
 * <p><b>Time Complexity:</b> O(N) - N is length of s, comparing 26 chars is O(1)
 * <br><b>Space Complexity:</b> O(1) - Frequency arrays are constant size (26)
 */
public class FindAllAnagramsInAString {
    /**
     * Finds all start indices of p's anagrams in s.
     */
    public List<Integer> findAnagrams(String s, String p) {
        // Frequency array for pattern string p
        int[] freqP = new int[26];
        for (char ch : p.toCharArray()) {
            freqP[ch - 'a']++;
        }

        // Frequency array for current sliding window in s
        int[] freqS = new int[26];
        int n = s.length();
        int m = p.length();

        List<Integer> ans = new ArrayList<>();

        // Sliding window traversal
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            freqS[ch - 'a']++;  // include current char into window

            // shrink window from left when size exceeds m
            if (i >= m) {
                freqS[s.charAt(i - m) - 'a']--;
            }

            // compare freq arrays when window size == m
            if (isPossible(freqP, freqS)) {
                ans.add(i - m + 1);
            }
        }

        return ans;
    }

    private boolean isPossible(int[] freqP, int[] freqS) {
        for (int i = 0; i < 26; i++) {
            if (freqP[i] != freqS[i]) {
                return false;
            }
        }
        return true;
    }
}
