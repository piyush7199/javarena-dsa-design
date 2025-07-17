package org.example.coding.algorithms.twoPointerAndSlidingWindow;

import java.util.HashMap;

public class LongestSubstring {

    /**
     * Finds the length of the longest substring with exactly k distinct characters.
     * <p>
     * Intuition:
     * - Use a sliding window and a HashMap to track character frequencies.
     * - If the number of distinct characters exceeds k, shrink the window from the left.
     * - Track and update the maximum length when distinct characters count is exactly k.
     * <p>
     * Time Complexity: O(n) — Each character is added and removed from the map at most once.
     * Space Complexity: O(k) — HashMap stores at most k distinct characters.
     */
    public int longestKSubstr(String s, int k) {
        // code here
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int n = s.length();
        int ans = -1;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.size() > k) {
                char rem = s.charAt(left);
                map.put(rem, map.get(rem) - 1);
                if (map.get(rem) == 0) {
                    map.remove(rem);
                }
                left++;
            }
            if (map.size() == k) {
                ans = Math.max(ans, i - left + 1);
            }
        }
        return ans;
    }

    /**
     * Finds the length of the longest substring with at most k distinct characters.
     * <p>
     * Intuition:
     * - Similar to `longestKSubstr` but relaxes the condition to at most k distinct characters.
     * - Use a sliding window with HashMap to track the current character frequencies.
     * <p>
     * Time Complexity: O(n) — Sliding window ensures each character is processed at most twice.
     * Space Complexity: O(k) — At most k entries in the character map.
     */
    public static int kDistinctChars(int k, String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int n = s.length();
        int ans = -1;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.size() > k) {
                char rem = s.charAt(left);
                map.put(rem, map.get(rem) - 1);
                if (map.get(rem) == 0) {
                    map.remove(rem);
                }
                left++;
            }
            ans = Math.max(ans, i - left + 1);
        }
        return ans;
    }
}
