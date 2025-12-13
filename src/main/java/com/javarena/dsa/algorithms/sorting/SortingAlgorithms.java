package com.javarena.dsa.algorithms.sorting;

import java.util.*;

/**
 * Sorting Algorithms Collection
 *
 * <p><b>Problem Statement:</b><br>
 * Collection of fundamental sorting algorithms:
 * 1. Selection Sort - Find minimum, swap to front
 * 2. Bubble Sort - Repeatedly swap adjacent elements
 * 3. Insertion Sort - Build sorted array one element at a time
 * 4. Merge Sort - Divide and conquer with merging
 * 5. Quick Sort - Divide and conquer with partitioning
 * 6. Group Anagrams - Sort-based grouping problem
 *
 * <p><b>Intuition & Approach:</b><br>
 * Comparison-based sorting fundamentals:
 * 
 * Selection Sort (O(N²)):
 * - Find minimum in unsorted portion, swap to front
 * - Not stable, in-place
 * 
 * Bubble Sort (O(N²)):
 * - Repeatedly swap adjacent out-of-order elements
 * - Stable, in-place, largest bubbles to end
 * 
 * Insertion Sort (O(N²)):
 * - Build sorted portion by inserting elements correctly
 * - Stable, in-place, efficient for small/nearly sorted arrays
 * 
 * Merge Sort (O(N log N)):
 * - Divide array, sort halves, merge sorted halves
 * - Stable, requires O(N) extra space, guaranteed O(N log N)
 * 
 * Quick Sort (O(N log N) average, O(N²) worst):
 * - Choose pivot, partition around it, recursively sort partitions
 * - Not stable, in-place, fastest in practice
 *
 * <p><b>Time Complexity:</b> O(N²) for simple sorts, O(N log N) for advanced
 * <br><b>Space Complexity:</b> O(1) for in-place, O(N) for merge sort
 */
public class SortingAlgorithms {

    /**
     * Selection Sort: Find minimum and swap to front.
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
     * Bubble Sort: Repeatedly swap adjacent out-of-order elements.
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;  // Already sorted
        }
    }

    /**
     * Insertion Sort: Build sorted array by inserting elements correctly.
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
     * Merge Sort: Divide, sort, and merge.
     */
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) return;
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] L = new int[n1];
        int[] R = new int[n2];
        
        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    /**
     * Quick Sort: Partition around pivot and recursively sort.
     */
    public static void quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortHelper(arr, low, pi - 1);
            quickSortHelper(arr, pi + 1, high);
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

    /**
     * Group Anagrams: Sort-based grouping.
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }
        
        return new ArrayList<>(map.values());
    }

    /**
     * Utility: Swap two elements.
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Utility: Print array.
     */
    public static void print(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
