package com.javarena.dsa.datastructures.arrays;

/**
 * Kth Largest Element in an Array
 *
 * <p><b>Problem Statement:</b><br>
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element 
 * in sorted order, not the kth distinct element.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Multiple approaches exist:
 * 
 * 1. Sorting: O(N log N) - Sort and return nums[n-k]
 * 
 * 2. Min-Heap: O(N log K) - Maintain heap of size k
 * 
 * 3. Counting Sort (Bounded Range): O(N + R) where R = max - min
 *    - Find min and max values
 *    - Create frequency array of size (max - min + 1)
 *    - Count occurrences of each number
 *    - Iterate from end (largest values) decrementing k
 *    - When k <= 0, current index maps to kth largest
 *    - Optimal when range is not much larger than n
 *
 * <p><b>Time Complexity:</b> O(N + R) for counting sort, where R = max - min
 * <br><b>Space Complexity:</b> O(R) for frequency array
 */
public class KthLargetElement {
    /**
     * Finds kth largest element using counting sort approach.
     */
    public int findKthLargest(int[] nums, int k) {
        // Step 1: Find the minimum and maximum elements in the array
        int minValue = java.util.Arrays.stream(nums).min().getAsInt();
        int maxValue = java.util.Arrays.stream(nums).max().getAsInt();

        // Step 2: Create a frequency array to count occurrences of each element
        int[] count = new int[maxValue - minValue + 1];
        for (int num : nums) {
            count[num - minValue]++;
        }

        // Step 3: Iterate from the end (representing larger values) to find the k-th largest
        int remaining = k;
        for (int i = count.length - 1; i >= 0; i--) {
            remaining -= count[i];
            if (remaining <= 0) {
                return i + minValue; // Recover actual value
            }
        }

        return -1; // Should never be reached if input is valid
    }
}
