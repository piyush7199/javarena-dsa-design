package com.javarena.dsa.datastructures.binaryTree;

import java.util.HashMap;
import java.util.Map;

/**
 * Construct Binary Tree from Traversals
 *
 * <p><b>Problem Statement:</b><br>
 * Reconstruct binary tree from:
 * 1. Preorder and Inorder traversals
 * 2. Inorder and Postorder traversals
 *
 * <p><b>Intuition & Approach:</b><br>
 * From Preorder + Inorder:
 * - Preorder gives root first
 * - Find root in inorder to separate left/right subtrees
 * - Recursively build left (preStart+1 to preStart+leftSize)
 * - Recursively build right (preStart+leftSize+1 to preEnd)
 * 
 * From Postorder + Inorder:
 * - Postorder gives root last
 * - Find root in inorder to separate left/right subtrees
 * - Build right first, then left (postorder processes right before left)
 * 
 * Use HashMap for O(1) inorder index lookup
 *
 * <p><b>Time Complexity:</b> O(N) - Each node visited once with O(1) lookup
 * <br><b>Space Complexity:</b> O(N) - Recursion stack + HashMap
 */
public class ConstructTree {

    /**
     * Builds tree from preorder and inorder traversals.
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
     * Builds tree from inorder and postorder traversals.
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
