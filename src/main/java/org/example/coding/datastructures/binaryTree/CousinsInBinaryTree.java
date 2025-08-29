package org.example.coding.datastructures.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class CousinsInBinaryTree {
    public boolean isCousins(Node root, int x, int y) {
        if (root == null) return false;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean foundX = false;
            boolean foundY = false;

            for (int i = 0; i < size; i++) { // FIXED i++
                Node node = queue.poll();

                // check if x or y found at this level
                if (node.val == x) foundX = true;
                if (node.val == y) foundY = true;

                // ❗ check if they are siblings
                if (node.left != null && node.right != null) {
                    if ((node.left.val == x && node.right.val == y) ||
                            (node.left.val == y && node.right.val == x)) {
                        return false; // same parent → not cousins
                    }
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            // If both found at same level → they are cousins
            if (foundX && foundY) return true;

            // If only one found at this level → not cousins
            if (foundX || foundY) return false;
        }

        return false;
    }
}
