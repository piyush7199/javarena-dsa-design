package org.example.coding.algorithms.recursionAndBacktracking;

public class CountMaxBitOr {

    /**
     * Counts the number of subsets from the given array `nums` such that the
     * bitwise OR of the elements in the subset is equal to the maximum bitwise OR
     * possible from any subset of the array.
     *
     * <p><strong>Initialization:</strong>
     * <ul>
     *   <li>Compute the maximum bitwise OR value (`max`) of the entire array.</li>
     *   <li>Use a recursive helper function {@code getCount} to explore all subset combinations,
     *       starting from index 0 and OR value 0.</li>
     * </ul>
     *
     * <p><strong>Approach:</strong>
     * <ul>
     *   <li>Use a recursive DFS strategy to either include or exclude each element.</li>
     *   <li>Track the current OR value (`val`) as the recursion proceeds.</li>
     *   <li>If the current OR value equals `max`, all remaining subsets from this point
     *       are guaranteed to have OR value equal to `max`, so return {@code 1 << (n - index)}
     *       as the count (optimization to skip remaining recursion).</li>
     *   <li>Base case: if the index reaches the end of the array, return 1 if the OR
     *       value equals `max`, otherwise return 0.</li>
     * </ul>
     *
     *
     * <p><strong>Time Complexity:</strong> O(2^n), where n is the length of the input array.
     * Although there’s an optimization for early exit when OR reaches `max`, in the worst case
     * all subsets must still be considered.
     *
     * <p><strong>Space Complexity:</strong> O(n) for the recursion call stack in the worst case.
     */
    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        int max = 0;

        // Step 1: Find the maximum OR possible across all subsets.
        for (int ele : nums) {
            max |= ele;
        }

        // Step 2: Use DFS to count the subsets that result in this max OR.
        return getCount(nums, 0, 0, max);
    }

    /**
     * Recursive helper function to count valid subsets.
     */
    private int getCount(int[] nums, int ind, int val, int max) {
        if (nums.length == ind) {
            return val == max ? 1 : 0;
        }

        // Optimization: if current OR value is already max, all remaining subsets will also be valid
        if (val == max) {
            return 1 << (nums.length - ind); // 2^(remaining elements)
        }

        // Recursive cases: pick or don't pick current element
        int pick = getCount(nums, ind + 1, val | nums[ind], max);
        int notPick = getCount(nums, ind + 1, val, max);

        return pick + notPick;
    }
}
