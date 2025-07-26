package org.example.coding.datastructures.string;

public class LongestCommonPrefix {

    /**
     * Finds the longest common prefix string amongst an array of strings.
     *
     * <p>The method compares characters in each string one-by-one from left to right.
     * It stops when a mismatch is found or the shortest string ends.
     * <p>
     * Time Complexity: O(S), where S is the total number of characters across all strings.
     * In the worst case, we compare every character of every string until a mismatch or end.
     * <p>
     * Space Complexity: O(1), excluding the output. The only additional space used is for a few variables and the StringBuilder.
     */
    public String longestCommonPrefix(String[] strs) {
        int n = strs[0].length();
        for (String str : strs) {
            n = Math.min(n, str.length());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = strs[0].charAt(i);
            for (String str : strs) {
                if (ch != str.charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(ch);
        }
        return sb.toString();
    }
}
