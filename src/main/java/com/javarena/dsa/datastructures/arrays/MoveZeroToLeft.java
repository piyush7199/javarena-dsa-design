package com.javarena.dsa.datastructures.arrays;

/**
 * Move Zeros to Left
 *
 * <p><b>Problem Statement:</b><br>
 * Move all zeros in the array to the left side while maintaining the relative order of non-zero elements 
 * on the right side.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use two-pointer approach from end of array (reverse of "move zeros to right")
 * - Pointer i: traverse from end to start
 * - Pointer j: track where to place next non-zero element from right
 * - When non-zero found at i, swap arr[i] with arr[j], decrement j
 * - This pushes non-zeros toward right end, leaving left side for zeros
 * - Single pass, in-place solution
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through array
 * <br><b>Space Complexity:</b> O(1) - In-place swapping
 */
public class MoveZeroToLeft {

    /**
     * Moves all zeros to left side of array.
     */
    public static void moveZeroToLeft(int[] arr) {
        int i = arr.length - 1;
        int j = arr.length - 1;
        while (i >= 0) {
            if (arr[i] != 0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j--;
            }
            i--;
        }
    }
}
