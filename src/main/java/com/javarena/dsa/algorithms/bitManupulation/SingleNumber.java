package com.javarena.dsa.algorithms.bitManupulation;

/**
 * 136. Single Number
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/single-number/">LeetCode - Single Number</a>
 *
 * <p><b>Difficulty:</b> Easy
 *
 * <p><b>Topics:</b> Array, Bit Manipulation
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given a non-empty array of integers nums, every element appears twice except for one. 
 * Find that single one. You must implement a solution with a linear runtime complexity 
 * and use only constant extra space.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: nums = [2,2,1]
 * Output: 1
 * Explanation: The number 1 appears only once while 2 appears twice.
 *
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * The key insight is to use the XOR bitwise operation properties:
 * - XOR of a number with itself is 0 (a ⊕ a = 0)
 * - XOR of a number with 0 is the number itself (a ⊕ 0 = a)
 * - XOR operation is commutative and associative
 * - Therefore, XOR of all numbers will cancel out duplicates, leaving only the single number
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Initialize result variable to 0</li>
 *   <li>Iterate through all numbers in the array</li>
 *   <li>XOR each number with the result</li>
 *   <li>The final result will be the single number (all pairs cancel out)</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n)<br>
 * We iterate through the array once, performing O(1) XOR operation for each element.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * We only use a single integer variable to store the result, regardless of input size.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Array with single element: Returns that element directly</li>
 *   <li>All numbers are negative: XOR works the same way with negative numbers</li>
 *   <li>Single number is zero: Properly handled by XOR properties</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/single-number-ii/">Single Number II</a>
 * @see <a href="https://leetcode.com/problems/single-number-iii/">Single Number III</a>
 */
public class SingleNumber {
    
    /**
     * Finds the single number that appears only once using XOR bit manipulation.
     *
     * @param nums array of integers where every element appears twice except one
     * @return the single number that appears only once
     */
    public int singleNumber(int[] nums) {
        // Step 1: Initialize result to 0
        int ans = 0;
        
        // Step 2: XOR all numbers - pairs will cancel out
        for (int ele : nums) {
            ans ^= ele;  // XOR operation: same numbers cancel to 0
        }
        
        // Step 3: Return the single number (all others cancelled out)
        return ans;
    }
    
    /**
     * Alternative approach using HashSet - for educational comparison.
     * 
     * <p><b>Time:</b> O(n) - same as XOR approach
     * <br><b>Space:</b> O(n) - requires extra space for set vs O(1) for XOR
     * <br><b>Trade-off:</b> More intuitive but uses extra space, violates problem constraint
     *
     * @param nums array of integers
     * @return the single number
     */
    public int singleNumberUsingSet(int[] nums) {
        java.util.Set<Integer> set = new java.util.HashSet<>();
        
        // Add if not present, remove if present
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        
        // Only one number remains
        return set.iterator().next();
    }
}
