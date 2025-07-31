package org.example.coding.algorithms.bitManupulation;

import java.util.ArrayList;
import java.util.HashSet;

public class BitOrSubarray {
    /**
     * This method returns the number of unique bitwise OR results
     * from all possible contiguous subarrays of the given array.
     *
     * <p><b>Intuition:</b></p>
     * - Bitwise OR accumulates 1s: once a bit is set, it remains set in future ORs.
     * - So, for each element, we can extend all previous OR results and add the new ones.
     * - This avoids brute-force generation of all subarrays.
     *
     * <p><b>Approach:</b></p>
     * - Maintain a list of intermediate OR results.
     * - For each new element, combine it (using OR) with all results from the previous round.
     * - Avoid duplicates while extending by checking against the last inserted value.
     * - Finally, use a HashSet to collect unique OR values.
     *
     * <p><b>Time Complexity:</b> O(n²) in the worst case, but efficient in practice due to bit behavior.</p>
     * <p><b>Space Complexity:</b> O(n²) in worst case for list and set.</p>
     */
    class Solution {
        public int subarrayBitwiseORs(int[] arr) {
            // This list stores all OR values calculated so far
            ArrayList<Integer> s = new ArrayList<>();

            // 'l' marks the index from where the last round of ORs began
            int l = 0;

            // Loop through each number in the array
            for (int a : arr) {
                int r = s.size();  // Store current size before new ORs are added
                s.add(a);          // Start a new subarray containing only the current number

                // Extend all previous ORs from the last round with the current number
                for (int i = l; i < r; ++i) {
                    int v = s.get(i) | a; // Combine old value with current number
                    // Only add if it is a new value (avoid duplicate consecutive entries)
                    if (v != s.getLast()) {
                        s.add(v);
                    }
                }

                // Update 'l' to mark the start of the current round's values
                l = r;
            }

            // Use a HashSet to count only unique OR values
            return new HashSet<>(s).size();
        }
    }

}
