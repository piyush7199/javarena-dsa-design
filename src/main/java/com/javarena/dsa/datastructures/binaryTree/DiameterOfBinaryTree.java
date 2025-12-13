package com.javarena.dsa.datastructures.binaryTree;

/**
 * Diameter of Binary Tree
 *
 * <p><b>Problem Statement:</b><br>
 * Find the diameter of a binary tree - the length of the longest path between any two nodes.
 * The path may or may not pass through the root.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Diameter at any node = left height + right height
 * - Need to check diameter at every node
 * - Use DFS to calculate height while tracking max diameter
 * - For each node:
 *   - Calculate left subtree height
 *   - Calculate right subtree height
 *   - Update max diameter = max(current diameter, lh + rh)
 *   - Return height = 1 + max(lh, rh) to parent
 * - Use array to pass diameter by reference
 *
 * <p><b>Time Complexity:</b> O(N) - Visit each node once
 * <br><b>Space Complexity:</b> O(H) - Recursion stack where H = height
 */
public class DiameterOfBinaryTree {
    /**
     * Calculates diameter of binary tree.
     */
    public int diameterOfBinaryTree(Node root) {
        int[] dia = new int[1];
        height(root, dia);
        return dia[0];
    }

    /**
     * Helper to calculate height while tracking diameter.
     */
    public int height(Node root, int[] dia) {
        if (root == null) return 0;
        
        int lh = height(root.left, dia);
        int rh = height(root.right, dia);
        
        // Update diameter at this node
        dia[0] = Math.max(dia[0], lh + rh);
        
        // Return height to parent
        return 1 + Math.max(lh, rh);
    }
}
