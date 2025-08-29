package org.example.coding.datastructures.binaryTree;

public class HouseRobberIII {

    /**
     * You are a thief planning to rob houses along a binary tree structure.
     * Each house has a certain amount of money. Adjacent houses (parent-child)
     * cannot be robbed on the same night.
     * <p>
     * -------------------------
     * Intuition:
     * - For each node (house), we have two choices:
     * 1. Rob this node → then we cannot rob its children.
     * 2. Skip this node → then we are free to rob its children.
     * <p>
     * - This is a classic tree DP problem solved using DFS.
     * - At each node, we compute two values:
     * [0] = maximum money if this node is NOT robbed.
     * [1] = maximum money if this node IS robbed.
     * <p>
     * Approach:
     * 1. Perform DFS from the root.
     * 2. For each node:
     * - If we rob it: include its value + "not robbed" values of left and right children.
     * - If we skip it: take max of robbed or not robbed from both children.
     * 3. The answer is the maximum of robbing or not robbing the root.
     * <p>
     * Time Complexity:  O(n)
     * - Each node is visited once.
     * <p>
     * Space Complexity: O(h)
     * - h = height of tree (recursion stack).
     * - In worst case (skewed tree), O(n).
     * - In balanced tree, O(log n).
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
