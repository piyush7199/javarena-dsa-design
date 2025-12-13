package com.javarena.dsa.datastructures.stackAndQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Next Greater Element I
 *
 * <p><b>Problem Statement:</b><br>
 * Given nums1 (subset of nums2), find next greater element for each nums1 element in nums2.
 * Return -1 if no greater element exists.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Monotonic decreasing stack:
 * - Traverse nums2 left to right
 * - For each element, pop smaller elements from stack (found their NGE)
 * - Store element → NGE mapping in HashMap
 * - Push current element to stack
 * - Elements remaining in stack have no NGE (-1)
 * - Lookup nums1 elements in map
 * 
 * Stack maintains decreasing order, current element is NGE for all smaller popped elements.
 *
 * <p><b>Time Complexity:</b> O(M + N) - M = nums2 length, N = nums1 length
 * <br><b>Space Complexity:</b> O(M) - HashMap + stack
 */
public class NGE {
    /**
     * Finds next greater elements using monotonic stack.
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
