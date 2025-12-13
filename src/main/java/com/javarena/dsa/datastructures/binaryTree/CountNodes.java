package com.javarena.dsa.datastructures.binaryTree;

/**
 * Count Nodes in Binary Tree
 *
 * <p><b>Problem Statement:</b><br>
 * Count the total number of nodes in a binary tree. Optimize for complete binary trees.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Standard approach: DFS recursion counting 1 + left + right
 * 
 * Optimized for Complete Binary Tree:
 * - Check if left height == right height
 * - If equal: perfect binary tree, count = 2^h - 1
 * - If not equal: recursively count left and right
 * - For complete tree: O(log² N) vs O(N) for standard
 * - Height computation takes O(log N), done at each level
 *
 * <p><b>Time Complexity:</b> O(N) standard, O(log² N) for complete tree
 * <br><b>Space Complexity:</b> O(H) recursion stack where H = height
 */
public class CountNodes {

    /**
     * Standard DFS node counting.
     */
    public int countNodes1(Node root) {
        if (root == null) {
            return 0;
        }

        return 1 + countNodes1(root.left) + countNodes1(root.right);
    }

    /**
     * Optimized counting for complete binary tree.
     */
    public int countNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int lh = findHeightLeft(root);
        int rh = findHeightRight(root);
        if (lh == rh) {
            return (1 << lh) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private int findHeightLeft(Node node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }

    private int findHeightRight(Node node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.right;
        }
        return height;
    }
}
