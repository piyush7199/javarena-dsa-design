package com.javarena.dsa.datastructures.binaryTree;

/**
 * Invert Binary Tree
 *
 * <p><b>Problem Statement:</b><br>
 * Invert a binary tree by swapping left and right children of all nodes (mirror the tree).
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use DFS (preorder) to recursively swap children
 * - At each node:
 *   1. Swap left and right children
 *   2. Recursively invert left subtree
 *   3. Recursively invert right subtree
 * - Base case: null node, return immediately
 * - Simple and elegant recursive solution
 *
 * <p><b>Time Complexity:</b> O(N) - Visit each node once
 * <br><b>Space Complexity:</b> O(H) - Recursion stack where H = height
 */
public class InvertTree {
    /**
     * Inverts binary tree by swapping all left and right children.
     */
    public void invertTree(Node root) {
        if (root == null) return;
        
        // Swap left and right
        Node node = root.left;
        root.left = root.right;
        root.right = node;
        
        // Recursively invert subtrees
        invertTree(root.left);
        invertTree(root.right);
    }
}
