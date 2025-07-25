package org.example.coding.datastructures.arrays;

public class KthLargetElement {
    /**
     * 1. We can sort the array then return n-kth element;
     * 2. We can use heap that and put ele into min heap if size of heap is hreader than k we will remove the top element
     * and then at last we will return peek;
     * In both the above case time c is o logn
     * 3. We can use below approach where we first find min and max ele;
     * create a array with that and now count the occurance of each ele;
     * now when we iter from back array is sorted, if at any time k is less than 0 we will return
     */
    public int findKthLargest(int[] nums, int k) {

        int minValue = java.util.Arrays.stream(nums).min().getAsInt();
        int maxValue = java.util.Arrays.stream(nums).max().getAsInt();

        int[] count = new int[maxValue - minValue + 1];

        for (int num : nums) {
            count[num - minValue]++;
        }

        int remaining = k;
        for (int i = count.length - 1; i >= 0; i--) {
            remaining -= count[i];

            if (remaining <= 0) {
                return i + minValue;
            }
        }

        return -1; // This line should not be reached
    }
}
