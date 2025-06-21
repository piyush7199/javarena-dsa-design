package org.example.coding.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SlidingWindow {

    /**
     * Finds the shortest lexicographically smallest substring in a binary string `s` that contains exactly `k` ones.
     *
     * <p><b>Intuition:</b></p>
     * We use the sliding window technique with two pointers (`left` and `right`) to explore all substrings of `s`.
     * As we move the right pointer, we count the number of '1's in the window.
     * If the count exceeds `k`, we move the left pointer to shrink the window until we have at most `k` '1's.
     * Whenever the window has exactly `k` '1's:
     * - We check if the window is shorter than the previous shortest (update if yes).
     * - If lengths are equal, we keep the lexicographically smaller one.
     * This approach ensures we find the shortest valid substring efficiently.
     *
     * <p><b>Time Complexity:</b> O(n)</p>
     * - Each character is visited at most twice (once by `right`, once by `left`).
     *
     * <p><b>Space Complexity:</b> O(1)</p>
     * - Only constant extra space is used (a few variables), excluding the result string.
     */
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int ones = 0;
        int minLen = Integer.MAX_VALUE;
        String result = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                ones++;
            }

            // Shrink from left if too many 1s
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            // Try to shrink while still maintaining exactly k 1s
            while (ones == k) {
                int len = right - left + 1;
                String candidate = s.substring(left, right + 1);

                if (len < minLen || (len == minLen && candidate.compareTo(result) < 0)) {
                    minLen = len;
                    result = candidate;
                }

                // Try to shrink the window from the left
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }
        }

        return result;
    }

    /**
     * Finds the length of the longest substring without repeating characters.
     *
     * <p><b>Problem:</b> Given a string, find the length of the longest substring
     * without repeating characters.
     *
     * <p><b>Intuition:</b> <br>
     * - Use a sliding window with a HashSet to track unique characters.                <br>
     * - Expand the window with `right`, and shrink with `left` when duplicates occur.
     *
     * <p><b>Time Complexity:</b> O(n) — Each character is visited at most twice.
     * <br><b>Space Complexity:</b> O(min(n, m)) — where m is the size of the character set.
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, ans = 0, n = s.length();

        while (right < n) {
            char ch = s.charAt(right);
            while (right > left && set.contains(ch)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(ch);
            ans = Math.max(ans, right - left + 1);
            right++;
        }

        return ans;
    }

    /**
     * Returns the maximum number of consecutive 1s in the array if you can flip at most k 0s.
     *
     * <p><b>Problem:</b> You are given a binary array and an integer k.
     * Return the length of the longest contiguous subarray with at most k zeros.
     *
     * <p><b>Intuition:</b> <br>
     * - Use two pointers and track the number of zeros in the window. <br>
     * - Shrink the window from the left when `zero > k`.
     *
     * <p><b>Time Complexity:</b> O(n) — Both pointers traverse the array once.
     * <br><b>Space Complexity:</b> O(1)
     */
    public int longestOnes(int[] nums, int k) {
        int zero = 0, left = 0, right = 0, ans = 0;

        while (right < nums.length) {
            if (nums[right] == 0) zero++;
            if (zero > k) {
                if (nums[left] == 0) zero--;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }

        return ans;
    }

    /**
     * Finds the length of the longest subarray containing at most two types of fruits.
     *
     * <p><b>Problem:</b> You are given an array where each element is a tree type.
     * You can pick only two types of fruits in a basket. Find the longest subarray
     * where you can only collect two types.
     *
     * <p><b>Intuition:</b> <br>
     * - Use a sliding window and HashMap to track counts of each fruit type. <br>
     * - When size > 2, shrink the window from the left.
     *
     * <p><b>Time Complexity:</b> O(n) — Each element is visited at most twice.
     * <br><b>Space Complexity:</b> O(1) — Since only 2 distinct fruit types are allowed.
     */
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0, right = 0, maxLen = Integer.MIN_VALUE;

        while (right < fruits.length) {
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);

            while (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left]) - 1);
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }


}
