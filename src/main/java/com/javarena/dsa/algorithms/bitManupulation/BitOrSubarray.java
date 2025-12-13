package com.javarena.dsa.algorithms.bitManupulation;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Bitwise ORs of Subarrays
 *
 * <p><b>Problem Statement:</b><br>
 * Given integer array, return number of distinct bitwise OR values from all possible
 * contiguous subarrays.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Dynamic programming with optimization:
 * - Brute force O(N²) generates all subarrays
 * - Optimization: For each element, extend previous OR results
 * - Key insight: OR is monotonic (values only increase or stay same)
 * - At each position, maintain set of OR values ending at current position
 * - New OR values = {current element} ∪ {prev OR values | current}
 * - Maximum unique ORs per position ≤ 32 (limited by bit positions)
 * 
 * Process:
 * - For each element, compute ORs with all previous subarray endings
 * - Add all unique OR values to global set
 * - Total distinct ORs = size of global set
 *
 * <p><b>Time Complexity:</b> O(N × 32) ≈ O(N) - At most 32 unique ORs per position
 * <br><b>Space Complexity:</b> O(N × 32) - Store at most 32 × N OR values
 */
public class BitOrSubarray {
    
    /**
     * Counts distinct OR values from all subarrays.
     */
    public int subarrayBitwiseORs(int[] arr) {
        HashSet<Integer> result = new HashSet<>();
        HashSet<Integer> current = new HashSet<>();
        
        for (int num : arr) {
            HashSet<Integer> next = new HashSet<>();
            next.add(num);
            
            for (int prev : current) {
                next.add(prev | num);
            }
            
            result.addAll(next);
            current = next;
        }
        
        return result.size();
    }
}
