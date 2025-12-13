package com.javarena.dsa.datastructures.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Even-Odd Tree
 *
 * <p><b>Problem Statement:</b><br>
 * A binary tree is Even-Odd if:
 * - Even-indexed levels (0, 2, 4...): all odd values in strictly increasing order
 * - Odd-indexed levels (1, 3, 5...): all even values in strictly decreasing order
 *
 * <p><b>Intuition & Approach:</b><br>
 * Use level-order traversal (BFS) with level tracking:
 * - Track current level (even/odd) with boolean flag
 * - For even levels:
 *   - All values must be odd
 *   - Must be strictly increasing (current > prev)
 *   - Initialize prev = MIN_VALUE
 * - For odd levels:
 *   - All values must be even
 *   - Must be strictly decreasing (current < prev)
 *   - Initialize prev = MAX_VALUE
 * - Return false if any condition violated
 * - Toggle even flag after each level
 *
 * <p><b>Time Complexity:</b> O(N) - Visit all nodes once
 * <br><b>Space Complexity:</b> O(W) - Queue stores width of tree
 */
public class EvenOddTree {
    /**
     * Checks if tree satisfies Even-Odd property.
     */
    public boolean isEvenOddTree(Node root) {
        Queue<Node> queue = new LinkedList<>();
        Node current = root;
        queue.add(current);

        boolean even = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int prev = Integer.MAX_VALUE;
            if (even) {
                prev = Integer.MIN_VALUE;
            }

            while (size > 0) {
                current = queue.poll();

                // Check conditions based on level
                if ((even && (current.val % 2 == 0 || current.val <= prev)) ||
                        (!even && (current.val % 2 == 1 || current.val >= prev))) {
                    return false;
                }

                prev = current.val;
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
                size--;
            }
            even = !even;
        }
        return true;
    }
}
