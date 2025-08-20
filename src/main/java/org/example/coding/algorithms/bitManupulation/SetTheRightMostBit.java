package org.example.coding.algorithms.bitManupulation;

public class SetTheRightMostBit {
    /**
     * Sets the rightmost unset (0) bit of the given integer `n`.
     *
     * <p><b>Intuition:</b>
     * - To set the rightmost 0 bit, we need to scan from the least significant bit (LSB)
     * until we find the first unset (0) bit.
     * - Once found, we set that bit to 1 by using bitwise OR with (1 << position).
     *
     * <p><b>Approach:</b>
     * 1. Start with a copy of n.
     * 2. Right-shift until the least significant bit is 0.
     * 3. Count how many shifts we did → that’s the position of the first 0 bit.
     * 4. Set that bit in the original number using bitwise OR.
     *
     * <p><b>Time Complexity:</b>
     * O(log n) in the worst case, since we may need to shift through all bits.
     *
     * <p><b>Space Complexity:</b>
     * O(1), as we only use a constant amount of extra variables.
     */
    static int setBit(int n) {
        int unsetBit = 0;
        int m = n;
        while ((m & 1) != 0) {
            unsetBit++;
            m = m >> 1;
        }
        return n | (1 << unsetBit);
    }


    /**
     * Sets the rightmost unset (0) bit of the given integer `n` without using a loop.
     *
     * <p><b>Intuition:</b>
     * - Adding 1 to a number flips the rightmost sequence of 1’s to 0’s, and the first 0 to 1.
     * - Therefore, `n + 1` has the rightmost unset bit of `n` set to 1, with lower bits reset.
     * - Performing OR (`n | (n + 1)`) ensures that bit is set in `n`, without affecting other bits.
     *
     * <p><b>Approach:</b>
     * 1. Simply compute `n | (n + 1)`.
     * 2. This ensures the first unset bit in `n` is set to 1.
     *
     * <p><b>Time Complexity:</b>
     * O(1), since it uses only one addition and one OR operation.
     *
     * <p><b>Space Complexity:</b>
     * O(1), as no extra space is used.
     */
    static int setBitWithoutLoop(int n) {
        return n | (n + 1);
    }

}
