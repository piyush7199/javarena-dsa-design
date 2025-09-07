package org.example.coding.datastructures.binaryTree;

public class InvertTree {
    public void invertTree(Node root) {
        if (root == null) return;
        Node node = root.left;
        root.left = root.right;
        root.right = node;
        invertTree(root.left);
        invertTree(root.right);
    }
}
