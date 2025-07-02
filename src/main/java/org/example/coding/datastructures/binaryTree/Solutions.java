package org.example.coding.datastructures.binaryTree;

public class Solutions {
    public boolean isIdentical(Node r1, Node r2) {
        if (r1 == null || r2 == null) return r1 == r2;
        if (r1.val != r2.val) return false;
        boolean left = isIdentical(r1.left, r2.left);
        if (left) {
            return isIdentical(r1.right, r2.right);
        }
        return false;
    }

    public int maxDepth(Node root) {
        if (root == null) return 0;
        int left = 1 + maxDepth(root.left);
        int right = 1 + maxDepth(root.right);
        return Math.max(left, right);
    }
}
