package org.example.coding.algorithms.bitManupulation;

public class MinimumBitFlipsToConvertNumber {
    /**
     * Returns the minimum number of bit flips required to convert
     * the integer 'start' into 'goal'.
     *
     * <p><b>Intuition:</b>
     * To transform one number into another, we only need to flip
     * the bits where they differ. By taking the XOR of 'start' and 'goal',
     * we get a number where each set bit (1) represents a differing bit position.
     * The problem reduces to counting the number of set bits in that XOR result.
     *
     * <p><b>Approach:</b>
     * 1. Compute diffBit = start ^ goal.
     * - This highlights differing bits (1 = different, 0 = same).
     * 2. Count the number of 1s in diffBit.
     * - Use bitwise AND with 1 to check the last bit.
     * - Right shift until diffBit becomes 0.
     * 3. Return the count as the minimum flips required.
     *
     * <p><b>Time Complexity:</b> O(log(max(start, goal)))
     * Because in each iteration, we process one bit of diffBit.
     *
     * <p><b>Space Complexity:</b> O(1)
     * We only use a constant amount of extra variables regardless of input size.
     */
    public int minBitFlips(int start, int goal) {
        int diffBit = start ^ goal;
        int ans = 0;
        while (diffBit != 0) {
            ans += (diffBit & 1);
            diffBit = diffBit >> 1;
        }
        return ans;
    }

}
