package com.javarena.dsa.datastructures.binaryTree;

/**
 * Search in Binary Search Tree
 *
 * <p><b>Problem Statement:</b><br>
 * Given a BST and target value, find and return the node with that value. Return null if not found.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Leverage BST property:
 * - All nodes < root are in left subtree
 * - All nodes > root are in right subtree
 * 
 * Algorithm:
 * - If root.val == target: found, return root
 * - If root.val > target: search left subtree
 * - If root.val < target: search right subtree
 * - If null reached: not found
 * 
 * Two implementations:
 * - Iterative: Uses while loop, O(1) space
 * - Recursive: Cleaner code, O(H) stack space
 *
 * <p><b>Time Complexity:</b> O(log N) average, O(H) worst case where H = height
 * <br><b>Space Complexity:</b> O(1) iterative, O(H) recursive stack
 */
public class BSTSearch {
    /**
     * Iterative search in BST.
     */
    public Node searchBST(Node root, int val) {
        if (root == null) {
            return null;
        }

        while (root != null) {
            int value = root.val;
            if (value == val) {
                return root;
            }
            if (value > val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return null;
    }

    /**
     * Recursive search in BST.
     */
    public Node searchBSTRecursive(Node root, int val) {
        if (root == null) {
            return null;
        }
        int value = root.val;
        if (value == val) return root;
        if (value > val) return searchBST(root.left, val);
        return searchBST(root.right, val);
    }
}
