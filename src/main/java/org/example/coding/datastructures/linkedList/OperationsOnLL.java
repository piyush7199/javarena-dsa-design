package org.example.coding.datastructures.linkedList;

public class OperationsOnLL {

    /**
     * Delete Middle Node of a Linked List
     * <p>
     * Intuition:
     * Use slow and fast pointers to find the middle. Then remove the middle node by linking its previous node to the next node.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode fast = head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
    }

    /**
     * Sort Linked List using Merge Sort
     * <p>
     * Intuition:
     * Recursively split the list into halves using fast/slow pointer approach. Then merge sorted halves.
     * <p>
     * Time Complexity: O(n log n)
     * Space Complexity: O(log n) due to recursion stack
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        return merge(l1, l2);
    }


    /**
     * Merge Two Sorted Linked Lists (Helper for sortList)
     * <p>
     * Intuition:
     * Standard merging of two sorted lists into one.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if (l1 != null) {
            cur.next = l1;
        }

        if (l2 != null) {
            cur.next = l2;
        }

        return dummy.next;
    }

    /**
     * Remove N-th Node from End of List
     * <p>
     * Intuition:
     * Use two pointers. Move the first n+1 steps ahead, then move both pointers together
     * so the second pointer lands just before the node to delete.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);

        dummy.next = head;

        ListNode first = dummy;
        for (int i = 0; i <= n; i++) {
            if (first != null) {
                first = first.next;
            } else break;
        }

        ListNode res = dummy;

        while (first != null) {
            res = res.next;
            first = first.next;
        }

        res.next = res.next.next;

        return dummy.next;
    }

    /**
     * Rearrange Linked List into Odd-Even Groups
     * <p>
     * Intuition:
     * Traverse and collect odd and even indexed nodes in separate lists,
     * then combine them at the end.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode oddEvenList(ListNode head) {
        ListNode cur = head;
        ListNode odd = new ListNode(-1);
        ListNode even = new ListNode(-1);
        ListNode oddT = odd;
        ListNode evenT = even;
        boolean flag = false;
        while (cur != null) {
            if (flag) {
                evenT.next = cur;
                evenT = evenT.next;
            } else {
                oddT.next = cur;
                oddT = oddT.next;
            }
            cur = cur.next;
            flag = !flag;
        }
        evenT.next = null;
        oddT.next = even.next;
        return odd.next;
    }
}
