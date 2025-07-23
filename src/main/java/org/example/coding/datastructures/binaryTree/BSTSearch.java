package org.example.coding.datastructures.binaryTree;

public class BSTSearch {
    /**
     * As we know what in binary search tree has a  property that
     * All nodes less than its root are on left and all node whose value is
     * greater than root is on right
     * So we will use this property and traverse the substree in which
     * We might that target value
     * if the root value is equal then we have found the node and will return that;
     * Else we check if the root value is greater it means the and is in left
     * else in right
     * Time Complexity - log(n)
     * Space Complexity - O(h) - h is the height of the tree (In recursion stack)
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
