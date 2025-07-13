package org.example.coding.datastructures.binaryTree;

public class CountNodes {

    /**
     * Counts the number of nodes in a binary tree using standard DFS traversal.
     * <p>
     * Time Complexity: O(n) — visits every node.
     * Space Complexity: O(h) — recursion stack, h = height of tree.
     */
    public int countNodes1(Node root) {
        if (root == null) {
            return 0;
        }

        return 1 + countNodes1(root.left) + countNodes1(root.right);
    }

    /**
     * Optimized node counting for a complete binary tree using tree height.
     * <p>
     * Intuition:
     * - In a perfect binary tree (left and right heights equal), node count = 2^h - 1.
     * - Otherwise, recursively count left and right subtrees.
     * <p>
     * Time Complexity: O(log² n) — height computation O(log n), done at each level.
     * Space Complexity: O(log n) — due to recursion stack.
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
