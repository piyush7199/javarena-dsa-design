package org.example.coding.datastructures.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class BSTClosetNode {

    /**
     * 1. Approach 1
     * <p>
     * For each query, we perform a floor and a ceiling search on the BST using binary-like logic.
     * <p>
     * Although the search per query is O(log n) on average, for k queries it becomes O(k * log n) for each of floor and ceil. So total is O(2 * k * log n).
     * <p>
     * Optimization: If floor equals the query, no need to compute the ceiling again.
     * <p>
     * But in the worst case (if tree is skewed), complexity degrades to O(k * h), where h is the height.
     * 2. Approach 2
     * <p>
     * First perform an inorder traversal of the BST to get a sorted list of all node values in O(n).
     * <p>
     * Then, for each query, perform a binary search on this sorted list to find:
     * <p>
     * Floor → greatest element ≤ query
     * <p>
     * Ceil → smallest element ≥ query
     * <p>
     * Per query binary search is O(log n), so total time complexity:
     * O(n + k * log n), where:
     * <p>
     * n = number of nodes in BST
     * <p>
     * k = number of queries
     * Time Complexity:
     * - Inorder traversal: O(n)
     * - For k queries, each binary search: O(log n)
     * - Total: O(n + k * log n)
     * <p>
     * Space Complexity:
     * - O(n) for storing the inorder traversal
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
