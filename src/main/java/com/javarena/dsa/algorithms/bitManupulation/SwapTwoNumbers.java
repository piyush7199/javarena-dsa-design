package com.javarena.dsa.algorithms.bitManupulation;

import java.util.Arrays;
import java.util.List;

/**
 * Swap Two Numbers
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://www.geeksforgeeks.org/problems/swap-two-numbers/1">GFG - Swap Two Numbers</a>
 *
 * <p><b>Difficulty:</b> Easy
 *
 * <p><b>Topics:</b> Bit Manipulation, Math
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Swap two given integers a and b without using a temporary variable. Return the swapped 
 * values as a list [b, a]. Implement two approaches: one using arithmetic operators and 
 * one using XOR bitwise operator.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: a = 5, b = 10
 * Output: [10, 5]
 * Explanation: After swapping, a becomes 10 and b becomes 5.
 *
 * Input: a = 1, b = 2
 * Output: [2, 1]
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Two approaches to swap without temporary variable:
 * - **Arithmetic Method:** Use addition and subtraction properties (a+b)-b=a, (a+b)-a=b
 * - **XOR Method:** Use XOR property where x^x=0 and x^0=x, making it reversible
 * - XOR method is preferred as it avoids potential overflow issues with large numbers
 * - Both methods achieve O(1) space complexity without extra variables
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li><b>Arithmetic Method:</b> a = a + b (stores sum)</li>
 *   <li>b = a - b (retrieves original a since a-b = (a+b)-b = a)</li>
 *   <li>a = a - b (retrieves original b since a-b = (a+b)-a = b)</li>
 *   <li><b>XOR Method:</b> a = a ^ b (XOR both values)</li>
 *   <li>b = a ^ b (XOR gives original a since (a^b)^b = a)</li>
 *   <li>a = a ^ b (XOR gives original b since (a^b)^a = b)</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(1)<br>
 * Both methods use only three constant-time operations regardless of input values.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * No temporary variables needed; swapping done in-place.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>a = b: Both methods work correctly, result is [a, a]</li>
 *   <li>Large numbers: XOR method preferred to avoid arithmetic overflow</li>
 *   <li>Negative numbers: Both methods handle negatives correctly</li>
 *   <li>Zero values: Works correctly for any combination with 0</li>
 * </ul>
 *
 * @see <a href="https://www.geeksforgeeks.org/problems/swap-two-nibbles-in-a-byte/1">Swap Nibbles</a>
 */
public class SwapTwoNumbers {
    
    /**
     * Swaps two numbers using arithmetic operators (addition and subtraction).
     *
     * @param a first integer to swap
     * @param b second integer to swap
     * @return List containing [b, a] (swapped values)
     */
    static List<Integer> getWithOperators(int a, int b) {
        // Step 1: Store sum in a
        a = a + b;
        
        // Step 2: Extract original a by subtracting b
        b = a - b;
        
        // Step 3: Extract original b by subtracting new b (original a)
        a = a - b;
        
        return Arrays.asList(a, b);
    }

    /**
     * Swaps two numbers using XOR bitwise operator (preferred method).
     *
     * @param a first integer to swap
     * @param b second integer to swap
     * @return List containing [b, a] (swapped values)
     */
    static List<Integer> getWithoutOperators(int a, int b) {
        // Step 1: a becomes a^b
        a = a ^ b;
        
        // Step 2: b becomes a^b^b = a (original a)
        b = a ^ b;
        
        // Step 3: a becomes a^b^a = b (original b)
        a = a ^ b;
        
        return Arrays.asList(a, b);
    }
}
