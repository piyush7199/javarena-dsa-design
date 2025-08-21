package org.example.coding.algorithms.twoPointerAndSlidingWindow;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInAString {
    /**
     * Problem: Find all start indices of p's anagrams in string s.
     * <p>
     * Intuition:
     * - An anagram is just a permutation of characters.
     * - If two strings have the same frequency count of each character (a–z), they are anagrams.
     * - We can use a sliding window of size = p.length() over s and compare frequency counts.
     * <p>
     * Approach:
     * 1. Build a frequency map for string p (pattern).
     * 2. Use a sliding window of size m = p.length() over s:
     * - Add the current character into the window frequency.
     * - Remove the character that falls out of the window (when i >= m).
     * - Compare the frequency arrays of window and p.
     * - If they match → it means substring is an anagram → add index.
     * 3. Return all valid starting indices.
     * <p>
     * Time Complexity:
     * - O(n * 26) = O(26n) = O(n), where n = length of s.
     * (Each index requires checking 26 characters in worst case.)
     * <p>
     * Space Complexity:
     * - O(26) = O(1), since frequency arrays are constant size (for lowercase English letters).
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
