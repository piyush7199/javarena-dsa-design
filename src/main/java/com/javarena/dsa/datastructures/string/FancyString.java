package com.javarena.dsa.datastructures.string;

/**
 * Delete Characters to Make Fancy String
 *
 * <p><b>Problem Statement:</b><br>
 * Make string "fancy" by ensuring no three consecutive characters are same.
 * Delete minimum characters to achieve this.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Greedy approach with counter:
 * - Track consecutive count of current character
 * - If count < 3: include character
 * - If count >= 3: skip character (don't add to result)
 * - Reset count when character changes
 * 
 * Only keep first 2 occurrences of any consecutive sequence.
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through string
 * <br><b>Space Complexity:</b> O(N) - Result string
 */
public class FancyString {
    /**
     * Makes string fancy by limiting consecutive characters to 2.
     */
    public String makeFancyString(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        int cnt = 1;
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cnt++;
            } else {
                cnt = 1;
            }
            
            if (cnt < 3) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
