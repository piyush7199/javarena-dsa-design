package org.example.coding.datastructures.arrays;

import java.util.Arrays;

public class FindTheNumberOfWaysToPlacePeopleI {


    /**
     * Counts the number of valid pairs of points (A, B) such that:
     * <p>
     * 1. A lies on the upper-left side of B:
     * - Ax < Bx
     * - Ay > By
     * <p>
     * 2. The axis-aligned rectangle (or line) formed by A and B is empty:
     * - No other point exists inside or on the boundary of this rectangle.
     * <p>
     * -------------------
     * Intuition:
     * -------------------
     * - A valid pair requires that A is strictly above and to the left of B.
     * - The emptiness condition means no other point can "block" the rectangle.
     * - If we sort the points by x ascending (and y descending for ties),
     * then when we iterate from left to right, all possible candidates for B
     * naturally appear after A.
     * - We can then maintain two vertical boundaries (top and bottom) while scanning:
     * - `top = y0` → A’s y-coordinate (upper bound).
     * - `bot = -∞` → lowest possible y seen so far.
     * - For each new candidate B, if its y lies strictly between (bot, top],
     * then (A, B) forms a valid rectangle with no other interfering points.
     * - After counting it, update `bot = y1` (to prevent overlaps),
     * and adjust `top` if needed (to avoid duplicates at the same y).
     * <p>
     * -------------------
     * Approach:
     * -------------------
     * 1. Sort points:
     * - By x ascending.
     * - If x is the same, by y descending.
     * This ensures consistent left-to-right iteration.
     * <p>
     * 2. For each point A = (x0, y0):
     * - Initialize `bot = -∞`, `top = y0`.
     * - Iterate through all subsequent points B = (x1, y1).
     * - If (y1 <= top && y1 > bot), then count it as a valid pair.
     * - Update boundaries:
     * * `bot = y1`
     * * If y1 == top, then decrement top to avoid duplicate counting.
     * <p>
     * 3. Return the total count.
     * <p>
     * -------------------
     * Complexity Analysis:
     * -------------------
     * - Sorting: O(n log n), where n = number of points.
     * - Nested loops: O(n^2) in the worst case (each point compared with every later point).
     * <p>
     * Total Time: O(n^2).
     * Space Complexity: O(1) (in-place sorting, only counters and temporary vars used).
     */
    public int numberOfPairs(int[][] points) {
        // Step 1: Sort points by x ascending, y descending
        Arrays.sort(points, (a, b) -> a[0] == b[0] ?
                b[1] - a[1] : a[0] - b[0]);

        int count = 0; // total valid pairs

        // Step 2: Treat each point as "A"
        for (int i = 0; i < points.length; i++) {
            int x0 = points[i][0], y0 = points[i][1];

            // Boundaries for valid rectangles
            int bot = Integer.MIN_VALUE; // lowest valid y
            int top = y0;                // upper bound from A

            // Step 3: Look for possible "B" to the right
            for (int j = i + 1; j < points.length; j++) {
                int x1 = points[j][0], y1 = points[j][1];

                // Check if B is within the valid vertical range
                if (y1 <= top && y1 > bot) {
                    count++;   // Valid pair found
                    bot = y1;  // Update bottom boundary

                    // Prevent duplicate counting if B sits exactly at top
                    if (y1 == top) top--;
                }
            }
        }

        return count;
    }
}

