package org.example.coding.datastructures.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PartitionLabels {
    /**
     * Brute-force approach to partition labels.
     * <p>
     * Intuition:
     * - First, count the frequency of each character in the string.
     * - Then, iterate through the string and decrease the count of each character.
     * - When the current character’s count reaches 0, check if all characters up to this point
     * also have a frequency of 0. If so, this index can be the end of a partition.
     * <p>
     * Approach:
     * - Count character frequencies.
     * - Traverse the string, updating frequencies.
     * - At each character, if it’s the last occurrence and no previous characters are pending, mark a partition.
     * <p>
     * Time Complexity: O(n^2) — due to nested check for previous characters in the check function.
     * Space Complexity: O(n) — for the HashMap used to store character frequencies.
     */
    public List<Integer> partitionLabels1(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        List<Integer> ans = new ArrayList<>();
        int lastInd = -1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.get(ch) - 1);
            if (map.get(ch) == 0 && check(i, s, map)) {
                ans.add(i - lastInd);
                lastInd = i;
            }
        }
        return ans;
    }

    // Helper function to verify if all characters from start to index i have zero count left.
    private boolean check(int i, String s, HashMap<Character, Integer> map) {
        for (int j = 0; j <= i; j++) {
            if (map.get(s.charAt(j)) > 0) return false;
        }
        return true;
    }

    /**
     * Optimized approach using last occurrence of characters.
     * <p>
     * Intuition:
     * - If we know the last position of each character, we can determine the point
     * at which all characters seen so far in a partition will not appear again.
     * - Once the current index reaches the maximum last index seen so far, we can mark the end of a partition.
     * <p>
     * Approach:
     * - First pass: Store last index of each character.
     * - Second pass: Iterate and track the furthest last index seen (partition end).
     * When current index matches it, we can finalize a partition.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n) — for the HashMap of last indices.
     */
    public List<Integer> partitionLabels2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        // Step 1: Record last occurrence of each character
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }

        List<Integer> res = new ArrayList<>();
        int prev = -1;
        int max = 0;

        // Step 2: Find partitions based on max last index of characters seen
        for (int i = 0; i < s.length(); i++) {
            max = Math.max(max, map.get(s.charAt(i)));
            if (i == max) {
                res.add(max - prev);
                prev = max;
            }
        }
        return res;
    }

    /**
     * Further optimized approach using an array instead of a map.
     * <p>
     * Intuition:
     * - Similar to the previous method, but since the string only contains lowercase letters (a-z),
     * we can use a fixed-size array of 26 elements to store last indices.
     * <p>
     * Approach:
     * - Step 1: Record last occurrence index for each character in an int array of size 26.
     * - Step 2: Traverse string while updating the max index of the current partition.
     * When the current index reaches this max, a partition ends.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1) — constant space used for fixed-size array.
     */
    public List<Integer> partitionLabels(String s) {
        int[] lastIndex = new int[26]; // Stores last occurrence of each character
        for (int index = 0; index < s.length(); index++) {
            lastIndex[s.charAt(index) - 'a'] = index;
        }

        List<Integer> partitionSizes = new ArrayList<>();
        int partitionStart = 0, partitionEnd = 0;

        for (int index = 0; index < s.length(); index++) {
            partitionEnd = Math.max(partitionEnd, lastIndex[s.charAt(index) - 'a']);
            if (index == partitionEnd) {
                partitionSizes.add(partitionEnd - partitionStart + 1);
                partitionStart = index + 1;
            }
        }
        return partitionSizes;
    }
}
