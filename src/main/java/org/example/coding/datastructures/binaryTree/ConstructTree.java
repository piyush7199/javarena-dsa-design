package org.example.coding.datastructures.binaryTree;

import java.util.HashMap;
import java.util.Map;

public class ConstructTree {

    /**
     * Reconstructs a binary tree from preorder and inorder traversal.
     * <p>
     * Intuition:
     * - Preorder gives root first; find its index in inorder to separate left and right subtrees.
     * - Recursively build left and right subtrees.
     * <p>
     * <p>
     * Time Complexity: O(n) — each node is visited once, and hashmap lookup is O(1).
     * Space Complexity: O(n) — recursion stack + hashmap.
     */
    public Node buildTreeFromPreOrderAndInorder(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return build(preorder, 0, preorder.length - 1, 0, inorder.length - 1, inMap);
    }


    private Node build(int preorder[], int preStart, int preEnd, int inStart, int inEnd, Map<Integer, Integer> map) {

        if (preStart > preEnd || inStart > inEnd) return null;
        Node node = new Node(preorder[preStart]);
        int inRoot = map.get(preorder[preStart]);
        int left = inRoot - inStart;
        node.left = build(preorder, preStart + 1, preStart + left, inStart, inRoot - 1, map);
        node.right = build(preorder, preStart + 1 + left, preEnd, inRoot + 1, inEnd, map);
        return node;
    }

    /**
     * Reconstructs a binary tree from inorder and postorder traversal.
     * <p>
     * Intuition:
     * - Postorder gives the root at the end.
     * - Use the inorder index to divide left and right subtrees.
     * <p>
     * <p>
     * Time Complexity: O(n) — each node is processed once with O(1) map lookup.
     * Space Complexity: O(n) — recursion stack and map.
     */
    public Node buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildPostOrderTree(0, inorder.length - 1, postorder, 0, postorder.length - 1, map);

    }

    private Node buildPostOrderTree(int inStart, int inEnd, int[] postorder, int postStart, int postEnd, HashMap<Integer, Integer> map) {
        if (inStart > inEnd || postStart > postEnd) return null;

        Node root = new Node(postorder[postEnd]);

        int inNode = map.get(postorder[postEnd]);
        int leftSize = inNode - inStart - 1;

        root.left = buildPostOrderTree(inStart, inNode - 1, postorder, postStart, postStart + leftSize, map);
        root.right = buildPostOrderTree(inNode + 1, inEnd, postorder, postStart + leftSize + 1, postEnd - 1, map);
        return root;
    }
}
