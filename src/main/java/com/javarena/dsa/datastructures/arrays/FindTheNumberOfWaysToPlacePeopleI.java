package com.javarena.dsa.datastructures.arrays;

import java.util.Arrays;

/**
 * Find the Number of Ways to Place People I
 *
 * <p><b>Problem Statement:</b><br>
 * Count valid pairs of points (A, B) where:
 * 1. A is upper-left of B (Ax < Bx and Ay > By)
 * 2. Rectangle formed by A and B is empty (no other points inside or on boundary)
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Valid pair requires A strictly above and left of B
 * - Empty rectangle means no other point "blocks" the space
 * - Sort points by x ascending, y descending (for ties)
 * - For each point A, maintain vertical boundaries while scanning:
 *   - top = y0 (A's y-coordinate, upper bound)
 *   - bot = -∞ (lowest y seen so far)
 * - For each candidate B after A:
 *   - If y1 is in range (bot < y1 <= top), it's valid
 *   - Update bot = y1 to prevent overlaps
 *   - If y1 == top, decrement top to avoid duplicates
 * - This ensures rectangle emptiness by tracking boundaries
 *
 * <p><b>Time Complexity:</b> O(N²) - Nested loops after O(N log N) sort
 * <br><b>Space Complexity:</b> O(1) - Only counters and variables
 */
public class FindTheNumberOfWaysToPlacePeopleI {

    /**
     * Counts valid pairs with empty rectangles.
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

