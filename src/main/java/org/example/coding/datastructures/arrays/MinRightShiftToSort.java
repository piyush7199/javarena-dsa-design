package org.example.coding.datastructures.arrays;

import java.util.List;

public class MinRightShiftToSort {
    /**
     * Finds the minimum number of right circular shifts required to make the given list sorted in non-decreasing order.
     * If it is not possible to do so with any number of right shifts, returns -1.
     *
     * <p><b>Intuition:</b></p>
     * A sorted and right-rotated array will have **at most one drop** (where a[i] > a[i+1]).
     * For example, [3, 4, 5, 1, 2] is a rotated version of [1, 2, 3, 4, 5] with one drop at 5 → 1.
     * This drop indicates the rotation point. If there is more than one such drop, the array cannot be sorted by rotation.
     *
     * <p><b>Approach:</b></p>
     * <ol>
     *     <li>Iterate through the array to find if there's more than one drop (i.e., nums[i] < nums[i-1]).</li>
     *     <li>If more than one drop exists, return -1.</li>
     *     <li>If no drop is found, the array is already sorted → return 0.</li>
     *     <li>Check that the array is properly rotated: the last element must be ≤ the first after rotation.</li>
     *     <li>If the rotation is valid, return the number of right shifts required: n - dropIndex.</li>
     * </ol>
     *
     * <p><b>Time Complexity:</b> O(n) – A single pass through the list.</p>
     * <p><b>Space Complexity:</b> O(1) – No extra space used except for variables.</p>
     */
    public static int minimumRightShifts(List<Integer> nums) {
        int n = nums.size();
        int drop = -1;

        for (int i = 1; i < n; i++) {
            if (nums.get(i) < nums.get(i - 1)) {
                if (drop != -1) return -1; // More than one drop
                drop = i;
            }
        }

        if (drop == -1) return 0; // Already sorted
        if (nums.get(n - 1) > nums.get(0)) return -1; // Invalid rotation

        return n - drop; // Valid rotation
    }

}
