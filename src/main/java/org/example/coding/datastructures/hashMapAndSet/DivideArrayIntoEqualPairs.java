package org.example.coding.datastructures.hashMapAndSet;

public class DivideArrayIntoEqualPairs {
    /**
     * Checks if the given array can be divided into pairs where both elements in each pair are equal.
     *
     * <p><b>Intuition:</b>
     * A valid division into pairs is possible only if every number appears an even number of times.
     * Instead of sorting (which costs O(n log n)), we track the frequency of each number and count
     * how many pairs are formed as we go.
     *
     * <p><b>Approach:</b>
     * <ul>
     *   <li>Use a fixed-size frequency array (since numbers are in range 0–500) to count occurrences.</li>
     *   <li>Iterate through the array once:
     *     <ul>
     *       <li>If the current count of a number is odd before incrementing, adding this occurrence
     *           will complete a pair → increment the pair counter.</li>
     *       <li>Increment the frequency for the current number.</li>
     *     </ul>
     *   </li>
     *   <li>At the end, if the number of formed pairs equals n/2, return true; else false.</li>
     * </ul>
     *
     * <p><b>Time Complexity:</b> O(n) — single pass through the array.
     * <br><b>Space Complexity:</b> O(1) — uses a fixed-size frequency array of length 501 regardless of input size.
     */
    public boolean divideArray(int[] nums) {
        int[] freq = new int[501];
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (freq[nums[i]] % 2 == 1) {
                count++;
            }
            freq[nums[i]]++;
        }

        return count == n / 2;
    }

}
