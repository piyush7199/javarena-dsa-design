package org.example.coding.datastructures.segmentTree;

public class FruitsIntoBasketsIII {
    static class Node {
        int val;
        Node left = null;
        Node right = null;
    }

    public int numOfUnplacedFruits(int[] fruits, int[] basket) {
        Node root = maxSegmentTree(basket, 0, basket.length - 1);
        int placed = 0;

        for (int fruit : fruits) {
            if (root.val < fruit)
                continue;
            place(fruit, root);
            placed++;
        }

        return fruits.length - placed;
    }

    public Node place(int fruit, Node node) {
        if (node.left == null && node.right == null) {
            node.val = -1;
            return node;
        }

        assert node.left != null;
        if (node.left.val >= fruit)
            node.left = place(fruit, node.left);
        else
            node.right = place(fruit, node.right);

        node.val = Math.max(node.left.val, node.right.val);

        return node;
    }

    public Node maxSegmentTree(int[] basket, int start, int end) {
        Node currentNode = new Node();

        if (start == end) {
            currentNode.val = basket[start];
            return currentNode;
        }

        int mid = start + (end - start) / 2;
        currentNode.left = maxSegmentTree(basket, start, mid);
        currentNode.right = maxSegmentTree(basket, mid + 1, end);
        currentNode.val = Math.max(currentNode.left.val, currentNode.right.val);

        return currentNode;
    }
}
