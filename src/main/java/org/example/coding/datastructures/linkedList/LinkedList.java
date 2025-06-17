package org.example.coding.datastructures.linkedList;

public class LinkedList {

    /**
     * Reverses a singly linked list using an iterative approach.
     *
     * <p>Uses two pointers (prev and curr) to reverse the direction of the list one node at a time.
     * This method operates in-place and returns the new head of the reversed list.
     *
     * <pre>{@code
     * Input:  1 -> 2 -> 3 -> 4 -> null
     * Output: 4 -> 3 -> 2 -> 1 -> null
     * }</pre>
     * <p>
     * Time and Space Complexity:
     * <ul>
     *   <li>Time Complexity: O(n)</li>
     *   <li>Space Complexity: O(1)</li>
     * </ul>
     */
    public static ListNode reverseListIter(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next; // Step 1: store the next node
            curr.next = prev;          // Step 2: reverse the link
            prev = curr;               // Step 3: move prev to current
            curr = next;               // Step 4: move to next node
        }
        return prev;
    }

    /**
     * Reverses a singly linked list using a recursive approach.
     *
     * <p>This method works by recursively reversing the sublist starting from the second node,
     * then adjusting pointers to reverse the current node. It uses the call stack
     * to hold the previous state, effectively reversing the list from the end toward the beginning.
     *
     * <pre>{@code
     * Example:
     * Input:  1 -> 2 -> 3 -> 4 -> null
     * Output: 4 -> 3 -> 2 -> 1 -> null
     * }</pre>
     * <p>
     * Time and Space Complexity:
     * <ul>
     *   <li>Time Complexity: O(n)</li>
     *   <li>Space Complexity: O(n), recursion stack</li>
     * </ul>
     */

    public static ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * <p>
     * In a linked list with a loop, consider two pointers: one that moves one node at a time (slow) and another that moves two nodes at a time (fast). If we start moving these pointers with their defined speed they will surely enter the loop and might be at some distance 'd' from each other within the loop.
     * </p>
     * <p>
     * The key insight here is the relative speed between these pointers. The fast pointer, moving at double the speed of the slow one, closes the gap between them by one node in every iteration. This means that with each step, the distance decreases by one node.
     * </p>
     * <p>
     * Imagine a race where one runner moves at twice the speed of another. The faster runner covers the ground faster and closes the gap, resulting in a reduction in the distance between them. Similarly, the fast pointer catches up to the slow pointer in the looped linked list, closing in the gap between them until it reaches zero.
     * </p>
     * <p>
     * Time and Space Complexity:
     * <ul>
     *   <li>Time Complexity: O(n)</li>
     *   <li>Space Complexity: O(1)</li>
     * </ul>
     */
    public static boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }

    /**
     * Time and Space Complexity:
     * <ul>
     *   <li>Time Complexity: O(m + n)</li>
     *   <li>Space Complexity: O(1)</li>
     * </ul>
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode curList = new ListNode(-1);
        ListNode head = curList;
        ListNode cur1 = list1;
        ListNode cur2 = list2;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                curList.next = cur1;
                cur1 = cur1.next;
            } else {
                curList.next = cur2;
                cur2 = cur2.next;
            }
            curList = curList.next;
        }
        while (cur1 != null) {
            curList.next = cur1;
            cur1 = cur1.next;
            curList = curList.next;
        }

        while (cur2 != null) {
            curList.next = cur2;
            cur2 = cur2.next;
            curList = curList.next;
        }
        return head.next;
    }
}
