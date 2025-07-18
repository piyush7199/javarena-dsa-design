package org.example.coding.algorithms.twoPointerAndSlidingWindow;

public class RepeatingChars {

    /**
     * Finds the length of the longest substring that can be obtained by replacing at most `k`
     * characters in the string to make all characters in the substring the same.
     * <p>
     * Intuition:
     * - Maintain the frequency of characters in the window.
     * - The window is valid if (window size - most frequent character count) ≤ k.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int characterReplacement(String s, int k) {
        int mf = 0;
        int[] freq = new int[26];
        int left = 0;
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'A'] += 1;
            mf = Math.max(freq[s.charAt(i) - 'A'], mf);
            int rem = (i - left + 1) - mf;
            if (rem <= k) {
                ans = Math.max(ans, i - left + 1);
            } else {
                freq[s.charAt(left) - 'A']--;
                left++;
            }
        }
        return ans;
    }
}
