package com.javarena.dsa.algorithms.miscellaneous;

import java.util.ArrayList;
import java.util.List;

/**
 * Lexicographical Numbers
 *
 * <p><b>Problem Statement:</b><br>
 * Given integer n, return all numbers in range [1, n] sorted in lexicographical order.
 * Must run in O(n) time and use O(1) extra space.
 *
 * <p><b>Intuition & Approach:</b><br>
 * DFS traversal of lexicographic tree:
 * - Think of numbers as tree: 1→10→100, 1→11→110, etc.
 * - Start from 1, try to go deeper (multiply by 10) while ≤ n
 * - If can't go deeper, try next sibling (increment)
 * - If at boundary (ends with 9 or next > n), backtrack (divide by 10)
 * - Generates numbers in lexicographical order without sorting
 * 
 * Algorithm:
 * 1. Start with current = 1
 * 2. For each iteration: add current to result
 * 3. Try deeper: if curr × 10 ≤ n, multiply by 10
 * 4. Else try sibling: while at boundary, backtrack then increment
 * 5. Repeat until n numbers generated
 * 
 * Avoids O(n log n) sorting by generating in correct order.
 *
 * <p><b>Time Complexity:</b> O(N) - Generate n numbers in order
 * <br><b>Space Complexity:</b> O(1) - Constant space excluding output
 */
public class LexicographicalNumbers {
    
    /**
     * Returns numbers 1 to n in lexicographical order.
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        int curr = 1;
        
        for (int i = 0; i < n; i++) {
            result.add(curr);
            
            if (curr * 10 <= n) {
                curr *= 10;
            } else {
                while (curr % 10 == 9 || curr + 1 > n) {
                    curr /= 10;
                }
                curr++;
            }
        }
        
        return result;
    }
}
