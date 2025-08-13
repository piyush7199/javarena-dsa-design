package org.example.coding.datastructures.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlusOne {
    /**
     * Adds one to the integer represented by the given array of digits.
     *
     * <p><b>Intuition:</b>
     * The array represents a non-negative integer where each element is a digit in base-10.
     * We need to simulate adding 1 to this number, taking care of carrying over when a digit becomes 10.
     *
     * <p><b>Approach (First Method):</b>
     * 1. Traverse the digits array from the last digit to the first.
     * 2. On the very first iteration (last digit), add 1 to it.
     *    On subsequent iterations, add any carry from the previous step.
     * 3. If a sum >= 10, store the last digit (sum % 10) and carry forward sum / 10.
     * 4. Store results temporarily in a List (reversed order), then reverse it at the end.
     * 5. If a carry remains after processing all digits, append it.
     * 6. Convert the list to an integer array and return it.
     *
     * <p><b>Time Complexity:</b> O(n)
     * <ul>
     *   <li>We traverse the array once, plus reverse and convert the list, which are both O(n).</li>
     * </ul>
     *
     * <p><b>Space Complexity:</b> O(n)
     * <ul>
     *   <li>We store digits in a new list before converting to an array.</li>
     * </ul>
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
     * Adds one to the integer represented by the given array of digits (Optimized version).
     *
     * <p><b>Intuition:</b>
     * Instead of using an extra list and reversing, we can modify the array in-place.
     * Carry will only propagate while we encounter 9's.
     *
     * <p><b>Approach:</b>
     * 1. Traverse the array from the last digit towards the first.
     * 2. If the current digit is less than 9:
     *    - Increment it by 1.
     *    - Return the array immediately (no carry propagation needed).
     * 3. If the digit is 9:
     *    - Set it to 0 and continue (carry is implied).
     * 4. If we exit the loop, it means all digits were 9's:
     *    - Create a new array of size n+1 with the first element as 1.
     *    - This handles cases like 999 → 1000.
     *
     * <p><b>Time Complexity:</b> O(n)
     * <ul>
     *   <li>We traverse the array once.</li>
     * </ul>
     *
     * <p><b>Space Complexity:</b> O(1) if we don't count the output array.
     * <ul>
     *   <li>Only a new array is created in the special case where all digits are 9's.</li>
     * </ul>
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
