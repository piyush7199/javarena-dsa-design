package com.javarena.dsa.datastructures.segmentTree;

/**
 * Fruits Into Baskets III
 *
 * <p><b>Problem Statement:</b><br>
 * Given fruits array and baskets array, place fruits into baskets where basket[i] = max capacity.
 * A fruit can only be placed if basket has sufficient capacity. Return number of unplaced fruits.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Use max segment tree to efficiently find basket with sufficient capacity:
 * - Build max segment tree from basket capacities
 * - For each fruit:
 *   - Check if any basket has capacity >= fruit size (root value)
 *   - If yes, find and use leftmost suitable basket
 *   - Mark basket as used (set to -1)
 *   - Update tree upward
 * - Count fruits that couldn't be placed
 * 
 * Segment tree allows O(log N) placement per fruit.
 *
 * <p><b>Time Complexity:</b> O(N log M) - N fruits, M baskets, log M per placement
 * <br><b>Space Complexity:</b> O(M) - Segment tree nodes
 */
public class FruitsIntoBasketsIII {
    /**
     * Segment tree node storing maximum capacity.
     */
    static class Node {
        int val;
        Node left = null;
        Node right = null;
    }

    /**
     * Counts unplaced fruits.
     */
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

    /**
     * Places fruit in suitable basket, updates tree.
     */
    public Node place(int fruit, Node node) {
        if (node.left == null && node.right == null) {
            node.val = -1; // Mark basket as used
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

    /**
     * Builds max segment tree from basket capacities.
     */
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
