package com.javarena.dsa.datastructures.binaryTree;

/**
 * Subtree of Another Tree
 *
 * <p><b>Problem Statement:</b><br>
 * Given two binary trees root and subRoot, check if subRoot is a subtree of root.
 * A subtree means a node and all its descendants match exactly.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Check if trees are identical starting from root
 * - If not, recursively check if subRoot is subtree of root.left or root.right
 * - Use helper function isSameTree to check exact match
 * - For each node in root, check if subtree starting there matches subRoot
 * - Continue until match found or all nodes checked
 *
 * <p><b>Time Complexity:</b> O(M × N) where M = nodes in root, N = nodes in subRoot
 * <br><b>Space Complexity:</b> O(H) - Recursion stack where H = height of root
 */
public class SubTree {
    /**
     * Checks if subRoot is a subtree of root.
     */
    public boolean isSubtree(Node root, Node subRoot) {
        boolean main = isSameTree(root, subRoot);
        if(main) return true;

        if(root != null) {
            boolean left = isSubtree(root.left, subRoot);
            if(left) return true;
            return isSubtree(root.right, subRoot);
        }
        return false;
    }

    /**
     * Helper to check if two trees are identical.
     */
    public boolean isSameTree(Node p, Node q) {
        if(p == null && q == null) return true;
        if(p == null) return false;
        if(q == null) return false;
        if(p.val != q.val) return false;

        boolean left = isSameTree(p.left, q.left);
        if(left) return isSameTree(p.right, q.right);
        return false;
    }
}
