package org.example.coding.datastructures.binaryTree;

public class LCA {
    /**
     * Find the lowest common ancestor (LCA) of two nodes in a binary tree.
     * <p>
     * Intuition:
     * - Recursively traverse left and right.
     * - If one node is found in each subtree, current node is LCA.
     * - If one is null, propagate the non-null result up.
     * <p>
     * <p>
     * Time Complexity: O(n) — visits each node once
     * Space Complexity: O(h) — recursion stack, where h = tree height
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
