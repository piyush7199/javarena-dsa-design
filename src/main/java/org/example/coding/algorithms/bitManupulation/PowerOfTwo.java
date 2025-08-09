package org.example.coding.algorithms.bitManupulation;

public class PowerOfTwo {
    /**
     * Checks if a given integer is a power of two using a brute force approach.
     *
     * <p><b>Intuition:</b>
     * A number is a power of two if it can be repeatedly divided by 2 until we reach 1,
     * without ever producing an odd number in the process (except 1 itself).
     * For example, 8 → 4 → 2 → 1.
     *
     * <p><b>Approach:</b>
     * - If the number is less than or equal to 0, return false (since powers of two are positive).
     * - Keep dividing the number by 2 while it is greater than 1.
     * - If at any point the number is not divisible by 2 (odd), return false.
     * - If the loop ends, it is a power of two.
     *
     * <p><b>Time Complexity:</b> O(log n)
     * - Each division by 2 reduces the number by half, so it runs in logarithmic time.
     *
     * <p><b>Space Complexity:</b> O(1)
     * - Only a constant amount of memory is used.
     */
    public boolean powerOfTwo(int n) {
        if (n <= 0) return false;
        while (n > 1) {
            if (n % 2 != 0) return false;
            n = n / 2;
        }
        return true;
    }

    /**
     * Checks if a given integer is a power of two using bit manipulation.
     *
     * <p><b>Intuition:</b>
     * In binary, a power of two has exactly one set bit (e.g., 1 → 0001, 2 → 0010, 4 → 0100, 8 → 1000).
     * If we subtract 1 from such a number, all bits after the set bit become 1 and the set bit becomes 0.
     * For example:
     * <pre>
     *   8 (1000)
     *   7 (0111)
     *   8 & 7 → 0 (0000)
     * </pre>
     * This property can be used to check for powers of two.
     *
     * <p><b>Approach:</b>
     * - If the number is less than or equal to 0, return false.
     * - Use the expression {@code (n & (n - 1)) == 0} to check if there is only one set bit.
     *
     * <p><b>Time Complexity:</b> O(1)
     * - Only a constant number of bitwise operations are performed.
     *
     * <p><b>Space Complexity:</b> O(1)
     * - No extra memory is used.
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }

}
