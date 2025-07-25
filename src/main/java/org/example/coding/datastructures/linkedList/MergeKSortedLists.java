package org.example.coding.datastructures.linkedList;

public class MergeKSortedLists {
    /**
     * Merges an array of k sorted linked lists into one sorted linked list.
     * <p>
     * This approach uses a divide-and-conquer strategy similar to merge sort.
     * It recursively divides the input array of lists into halves and merges them.
     * <p>
     * Time Complexity: O(N log k), where N is the total number of nodes and k is the number of lists.
     * Space Complexity: O(log k) due to recursion stack. (If recursion is optimized to be tail-recursive, it could be close to O(1)).
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
