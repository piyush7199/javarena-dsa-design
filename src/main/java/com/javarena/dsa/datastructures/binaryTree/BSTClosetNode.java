package com.javarena.dsa.datastructures.binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Closest Nodes in BST
 *
 * <p><b>Problem Statement:</b><br>
 * For each query value, find the closest floor (largest ≤ query) and ceiling (smallest ≥ query) in BST.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Two approaches:
 * 
 * Approach 1 - Direct BST Search: O(K × log N)
 * - For each query, traverse BST to find floor and ceiling
 * - Use BST property to navigate left/right
 * - Degrades to O(K × H) for skewed trees
 * 
 * Approach 2 - Inorder + Binary Search: O(N + K log N) (Implemented)
 * - Perform inorder traversal to get sorted list O(N)
 * - For each query, binary search on sorted list O(log N)
 * - Find floor: greatest element ≤ query (right pointer after search)
 * - Find ceiling: smallest element ≥ query (left pointer after search)
 * - Better for multiple queries
 *
 * <p><b>Time Complexity:</b> O(N + K log N) where N = nodes, K = queries
 * <br><b>Space Complexity:</b> O(N) for inorder list
 */
public class BSTClosetNode {

    /**
     * Finds closest floor and ceiling for each query.
     */
    public List<List<Integer>> closestNodes(Node root, List<Integer> queries) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> listinorder = new ArrayList<>();
        inorder(root, listinorder);
        for (int target : queries) {
            List<Integer> cur = binarysearch(target, listinorder);
            res.add(cur);
        }
        return res;
    }

    private void inorder(Node node, List<Integer> listinorder) {
        if (node == null) {
            return;
        }
        inorder(node.left, listinorder);
        listinorder.add(node.val);
        inorder(node.right, listinorder);
    }

    private List<Integer> binarysearch(int target, List<Integer> list) {
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) == target) {
                return List.of(target, target);
            } else if (list.get(mid) > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        int smallres = right == -1 ? -1 : list.get(right);
        int largeres = left == list.size() ? -1 : list.get(left);
        return List.of(smallres, largeres);
    }
}
