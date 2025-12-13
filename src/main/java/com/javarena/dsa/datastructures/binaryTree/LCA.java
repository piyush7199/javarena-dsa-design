package com.javarena.dsa.datastructures.binaryTree;

/**
 * Lowest Common Ancestor (LCA)
 *
 * <p><b>Problem Statement:</b><br>
 * Find the lowest common ancestor of two nodes p and q in a binary tree.
 * LCA is the deepest node that is an ancestor of both p and q.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use DFS to search for both nodes
 * - If current node is p or q, return it
 * - Recursively search left and right subtrees
 * - If both left and right return non-null: current node is LCA
 * - If only one side returns non-null: propagate that result up
 * - Base case: null node returns null
 * - Key insight: LCA is where paths to p and q diverge
 *
 * <p><b>Time Complexity:</b> O(N) - Visit each node once
 * <br><b>Space Complexity:</b> O(H) - Recursion stack where H = height
 */
public class LCA {
    /**
     * Finds lowest common ancestor of two nodes.
     */
    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        else if (right == null) return left;
        return root;
    }
}
