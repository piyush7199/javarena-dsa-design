package com.javarena.dsa.datastructures.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Array Utility Problems
 *
 * <p><b>Problem Statement:</b><br>
 * Collection of common array problems including finding missing/repeating elements and candy distribution.
 *
 * <p><b>Intuition & Approach:</b><br>
 * 1. Missing and Repeating: Use mathematical formulas
 *    - Calculate expected sum and sum of squares for 1 to n
 *    - Subtract actual values to get differences
 *    - Solve equations: missing - repeating and missing² - repeating²
 *    - Extract both values using algebra
 * 
 * 2. Kids with Candies: Simple comparison
 *    - Find maximum candies any kid has
 *    - For each kid, check if their candies + extra >= max
 *
 * <p><b>Time Complexity:</b> O(N) for both problems
 * <br><b>Space Complexity:</b> O(1) excluding output
 */
public class Arrays {

    /**
     * Finds the missing and repeating elements in array.
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

    /**
     * Determines which kids will have greatest number of candies after receiving extra.
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        ArrayList<Boolean> result = new ArrayList<>();
        int max = 0;
        for (int num : candies) {
            if (num > max)
                max = num;
        }
        for (int ca : candies) {
            if (ca + extraCandies >= max)
                result.add(true);
            else result.add(false);
        }
        return result;
    }
}
