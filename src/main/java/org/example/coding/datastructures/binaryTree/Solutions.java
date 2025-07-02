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
     * 📌 Asked in: Amazon, Google, Microsoft, Adobe
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
     * 📌 Asked in: Amazon, Google, Facebook, Uber
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
     * 📌 Asked in: Amazon, Apple, Bloomberg
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
     * 📌 Asked in: Google, Amazon, Facebook, Microsoft
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
     * 📌 Asked in: Google, Amazon, Adobe
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
}
