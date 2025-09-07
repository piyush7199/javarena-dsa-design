package org.example.coding.datastructures.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class KthSmallest {
    public int kthSmallest(Node root, int k) {
        List<Integer> arr = new ArrayList<>();
        inorder(root, arr);
        return arr.get(k - 1);
    }

    private void inorder(Node root, List<Integer> arr) {
        if (root == null) return;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
    }
}
