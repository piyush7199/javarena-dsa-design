package com.javarena.dsa.datastructures.string;

import java.util.ArrayList;
import java.util.List;

/**
 * ZigZag Conversion
 *
 * <p><b>Problem Statement:</b><br>
 * Convert string to zigzag pattern on given number of rows, then read line by line.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Simulate zigzag pattern:
 * - Create list of StringBuilder for each row
 * - Traverse string with direction flag (down/up)
 * - Add each character to current row
 * - Change direction at top (row 0) and bottom (row n-1)
 * - Concatenate all rows for result
 * 
 * Pattern: Down → Up → Down → Up...
 * Edge case: If numRows = 1 or >= string length, return original.
 *
 * <p><b>Time Complexity:</b> O(N) - Visit each character once
 * <br><b>Space Complexity:</b> O(N) - Store all characters in rows
 */
public class ZigZagConversion {
    /**
     * Converts string to zigzag pattern.
     */
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) return s;
        
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        
        int curRow = 0;
        boolean goingDown = false;
        
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            
            // Change direction at top or bottom
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }
        
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }
}
