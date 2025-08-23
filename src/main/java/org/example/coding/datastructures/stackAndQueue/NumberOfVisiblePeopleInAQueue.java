package org.example.coding.datastructures.stackAndQueue;

import java.util.Stack;

public class NumberOfVisiblePeopleInAQueue {

    /**
     * Problem: "Number of Visible People in a Queue" (LeetCode 1944)
     * <p>
     * Given an array heights[] where heights[i] is the height of the i-th person standing in a queue,
     * we need to determine for each person, how many people to their right they can "see".
     * <p>
     * A person i can see person j (where j > i) if:
     * - Every person in between i and j is shorter than person j, and
     * - Person i is taller than all people before person j.
     * <p>
     * Intuition:
     * - If we scan from right to left and maintain a "monotonic decreasing stack" of heights,
     * we can efficiently compute how many people each person can see.
     * - While a person at i is taller than the stack top, they can see them and "pop" them.
     * - After popping, if stack is not empty, then they can see the next taller person as well.
     * - This way, each person directly counts how many people they see without re-checking.
     * <p>
     * Approach (Monotonic Stack):
     * 1. Initialize a stack to keep track of people’s heights (in decreasing order).
     * 2. Traverse from right → left (because visibility is only to the right).
     * 3. While current person is taller than stack top:
     * - They see the person (increment count).
     * - Pop from stack (since shorter ones are blocked).
     * 4. After popping, if stack is not empty:
     * - Current person can also see the next taller person (increment count).
     * 5. Push current person into the stack.
     * 6. Return the result array.
     * <p>
     * Time Complexity: O(n)
     * - Each element is pushed and popped at most once.
     * <p>
     * Space Complexity: O(n)
     * - For the stack + output array.
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

