package com.javarena.dsa.datastructures.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Cousins in Binary Tree
 *
 * <p><b>Problem Statement:</b><br>
 * Two nodes are cousins if they are at the same depth but have different parents.
 * Given values x and y, determine if they are cousins.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Use level-order traversal (BFS):
 * - Process tree level by level
 * - For each level, track if x and y are found
 * - Check if they are siblings (same parent): return false
 * - If both found at same level and not siblings: return true
 * - If only one found at level: return false (different depths)
 * - Continue until both found or tree exhausted
 *
 * <p><b>Time Complexity:</b> O(N) - Visit all nodes in worst case
 * <br><b>Space Complexity:</b> O(W) - Queue stores width of tree (max nodes at any level)
 */
public class CousinsInBinaryTree {
    /**
     * Checks if two nodes are cousins.
     */
    public boolean isCousins(Node root, int x, int y) {
        if (root == null) return false;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean foundX = false;
            boolean foundY = false;

            for (int i = 0; i < size; i++) {
                Node node = queue.poll();

                // Check if x or y found at this level
                if (node.val == x) foundX = true;
                if (node.val == y) foundY = true;

                // Check if they are siblings (same parent)
                if (node.left != null && node.right != null) {
                    if ((node.left.val == x && node.right.val == y) ||
                            (node.left.val == y && node.right.val == x)) {
                        return false; // Same parent → not cousins
                    }
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            // If both found at same level → they are cousins
            if (foundX && foundY) return true;

            // If only one found at this level → not cousins
            if (foundX || foundY) return false;
        }

        return false;
    }
}
