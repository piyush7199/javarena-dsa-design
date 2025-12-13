package com.javarena.dsa.datastructures.string;

/**
 * Largest 3-Same-Digit Number in String
 *
 * <p><b>Problem Statement:</b><br>
 * Find largest "good integer" in string. Good integer = exactly 3 consecutive same digits.
 * Return empty string if no such integer exists.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Sliding window of size 3:
 * - Check every 3 consecutive characters
 * - If all three are same, it's a good integer
 * - Track the largest good integer found
 * - Compare lexicographically (larger digit = larger number)
 * 
 * Simple linear scan with comparison.
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through string
 * <br><b>Space Complexity:</b> O(1) - Only store result string
 */
public class Largest3Same {
    /**
     * Finds largest good integer (3 same consecutive digits).
     */
    public String largestGoodInteger(String num) {
        String ans = "";
        int n = num.length();
        
        for (int i = 1; i < n - 1; i++) {
            char a1 = num.charAt(i - 1);
            char a2 = num.charAt(i);
            char a3 = num.charAt(i + 1);
            
            if (a1 == a2 && a2 == a3) {
                if (ans.isEmpty() || ans.charAt(0) < a1) {
                    ans = "" + a1 + a2 + a3;
                }
            }
        }
        return ans;
    }
}
