package org.example.coding.datastructures.arrays;

import java.util.ArrayList;

public class Arrays {

    /**
     * Finds the missing and repeating elements in an array of size `n` containing
     * numbers from 1 to n where one number is repeated and one is missing.
     * <p>
     * Intuition:
     * - The sum and sum of squares of 1 to n are known.
     * - Subtract actual values to get the difference of missing - repeating and missing^2 - repeating^2.
     * - Use equations to find missing and repeating.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ArrayList<Integer> findTwoElement(int arr[]) {
        ArrayList<Integer> ans = new ArrayList<>();
        int n = arr.length;
        long s = (long) ((long) n * (n + 1)) / 2;
        long ssq = (long) (n * (n + 1) * (2L * n + 1)) / 6;
        long missing = 0, repeating = 0;

        for (int j : arr) {
            s -= j;
            ssq -= (long) j * j;
        }

        missing = ((s + ssq) / s) / 2;
        repeating = missing - s;
        ans.add((int) missing);
        ans.add((int) repeating);
        return ans;
    }
}
