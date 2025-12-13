package com.javarena.dsa.datastructures.stackAndQueue;

import java.util.Stack;

/**
 * Number of Visible People in a Queue
 *
 * <p><b>Problem Statement:</b><br>
 * Given heights array, for each person determine how many people to their right they can see.
 * Person i can see person j if all people between them are shorter.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Monotonic decreasing stack from right to left:
 * - Traverse right → left (visibility is rightward)
 * - For each person, count visible people:
 *   - Pop all shorter people (they're visible and blocked after)
 *   - If stack not empty after popping, see one taller person
 * - Push current person's height
 * - Stack maintains decreasing order of heights
 * 
 * Each person pushed/popped once → efficient counting.
 *
 * <p><b>Time Complexity:</b> O(N) - Each element pushed/popped once
 * <br><b>Space Complexity:</b> O(N) - Stack + output array
 */
public class NumberOfVisiblePeopleInAQueue {

    /**
     * Counts visible people for each position.
     */
    public int[] canSeePersonsCount(int[] heights) {
        Stack<Integer> stack = new Stack<>(); // store heights of people in decreasing order
        int n = heights.length;
        int[] ans = new int[n];

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop all shorter people → current person sees them directly
            while (!stack.isEmpty() && heights[i] > stack.peek()) {
                ans[i]++;
                stack.pop();
            }

            // If stack is not empty, current person can also see the next taller person
            if (!stack.isEmpty()) {
                ans[i]++;
            }

            // Push current person's height into the stack
            stack.push(heights[i]);
        }

        return ans;
    }
}

