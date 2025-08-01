package org.example.coding.algorithms.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    /**
     * Generates the first `numRows` of Pascal's Triangle.
     *
     * <p><b>Intuition:</b></p>
     * Pascal's Triangle is a triangle where:
     * - Each row starts and ends with 1.
     * - Every inner element is the sum of the two elements directly above it from the previous row.
     * <p>
     * Example:
     * ```
     * Row 0:        1
     * Row 1:       1 1
     * Row 2:      1 2 1
     * Row 3:     1 3 3 1
     * Row 4:    1 4 6 4 1
     * ```
     *
     * <p><b>Approach:</b></p>
     * - Start with the first row `[1]`.
     * - For each new row:
     * - Begin with 1.
     * - For each position `j` from 1 to length-2 in the previous row:
     * - Add the sum of the two numbers directly above from the previous row.
     * - End with 1.
     * - Append each new row to the result list.
     *
     *
     * <p><b>Time Complexity:</b> O(numRows²) – Since each row has up to `numRows` elements in total.</p>
     * <p><b>Space Complexity:</b> O(numRows²) – The output list stores all rows with their elements.</p>
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();

        // First row is always [1]
        ans.add(new ArrayList<>());
        ans.get(0).add(1);

        // Build each row from 1 to numRows - 1
        for (int i = 1; i < numRows; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(1); // First element is always 1

            // Calculate inner values using previous row
            for (int j = 0; j < ans.getLast().size() - 1; j++) {
                int val = ans.getLast().get(j) + ans.getLast().get(j + 1);
                temp.add(val);
            }

            temp.add(1); // Last element is always 1
            ans.add(temp);
        }

        return ans;
    }

}
