package org.example.coding.datastructures.arrays;

public class KthLargetElement {
    /**
     * There are three common approaches to solve this problem:
     * <p>
     * 1. **Sorting**:
     * - Sort the array in ascending order and return the element at position `n - k`.
     * - Time Complexity: O(n log n)
     * - Space Complexity: O(1) if sorting in-place.
     * <p>
     * 2. **Min-Heap (Priority Queue)**:
     * - Maintain a min-heap of size `k` while traversing the array.
     * - For each element, add it to the heap. If the heap exceeds size `k`, remove the smallest.
     * - The top of the heap will be the k-th largest element.
     * - Time Complexity: O(n log k)
     * - Space Complexity: O(k)
     * <p>
     * 3. **Counting Sort-based Frequency Array (Optimized for Bounded Range)**:
     * - Use when the range between min and max values is not very large.
     * - Steps:
     * a. Find min and max values in the array.
     * b. Create a count array of size `max - min + 1`.
     * c. Count the frequency of each number.
     * d. Iterate the count array in reverse (descending order of actual values).
     * Keep decrementing `k` by the frequency count until `k <= 0`.
     * The current index maps back to the k-th largest number.
     * - Time Complexity: O(n + r), where `r = max - min`
     * - Space Complexity: O(r)
     * - Ideal when `r` is not significantly larger than `n`
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
