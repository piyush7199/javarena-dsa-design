package org.example.coding.datastructures.linkedList;

public class SwapNodeInPairs {

    /**
     * Swaps every two adjacent nodes in a singly linked list.
     * <p>
     * Given the head of a singly linked list, this method swaps every two adjacent nodes
     * and returns the new head of the modified list. Node values are not modified—only
     * the node connections (pointers) are changed.
     * </p>
     *
     *
     * <p>
     * A dummy node is used to simplify edge cases, especially when swapping the first pair.
     * Pointers are moved in groups of two to perform in-place swaps.
     * </p>
     *
     * @implNote The method performs in-place swapping using constant extra space.
     * Only node pointers are changed, not the node values.
     * @timecomplexity O(n) - where n is the number of nodes in the list.
     * Each node is visited once during the iteration.
     * @spacecomplexity O(1) - No additional space is used other than a few pointers.
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
