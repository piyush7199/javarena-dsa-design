package org.example.coding.datastructures.arrays;

public class MaxUniqueSubArray {
    /**
     * Calculates the maximum possible sum from the given integer array `nums` by
     * including only the distinct positive elements. If all elements are negative,
     * it returns the maximum (least negative) number in the array.
     *
     * <p><b>Intuition:</b></p>
     * <ul>
     *   <li>We only want to consider positive numbers because negative numbers would reduce the sum.</li>
     *   <li>To avoid duplicates, we use a fixed-size boolean-like array `arr` of size 101
     *       (because values range from 0 to 100) to mark the presence of each positive number.</li>
     *   <li>If all numbers are negative, the best we can do is return the maximum among them.</li>
     * </ul>
     *
     * <p><b>Steps:</b></p>
     * <ol>
     *   <li>Traverse the array once to:
     *       <ul>
     *         <li>Mark the presence of positive numbers.</li>
     *         <li>Track whether all numbers are negative.</li>
     *         <li>Keep track of the maximum number.</li>
     *       </ul>
     *   </li>
     *   <li>If all numbers are negative, return the maximum number.</li>
     *   <li>Otherwise, sum all distinct positive numbers using the presence array.</li>
     * </ol>
     *
     * <p><b>Time Complexity:</b> O(n)</p>
     * <ul>
     *   <li>We iterate over the array `nums` once — O(n)</li>
     *   <li>We iterate over the fixed-size array `arr` of size 101 — O(101) = O(1)</li>
     *   <li>Total: O(n)</li>
     * </ul>
     *
     * <p><b>Space Complexity:</b> O(1)</p>
     * <ul>
     *   <li>The extra space used is a fixed-size array of 101 elements, which is constant space.</li>
     *   <li>No space usage depends on the input size `n`.</li>
     * </ul>
     */
    public int maxSum(int[] nums) {
        int[] arr = new int[101]; // to mark presence of numbers from 0 to 100
        boolean allNeg = true;
        int ans = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num >= 0) {
                arr[num] = 1;
                allNeg = false;
            }
            ans = Math.max(ans, num); // keep track of max element (even if all are negative)
        }

        if (allNeg) return ans; // if all numbers are negative, return the max among them

        int result = 0;
        for (int i = 1; i < 101; i++) {
            if (arr[i] > 0) {
                result += i; // add all distinct positive numbers
            }
        }

        return result;
    }

}
