package com.javarena.dsa.datastructures.arrays;

import java.util.List;

/**
 * Minimum Right Shifts to Sort Array
 *
 * <p><b>Problem Statement:</b><br>
 * Find the minimum number of right circular shifts required to make the list sorted in non-decreasing order.
 * Return -1 if impossible.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Sorted and rotated array has at most one "drop" where nums[i] < nums[i-1]
 * - Example: [3,4,5,1,2] has one drop at 5→1 (rotated from [1,2,3,4,5])
 * - More than one drop means impossible to sort by rotation
 * - Find the drop index by scanning array
 * - If no drop, already sorted, return 0
 * - Validate rotation: last element must be ≤ first element
 * - If valid, right shifts needed = n - dropIndex
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through array
 * <br><b>Space Complexity:</b> O(1) - Only variables for tracking
 */
public class MinRightShiftToSort {
    /**
     * Finds minimum right shifts to sort array.
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
