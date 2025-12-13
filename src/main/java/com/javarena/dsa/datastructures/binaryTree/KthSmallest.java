package com.javarena.dsa.datastructures.binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Kth Smallest Element in BST
 *
 * <p><b>Problem Statement:</b><br>
 * Given a Binary Search Tree, find the kth smallest element (1-indexed).
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Inorder traversal of BST gives sorted order
 * - Perform inorder traversal and store values in list
 * - Return element at index k-1 (0-indexed)
 * 
 * Optimization: Stop after finding kth element (not implemented here)
 * - Use counter during inorder
 * - Return when counter reaches k
 * - Saves space and time
 *
 * <p><b>Time Complexity:</b> O(N) - Inorder traversal visits all nodes
 * <br><b>Space Complexity:</b> O(N) - List stores all values
 */
public class KthSmallest {
    /**
     * Finds kth smallest element in BST.
     */
    public int kthSmallest(Node root, int k) {
        List<Integer> arr = new ArrayList<>();
        inorder(root, arr);
        return arr.get(k - 1);
    }

    /**
     * Helper for inorder traversal.
     */
    private void inorder(Node root, List<Integer> arr) {
        if (root == null) return;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
    }
}
