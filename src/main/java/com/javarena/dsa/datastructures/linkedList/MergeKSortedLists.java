package com.javarena.dsa.datastructures.linkedList;

/**
 * Merge K Sorted Linked Lists
 *
 * <p><b>Problem Statement:</b><br>
 * Merge k sorted linked lists into one sorted linked list.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Divide and conquer (similar to merge sort):
 * - Recursively divide array of lists into halves
 * - Merge pairs of lists bottom-up
 * - Continue until single merged list remains
 * 
 * Alternative approaches:
 * 1. Brute force: Merge lists one by one - O(N×K)
 * 2. Min-heap: Keep K pointers, extract min - O(N log K)
 * 3. Divide & Conquer: Current approach - O(N log K)
 * 
 * Divide & conquer reduces number of comparisons.
 *
 * <p><b>Time Complexity:</b> O(N log K) - N total nodes, K lists
 * <br><b>Space Complexity:</b> O(log K) - Recursion stack
 */
public class MergeKSortedLists {
    /**
     * Merges K sorted lists using divide and conquer.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        return merge(lists, 0, n - 1);
    }

    private ListNode merge(ListNode[] lists, int i, int j) {
        if (i == j) {
            return lists[j];
        }
        if (i < j) {
            int mid = i + (j - i) / 2;
            ListNode left = merge(lists, i, mid);
            ListNode right = merge(lists, mid + 1, j);
            return mergeNodes(left, right);
        }
        return null;
    }

    private ListNode mergeNodes(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if (left != null) {
            cur.next = left;
        }

        if (right != null) {
            cur.next = right;
        }

        return dummy.next;
    }
}
