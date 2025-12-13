package com.javarena.dsa.datastructures.string;

/**
 * Backspace String Compare
 *
 * <p><b>Problem Statement:</b><br>
 * Given two strings with '#' representing backspace, determine if they are equal
 * after processing all backspaces.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Simulate typing with backspace:
 * - Build final string by processing each character
 * - Regular character: append to result
 * - '#': delete last character if exists
 * - Compare final processed strings
 * 
 * Alternative: Two-pointer from end (O(1) space).
 *
 * <p><b>Time Complexity:</b> O(M + N) - Process both strings
 * <br><b>Space Complexity:</b> O(M + N) - Store processed strings
 */
public class BackspaceStringCompare {
    /**
     * Compares strings after processing backspaces.
     */
    public boolean backspaceCompare(String s, String t) {
        // Process first string
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == '#') {
                if (!sb.isEmpty()) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(ch);
            }
        }
        String s1 = sb.toString();
        
        // Process second string
        sb = new StringBuilder();
        for (char ch : t.toCharArray()) {
            if (ch == '#') {
                if (!sb.isEmpty()) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(ch);
            }
        }
        String t1 = sb.toString();
        
        return s1.equals(t1);
    }
}
