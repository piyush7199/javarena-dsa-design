package com.javarena.dsa.datastructures.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Plus One
 *
 * <p><b>Problem Statement:</b><br>
 * Given a large integer represented as an array of digits, increment the integer by one and return the result.
 * Each element represents a single digit (0-9), with the most significant digit at the start.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Two approaches:
 * 
 * 1. With Extra Space: Use ArrayList, add from right with carry, reverse
 * 2. Optimized In-Place:
 *    - Traverse from right to left
 *    - If digit < 9: increment and return immediately (no carry)
 *    - If digit == 9: set to 0, continue (carry implied)
 *    - If loop completes (all 9s like 999): create new array [1,0,0,0...]
 * 
 * Handle carry propagation: 999 → 1000
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass for optimal
 * <br><b>Space Complexity:</b> O(1) for optimal (O(N) for first approach)
 */
public class PlusOne {
    /**
     * Adds one using ArrayList and reversing.
     */
    public int[] plusOne(int[] digits) {
        List<Integer> ans = new ArrayList<>();
        int carry = 0;
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            int sum = digits[i];
            if (i == n - 1) {
                sum++;
            } else {
                sum += carry;
            }
            carry = sum / 10;
            ans.add(sum % 10);
        }
        if (carry != 0) ans.add(carry);
        Collections.reverse(ans);
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    /**
     * Optimized in-place approach handling carry efficiently.
     */
    public int[] plusOneEfficeent(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

}
