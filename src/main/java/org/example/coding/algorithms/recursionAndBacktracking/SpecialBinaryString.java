package org.example.coding.algorithms.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpecialBinaryString {
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
