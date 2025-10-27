package com.javarena.dsa.algorithms.miscellaneous;

import java.util.ArrayList;
import java.util.List;

/**
 * 386. Lexicographical Numbers
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/lexicographical-numbers/">LeetCode - Lexicographical Numbers</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Depth-First Search, Trie
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
 * You must write an algorithm that runs in O(n) time and uses O(1) extra space.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: n = 13
 * Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
 *
 * Input: n = 2
 * Output: [1,2]
 *
 * Input: n = 100
 * Output: [1,10,100,11,12,...,19,2,20,21,...,99]
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Simulate DFS traversal of lexicographic tree:
 * - Think of numbers as tree: 1→10→100, 1→11→110, etc.
 * - Start from 1, try to go deeper (multiply by 10) as long as result ≤ n
 * - If can't go deeper, try next sibling (increment)
 * - If at boundary (ends with 9 or next > n), backtrack (divide by 10)
 * - This generates numbers in lexicographical order without sorting
 * - Avoids generating and sorting all numbers (which would be O(n log n))
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Start with current number = 1</li>
 *   <li>For each of n iterations:</li>
 *   <li>- Add current number to result</li>
 *   <li>- Try to go deeper: if curr × 10 ≤ n, multiply by 10</li>
 *   <li>- Else, try to go to next sibling:</li>
 *   <li>  - While at boundary (ends with 9 or curr + 1 > n), backtrack (divide by 10)</li>
 *   <li>  - Then increment to get next number</li>
 *   <li>Repeat until all n numbers generated</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n)<br>
 * We generate exactly n numbers, each in O(1) time (constant operations per number).
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses constant extra space (excluding output list). No recursion stack.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>n = 1: Returns [1]</li>
 *   <li>n < 10: Simple sequential with proper order</li>
 *   <li>Powers of 10: Handles transitions like 9→10, 99→100 correctly</li>
 *   <li>Boundaries like 19, 29: Proper backtracking from 9</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/find-kth-smallest-pair-distance/">Kth Smallest Pair Distance</a>
 */
public class LexicographicalNumbers {
    
    /**
     * Returns all numbers from 1 to n in lexicographical order.
     *
     * @param n the upper limit (inclusive)
     * @return list of numbers [1, n] in lexicographical order
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        int curr = 1;
        
        // Step 1: Generate exactly n numbers
        for (int i = 0; i < n; i++) {
            // Step 2: Add current number to result
            res.add(curr);
            
            // Step 3: Try to go deeper (multiply by 10)
            if (curr * 10 <= n) {
                curr *= 10;
            } else {
                // Step 4: Can't go deeper, need to move to next branch
                
                // Backtrack if at boundary (ends with 9 or would exceed n)
                while (curr % 10 == 9 || curr + 1 > n) {
                    curr /= 10;
                }
                
                // Step 5: Move to next number
                curr++;
            }
        }
        
        return res;
    }
}
