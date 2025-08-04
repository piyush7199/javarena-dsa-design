package org.example.coding.datastructures.binaryTree;

public class DiameterOfBinaryTree {
    public int diameterOfBinaryTree(Node root) {
        int[] dia = new int[1];
        height(root, dia);
        return dia[0];
    }

    public int height(Node root, int[] dia) {
        if (root == null) return 0;
        int lh = height(root.left, dia);
        int rh = height(root.right, dia);
        dia[0] = Math.max(dia[0], lh + rh);
        return 1 + Math.max(lh, rh);
    }
}
