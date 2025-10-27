package com.javarena.dsa.algorithms.bitManupulation;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 898. Bitwise ORs of Subarrays
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/bitwise-ors-of-subarrays/">LeetCode - Bitwise ORs of Subarrays</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Array, Bit Manipulation, Dynamic Programming
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given an integer array arr, return the number of distinct bitwise OR values that can be 
 * obtained from all possible contiguous subarrays of arr. A subarray is a contiguous 
 * non-empty sequence of elements within an array.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: arr = [0]
 * Output: 1
 * Explanation: Only one subarray [0], OR value = 0.
 *
 * Input: arr = [1,1,2]
 * Output: 3
 * Explanation: Possible OR values are {1, 2, 3}.
 * Subarrays: [1]→1, [1]→1, [2]→2, [1,1]→1, [1,2]→3, [1,1,2]→3.
 *
 * Input: arr = [1,2,4]
 * Output: 6
 * Explanation: Possible OR values are {1, 2, 3, 4, 6, 7}.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Key insights about bitwise OR:
 * - OR operation accumulates 1s: once a bit is set, it remains set in future ORs
 * - For each new element, we can extend all previous OR results
 * - Brute force O(n²) generates all subarrays, but can be optimized
 * - Dynamic approach: maintain intermediate OR results and extend them
 * - Due to OR's accumulative nature, number of unique values at each step is limited
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Maintain a list of intermediate OR results from previous iterations</li>
 *   <li>For each new element in array:</li>
 *   <li>- Add the element itself (starting new subarray)</li>
 *   <li>- OR it with all previous round's results (extending subarrays)</li>
 *   <li>Track the start index of current round to avoid re-processing</li>
 *   <li>Avoid duplicate consecutive entries while extending</li>
 *   <li>Use HashSet at end to count only unique OR values</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n × 32) ≈ O(n)<br>
 * Although nested loops suggest O(n²), the inner loop runs at most 32 times per element
 * because integers have 32 bits, limiting unique OR values at each step.
 *
 * <p><b>Space Complexity:</b> O(n × 32) ≈ O(n)<br>
 * In worst case, list stores all intermediate OR results, but bounded by bit limit.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Single element: Returns 1 (only one unique value)</li>
 *   <li>All same elements: Returns 1 (all subarrays have same OR)</li>
 *   <li>All zeros: Returns 1 (OR of zeros is zero)</li>
 *   <li>Powers of 2: Each can contribute unique OR values</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/">Maximum XOR</a>
 */
public class BitOrSubarray {
    
    /**
     * Returns the number of unique bitwise OR results from all subarrays.
     *
     * @param arr the input array of integers
     * @return count of distinct OR values possible from all contiguous subarrays
     */
    class Solution {
        public int subarrayBitwiseORs(int[] arr) {
            // Step 1: List stores all OR values calculated so far
            ArrayList<Integer> s = new ArrayList<>();
            
            // Step 2: Track where last round of ORs began
            int l = 0;

            // Step 3: Loop through each number in array
            for (int a : arr) {
                // Mark current size before adding new ORs
                int r = s.size();
                
                // Start new subarray with only current element
                s.add(a);

                // Step 4: Extend all previous round's ORs with current element
                for (int i = l; i < r; ++i) {
                    // Combine old OR result with current number
                    int v = s.get(i) | a;
                    
                    // Only add if it creates a new value (avoid duplicates)
                    if (v != s.getLast()) {
                        s.add(v);
                    }
                }

                // Step 5: Update marker for next round
                l = r;
            }

            // Step 6: Use HashSet to count only unique values
            return new HashSet<>(s).size();
        }
    }
}
