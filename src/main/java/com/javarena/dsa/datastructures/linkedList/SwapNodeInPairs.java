package com.javarena.dsa.datastructures.linkedList;

/**
 * Swap Nodes In Pairs
 *
 * <p><b>Problem Statement:</b><br>
 * Swap every two adjacent nodes in a singly linked list.
 * Only pointers are changed, not node values.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Pointer manipulation with dummy node:
 * - Use dummy node to simplify edge cases (first pair)
 * - For each pair (cur, second):
 *   1. Save next pair: npn = second.next
 *   2. Reverse pair: second.next = cur, cur.next = npn
 *   3. Connect previous: prev.next = second
 *   4. Move pointers: prev = cur, cur = npn
 * 
 * Process pairs iteratively until end.
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass, each node visited once
 * <br><b>Space Complexity:</b> O(1) - Only a few pointers used
 */
public class SwapNodeInPairs {

    /**
     * Swaps adjacent pairs of nodes.
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, cur = head;

        while (cur != null && cur.next != null) {
            ListNode npn = cur.next.next;
            ListNode second = cur.next;

            second.next = cur;
            cur.next = npn;
            prev.next = second;

            prev = cur;
            cur = npn;
        }
        return dummy.next;
    }
}
