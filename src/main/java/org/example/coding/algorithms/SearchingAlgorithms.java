package org.example.coding.algorithms;

public class SearchingAlgorithms {

    /**
     * Linear Search is the simplest search algorithm that checks every element
     * of the array sequentially until the desired element is found or the end
     * of the array is reached.
     *
     * <p>
     * This algorithm is useful for unsorted or small datasets.
     * </p>
     *
     * <h3>Time Complexity:</h3>
     * <ul>
     *   <li>Best Case: O(1) – when the target is the first element</li>
     *   <li>Average Case: O(n)</li>
     *   <li>Worst Case: O(n) – when the target is not present or is the last element</li>
     * </ul>
     *
     * <p><b>Space Complexity:</b> O(1)</p>
     *
     * @param arr the array to search
     * @param key the value to search for
     * @return the index of the key if found, otherwise -1
     */
    public int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Binary Search is an efficient algorithm for finding an element's position
     * in a sorted array by repeatedly dividing the search interval in half.
     *
     * <p>
     * It uses the fact that the array is sorted to eliminate half of the remaining
     * elements from consideration in each step.
     * </p>
     *
     * <h3>Preconditions:</h3>
     * <ul>
     *   <li>The array must be sorted in ascending order</li>
     *   <li>Random (constant-time) access to elements is available</li>
     * </ul>
     *
     * <h3>Algorithm Steps:</h3>
     * <ol>
     *   <li>Calculate the middle index: <code>mid = (low + high) / 2</code></li>
     *   <li>If <code>arr[mid] == key</code>, return mid</li>
     *   <li>If <code>arr[mid] > key</code>, repeat the process on the left half</li>
     *   <li>If <code>arr[mid] < key</code>, repeat the process on the right half</li>
     *   <li>Repeat until the element is found or the search space is empty</li>
     * </ol>
     *
     * <h3>Time Complexity:</h3>
     * <ul>
     *   <li>Best Case: O(1)</li>
     *   <li>Average and Worst Case: O(log n)</li>
     * </ul>
     *
     * <p><b>Space Complexity:</b> O(1) for iterative version, O(log n) for recursive version due to stack calls</p>
     *
     * @param arr the sorted array to search
     * @param key the value to search for
     * @return the index of the key if found, otherwise -1
     */
    public int binarySearch(int[] arr, int key) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == key)
                return mid;
            else if (arr[mid] < key)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

}
