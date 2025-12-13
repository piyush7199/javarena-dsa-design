package com.javarena.dsa.algorithms.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Special Binary String
 *
 * <p><b>Problem Statement:</b><br>
 * A special binary string is a string that has equal number of 0s and 1s, and every prefix has
 * at least as many 1s as 0s. Given a special binary string, make it lexicographically largest
 * by swapping any two adjacent substrings.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Split the string into top-level special substrings (complete balanced parts)
 * - Recursively solve each substring's inner part (excluding outer '1' and '0')
 * - Sort all top-level parts in reverse order (lexicographically largest first)
 * - Concatenate sorted parts to get the result
 * - Key insight: Between two special strings, we can always arrange them in any order
 *
 * <p><b>Time Complexity:</b> O(N² log N) - Recursion and sorting at each level
 * <br><b>Space Complexity:</b> O(N) - Recursion depth and temporary lists
 */
public class SpecialBinaryString {
    
    /**
     * Makes the lexicographically largest special binary string.
     *
     * @param s input special binary string
     * @return lexicographically largest rearrangement
     */
    public String makeLargestSpecial(String s) {
        if (s.length() <= 2) return s; // Base case

        List<String> parts = new ArrayList<>();
        int count = 0, start = 0;

        // Step 1: Split into top-level special substrings
        for (int i = 0; i < s.length(); i++) {
            count += (s.charAt(i) == '1' ? 1 : -1);
            if (count == 0) {
                // Solve inner substring recursively
                String inner = makeLargestSpecial(s.substring(start + 1, i));
                parts.add("1" + inner + "0");
                start = i + 1;
            }
        }

        // Step 2: Sort parts in reverse order
        parts.sort(Collections.reverseOrder());

        // Step 3: Concatenate sorted parts
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            result.append(part);
        }

        return result.toString();
    }
}
