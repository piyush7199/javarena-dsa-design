package com.javarena.dsa.datastructures.binaryTree;

/**
 * House Robber III
 *
 * <p><b>Problem Statement:</b><br>
 * Houses are arranged in binary tree. Each house has money. Adjacent houses (parent-child) cannot be robbed 
 * on same night. Find maximum money that can be robbed.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Classic tree DP problem:
 * - For each node, two choices:
 *   1. Rob this node → cannot rob children
 *   2. Skip this node → free to rob children
 * 
 * - Use DFS to compute two values at each node:
 *   - res[0] = max money if node NOT robbed
 *   - res[1] = max money if node IS robbed
 * 
 * - If rob current: value + left[0] + right[0]
 * - If skip current: max(left[0], left[1]) + max(right[0], right[1])
 * - Answer = max(rob root, skip root)
 *
 * <p><b>Time Complexity:</b> O(N) - Visit each node once
 * <br><b>Space Complexity:</b> O(H) - Recursion stack where H = height
 */
public class HouseRobberIII {

    /**
     * Finds maximum money that can be robbed.
     */
    public int rob(Node root) {
        int[] res = dfs(root);
        // Return the better of robbing or not robbing the root
        return Math.max(res[0], res[1]);
    }

    /**
     * DFS helper function that returns an array of two values:
     * res[0] = max money if current node is NOT robbed
     * res[1] = max money if current node IS robbed
     */
    private int[] dfs(Node root) {
        if (root == null) return new int[2]; // Base case: no money from null node

        // Postorder DFS: compute values for left and right children first
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        // If we rob this node, we cannot rob its children
        int include = root.val + left[0] + right[0];

        // If we skip this node, we can take max of robbing or not robbing children
        int exclude = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{exclude, include};
    }
}
