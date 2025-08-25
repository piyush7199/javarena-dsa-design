package org.example.coding.datastructures.stackAndQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NGE {
    /**
     * Finds the next greater element for each element in nums1 with respect to nums2.
     *
     * <p><b>Problem Intuition:</b><br>
     * Given two arrays, nums1 and nums2 (where nums1 is a subset of nums2),
     * for each element in nums1, we want to find the first greater element
     * to its right in nums2. If no greater element exists, we return -1 for that element.
     *
     * <p><b>Approach:</b><br>
     * - We use a monotonic decreasing stack to efficiently find the "next greater element"
     * for each number in nums2:
     *   <ul>
     *     <li>Traverse nums2 from left to right.</li>
     *     <li>While the stack is not empty and the current number is greater than
     *         the top of the stack, it means we have found the "next greater element"
     *         for the stack's top. Pop it and record the mapping in a HashMap.</li>
     *     <li>Push the current number onto the stack.</li>
     *   </ul>
     * - After processing nums2, the HashMap will contain the next greater element
     *   for all numbers that have one. Any number left in the stack has no next
     *   greater element (implicitly -1).
     * - Finally, we overwrite nums1 by looking up each element in the map,
     *   defaulting to -1 if no mapping exists.
     *
     * <p><b>Time Complexity:</b><br>
     * O(n + m), where n = nums1.length and m = nums2.length.
     * - Each element in nums2 is pushed and popped at most once from the stack → O(m).
     * - Lookup for each nums1 element in the HashMap is O(1) average → O(n).
     *
     * <p><b>Space Complexity:</b><br>
     * O(m) for the HashMap and the stack.
     * The HashMap stores at most m key-value pairs, and the stack holds at most m elements.
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> st = new Stack<>();
        Map<Integer, Integer> mp = new HashMap<>();

        for (int num : nums2) {
            while (!st.isEmpty() && num > st.peek()) {
                mp.put(st.pop(), num);
            }
            st.push(num);
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = mp.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }

}
