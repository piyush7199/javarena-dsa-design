package com.javarena.dsa.algorithms.bitManupulation;

import java.util.Arrays;
import java.util.List;

/**
 * Swap Two Numbers
 *
 * <p><b>Problem Statement:</b><br>
 * Swap two integers a and b without using temporary variable.
 * Return swapped values as list [b, a].
 *
 * <p><b>Intuition & Approach:</b><br>
 * Two methods to swap without temp variable:
 * 
 * Method 1 - XOR (Preferred):
 * - Uses XOR property: x^x=0, x^0=x, XOR is reversible
 * - a = a ^ b, b = a ^ b (now b=original a), a = a ^ b (now a=original b)
 * - No overflow issues
 * 
 * Method 2 - Arithmetic:
 * - Uses addition: a = a + b, b = a - b, a = a - b
 * - Risk of overflow with large numbers
 * 
 * XOR method preferred for safety and elegance.
 *
 * <p><b>Time Complexity:</b> O(1) - Constant operations
 * <br><b>Space Complexity:</b> O(1) - No extra variables
 */
public class SwapTwoNumbers {
    
    /**
     * Swaps two numbers using XOR method.
     */
    static List<Integer> get(int a, int b) {
        a = a ^ b;
        b = a ^ b;  // b = (a ^ b) ^ b = a
        a = a ^ b;  // a = (a ^ b) ^ a = b
        
        return Arrays.asList(a, b);
    }
    
    /**
     * Alternative: Arithmetic method.
     */
    static List<Integer> getArithmetic(int a, int b) {
        a = a + b;
        b = a - b;  // b = (a + b) - b = a
        a = a - b;  // a = (a + b) - a = b
        
        return Arrays.asList(a, b);
    }
}
