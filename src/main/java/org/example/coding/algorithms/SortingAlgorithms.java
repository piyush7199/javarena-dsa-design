package org.example.coding.algorithms;

import java.util.*;

public class SortingAlgorithms {

    /**
     * Selection Sort is a comparison-based sorting algorithm.
     * <p>
     * It sorts an array by repeatedly selecting the smallest (or largest)
     * element from the unsorted portion and swapping it with the first
     * unsorted element. This process continues until the entire array is sorted.
     * </p>
     *
     * <p>
     * The sorting process involves the following steps:
     * <ul>
     *   <li>Find the smallest element in the array and swap it with the first element.
     *       This places the smallest element in its correct position.</li>
     *   <li>Next, find the smallest element from the remaining unsorted portion
     *       (i.e., the second smallest) and swap it with the second element.</li>
     *   <li>Repeat this process, moving the boundary of the sorted portion forward,
     *       until all elements are in their correct positions.</li>
     * </ul>
     * </p>
     * <p>
     * Complexity
     * <ul>
     *   <li>Time complexity:{@code O(n^2)}</li>
     *   <li>Space complexity:{@code O(1)}</li>
     * </ul>
     * </p>
     * <p>
     * <b>Note:</b> Selection Sort is <b>not a stable sorting algorithm</b>. Elements with equal keys may not retain
     * their original relative order because swaps are based solely on pivot comparisons.
     * </p>
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * Bubble Sort is the simplest sorting algorithm that works by repeatedly
     * swapping adjacent elements if they are in the wrong order.
     * <p>
     * This algorithm is not suitable for large data sets as its average and
     * worst-case time complexity are quite high.
     * </p>
     *
     * <p>
     * The array is sorted using multiple passes:
     * <ul>
     *   <li>After the first pass, the maximum element moves to the end (its correct position).</li>
     *   <li>After the second pass, the second largest element moves to the second last position, and so on.</li>
     *   <li>After <code>k</code> passes, the largest <code>k</code> elements are in the last <code>k</code> positions.</li>
     * </ul>
     * </p>
     *
     * <p>
     * In each pass, only the elements not already in their correct positions are processed.
     * We compare adjacent elements and swap them if the left element is greater than the right one.
     * </p>
     *
     * <p><b>Time Complexity:</b> O(n<sup>2</sup>)</p>
     * <p><b>Auxiliary Space:</b> O(1)</p>
     * <b>Note:</b> Bubble Sort is <b>not a stable sorting algorithm</b>. Elements with equal keys may not retain
     * their original relative order because swaps are based solely on pivot comparisons.
     * </p>
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * Insertion Sort is a simple sorting algorithm that works by iteratively
     * inserting each element of an unsorted list into its correct position
     * in the sorted portion of the list.
     * <p>
     * It is analogous to sorting playing cards in your hands. You divide the cards
     * into two groups: a sorted group and an unsorted group. Then, you pick a card
     * from the unsorted group and insert it into its correct position in the sorted group.
     * </p>
     *
     * <p>
     * The process is as follows:
     * <ul>
     *   <li>Start with the second element of the array, assuming the first element is already sorted.</li>
     *   <li>Compare the current element with elements in the sorted portion.</li>
     *   <li>Shift larger elements one position to the right to make space.</li>
     *   <li>Insert the current element into its correct position in the sorted portion.</li>
     *   <li>Repeat until the entire array is sorted.</li>
     * </ul>
     * </p>
     *
     * <p><b>Time Complexity:</b></p>
     * <ul>
     *   <li>Best Case: O(n) – when the list is already sorted</li>
     *   <li>Average Case: O(n<sup>2</sup>) – when elements are in random order</li>
     *   <li>Worst Case: O(n<sup>2</sup>) – when the list is in reverse order</li>
     * </ul>
     *
     * <p><b>Space Complexity:</b> O(1) – it is an in-place sorting algorithm</p>
     *
     * <p>
     * This is a <b>stable sorting algorithm</b>, meaning that elements with the same key
     * maintain their relative order in the sorted output.
     * </p>
     */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * Merge Sort is a popular, efficient, and stable sorting algorithm that follows the
     * divide-and-conquer strategy.
     * <p>
     * The algorithm works by recursively dividing the input array into two halves,
     * sorting each half, and then merging the sorted halves to produce a fully sorted array.
     * </p>
     *
     * <h3>How Merge Sort Works:</h3>
     * <ul>
     *   <li><b>Divide:</b> Recursively divide the array into two halves until the subarrays contain a single element.</li>
     *   <li><b>Conquer:</b> Sort each of the subarrays individually using merge sort.</li>
     *   <li><b>Merge:</b> Merge the sorted subarrays back together to form a single sorted array.</li>
     * </ul>
     *
     * <h3>Time Complexity:</h3>
     * <ul>
     *   <li>Best Case: O(n log n) – when the array is already sorted or nearly sorted.</li>
     *   <li>Average Case: O(n log n) – when the array is in random order.</li>
     *   <li>Worst Case: O(n log n) – when the array is sorted in reverse order.</li>
     * </ul>
     *
     * <h3>Space Complexity:</h3>
     * <ul>
     *   <li>Auxiliary Space: O(n) – extra space is required for merging subarrays.</li>
     * </ul>
     *
     * <p>
     * Merge Sort is a <b>stable sorting algorithm</b>, meaning that it preserves the relative
     * order of equal elements in the array.
     * </p>
     */
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] l = new int[n1];
        int[] r = new int[n2];

        for (int i = 0; i < n1; i++) {
            l[i] = arr[left + i];
        }

        for (int i = 0; i < n2; i++) {
            r[i] = arr[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (l[i] <= r[j]) {
                arr[k] = l[i];
                i++;
            } else {
                arr[k] = r[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = l[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = r[j];
            j++;
            k++;
        }
    }

    /**
     * Quick Sort is a highly efficient sorting algorithm based on the
     * divide-and-conquer strategy. It selects a pivot element and partitions the array
     * around the pivot such that elements less than the pivot go to the left,
     * and elements greater than the pivot go to the right.
     *
     * <h3>Algorithm Steps:</h3>
     * <ul>
     *   <li><b>Choose a Pivot:</b> Select an element from the array as the pivot. The choice of pivot can vary—
     *       it can be the first element, last element, a random element, or the median.</li>
     *   <li><b>Partition the Array:</b> Rearrange the array so that all elements less than the pivot
     *       are on the left and all greater elements are on the right. The pivot is placed in its correct sorted position.</li>
     *   <li><b>Recursively Call:</b> Recursively apply the same process to the subarrays to the left and right of the pivot.</li>
     *   <li><b>Base Case:</b> The recursion stops when the subarray has one or no elements,
     *       as such arrays are already sorted.</li>
     * </ul>
     *
     * <h3>Complexity Analysis:</h3>
     * <ul>
     *   <li><b>Best Case:</b> Ω(n log n) – when the pivot divides the array into two equal halves.</li>
     *   <li><b>Average Case:</b> Θ(n log n) – typical case with reasonably balanced partitions.</li>
     *   <li><b>Worst Case:</b> O(n²) – when the smallest or largest element is always chosen as pivot (e.g., sorted input).</li>
     *   <li><b>Auxiliary Space:</b> O(n) – due to recursive call stack.</li>
     * </ul>
     *
     * <p>
     * <b>Note:</b> Quick Sort is <b>not a stable sorting algorithm</b>. Elements with equal keys may not retain
     * their original relative order because swaps are based solely on pivot comparisons.
     * </p>
     */
    public static void quickSort(int[] arr) {
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pi = partition(arr, left, right);
            quickSort(arr, left, pi);
            quickSort(arr, pi + 1, right);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void print(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {-4, 5, 6, 7, 6, 2, 7, 6, 2};
        quickSort(arr);
        print(arr);
    }
}


class ProblemsSorting {

    /**
     * Groups a list of strings into anagram groups.
     *
     * <p><b>Problem:</b></p>
     * Given an array of strings `strs`, group the anagrams together.
     * Two strings are anagrams if they contain the same characters in a different order.
     * The result can be in any order.
     *
     * <p><b>Intuition:</b></p>
     * - For each string, we sort its characters alphabetically to form a key.
     * - All anagrams will have the same sorted key (e.g., "eat", "tea", "ate" => "aet").
     * - Use a HashMap to collect all strings that share the same sorted key.
     * - Finally, return all values from the map as grouped lists.
     *
     * <p><b>Time Complexity:</b> O(n * k log k)</p>
     * - n = number of strings
     * - k = maximum length of a string
     * - Each string is sorted individually, which takes O(k log k).
     *
     * <p><b>Space Complexity:</b> O(n * k)</p>
     * - For storing the grouped anagrams in a HashMap and final result list.
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramMap = new HashMap<>();

        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);
            anagramMap.computeIfAbsent(sortedStr, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(anagramMap.values());
    }
}
