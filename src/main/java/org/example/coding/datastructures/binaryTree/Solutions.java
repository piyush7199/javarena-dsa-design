package org.example.coding.datastructures.binaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * A collection of binary tree problems commonly asked in top tech companies.
 */
public class Solutions {

    /**
     * cProblem: Check if two binary trees are identical.
     * <p>
     * Intuition:
     * - Two trees are identical if:
     * - Their roots have the same value
     * - Their left subtrees are identical
     * - Their right subtrees are identical
     * <p>
     * Time Complexity: O(n) — Traverse each node once
     * Space Complexity: O(h) — Call stack for recursion (h = height)
     * <p>
     */
    public boolean isIdentical(Node r1, Node r2) {
        if (r1 == null || r2 == null) return r1 == r2;
        if (r1.val != r2.val) return false;
        boolean left = isIdentical(r1.left, r2.left);
        if (left) {
            return isIdentical(r1.right, r2.right);
        }
        return false;
    }

    /**
     * Problem: Find the maximum depth (height) of a binary tree.
     * <p>
     * Intuition:
     * - The depth of a tree is the longest path from root to leaf.
     * - Recursively compute depth of left and right subtrees and return the maximum.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     * <p>
     */
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int left = 1 + maxDepth(root.left);
        int right = 1 + maxDepth(root.right);
        return Math.max(left, right);
    }

    /**
     * Problem: Zigzag (spiral) level order traversal.
     * <p>
     * Intuition:
     * - Use a deque to support both left-to-right and right-to-left traversal alternately.
     * - Add children in reverse order depending on the current direction.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * <p>
     */
    public List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Deque<Node> q = new LinkedList<>();
        q.addFirst(root);
        boolean reverse = false;

        while (!q.isEmpty()) {
            List<Integer> current = new ArrayList<>();
            int level = q.size();

            for (int i = 0; i < level; i++) {
                if (!reverse) {
                    Node curr = q.pollFirst();
                    current.add(curr.val);
                    if (curr.left != null) q.addLast(curr.left);
                    if (curr.right != null) q.addLast(curr.right);
                } else {
                    Node curr = q.pollLast();
                    current.add(curr.val);
                    if (curr.right != null) q.addFirst(curr.right);
                    if (curr.left != null) q.addFirst(curr.left);
                }
            }

            res.add(current);
            reverse = !reverse;
        }
        return res;
    }

    /**
     * Problem: Validate if a binary tree is a Binary Search Tree (BST).
     * <p>
     * Intuition:
     * - Each node must lie in a valid range: (min, max).
     * - For left child: new max = current node value
     * - For right child: new min = current node value
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     * <p>
     */
    public boolean isValidBST(Node root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(Node root, long lower, long higher) {
        if (root == null) return true;
        if (!(root.val > lower && root.val < higher)) return false;
        boolean left = isValid(root.left, lower, root.val);
        if (left) {
            return isValid(root.right, root.val, higher);
        }
        return false;
    }

    /**
     * Problem: Check if two trees are structurally identical and have the same node values.
     * <p>
     * Intuition:
     * - Similar to isIdentical() function, compare node values and recurse on left and right.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     * <p>
     */
    public boolean isSameTree(Node p, Node q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        boolean left = isSameTree(p.left, q.left);
        if (left) {
            return isSameTree(p.right, q.right);
        }
        return false;
    }

    /**
     * Determines if a binary tree is height-balanced.
     * A binary tree is balanced if the height difference between
     * left and right subtrees of every node is no more than 1.
     * <p>
     * Intuition:
     * - Perform post-order traversal (bottom-up).
     * - At each node, compute left and right heights.
     * - If height difference > 1, propagate `-1` up as a failure flag.
     * <p>
     * Time Complexity: O(n)
     * - Each node is visited once.
     * Space Complexity: O(h)
     * - Due to recursion stack, where h is the height of the tree.
     */
    public boolean isBalanced(Node root) {
        if (root == null) return true;
        return maxDepth2(root) != -1;
    }

    /**
     * Helper function to compute the depth of the binary tree and
     * check for height balance in one traversal.
     * <p>
     * Intuition:
     * - Returns -1 if subtree is unbalanced.
     * - Otherwise, returns the actual depth.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     */
    public int maxDepth2(Node root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        if (left == -1) return -1;
        int right = maxDepth(root.right);
        if (right == -1) return -1;
        if (Math.abs(right - left) > 1) return -1;
        return 1 + Math.max(right, left);
    }

    /**
     * Finds the maximum path sum in a binary tree.
     * A path is any sequence of nodes where each pair is connected via parent-child edge.
     * It may or may not pass through the root, and must contain at least one node.
     * <p>
     * Intuition:
     * - Use post-order traversal to calculate the max sum from left and right subtrees.
     * - For each node, compute the maximum contribution:
     * - `max(root + left, root + right, root)` for recursion return.
     * - `left + root + right` for updating global max.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     */
    public int maxPathSum(Node root) {
        int[] ans = new int[1];
        ans[0] = Integer.MIN_VALUE;
        maxSum(root, ans);
        return ans[0];
    }

    /**
     * Helper function that computes the maximum root-to-leaf path sum and
     * updates the overall max path sum.
     */
    public int maxSum(Node root, int[] sum) {
        if (root == null) return 0;
        int l = Math.max(maxSum(root.left, sum), 0);
        int r = Math.max(maxSum(root.right, sum), 0);
        sum[0] = Math.max(sum[0], l + r + root.val);
        return root.val + Math.max(l, r);
    }

    /**
     * Checks whether a binary tree is symmetric around its center.
     * <p>
     * Intuition:
     * A tree is symmetric if its left and right subtrees are mirror images of each other.
     * We use recursion to check if the left subtree of one node is a mirror of the right subtree of the other node.
     * <p>
     * Time Complexity: O(N) – where N is the number of nodes.
     * Space Complexity: O(H) – for recursion stack, where H is the height of the tree.
     */
    public boolean isSymmetric(Node root) {
        return isSymmetric(root, root);
    }

    private boolean isSymmetric(Node p, Node q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val == q.val) {
            return isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
        }
        return false;
    }

    /**
     * Checks whether the value of each non-leaf node is equal to the sum of its children.
     * <p>
     * Intuition:
     * Perform a DFS traversal.
     * At each node, check if `node.val == left.val + right.val`.
     * Recursively validate for left and right subtrees.
     * <p>
     * Time Complexity: O(N)
     * Space Complexity: O(H) – where H is the height of the tree (stack space)
     */
    public boolean checkTree(Node root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        int sum = 0;
        if (root.left != null) sum += root.left.val;
        if (root.right != null) sum += root.right.val;
        if (sum == root.val) {
            return checkTree(root.left) && checkTree(root.right);
        }
        return false;
    }
}
