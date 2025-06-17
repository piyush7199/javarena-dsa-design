package org.example.coding.datastructures.arrays;

public class MoveZeroToLeft {

    /**
     * Instead of shifting elements one by one (which is slow), we use a two-pointer approach from the end of the array:
     * One pointer (i) goes through every element from the end to the start.
     * The other pointer (j) tracks where to place the next non-zero element.
     * When we find a non-zero element, we swap it with the element at position j.
     * This pushes the non-zero elements toward the end of the array and leaves the beginning (the left side) for zeros.
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
