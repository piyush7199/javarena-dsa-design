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

    /**
     * Adds two numbers represented by two non-empty linked lists, where each node contains a single digit.
     * The digits are stored in reverse order, and each of their nodes contains a single digit.
     * <p>
     * Intuition:
     * - This simulates the process of addition (like how we do on paper).
     * - We keep adding digits from both lists and carry forward any overflow using a variable `r`.
     * - If one list is shorter, we continue with the longer list and the carry.
     * - A final carry is appended if non-zero.
     * <p>
     * Time Complexity: O(max(N, M)) where N = length of l1, M = length of l2
     * Space Complexity: O(max(N, M)) for the resulting linked list
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        ListNode h1 = l1;
        ListNode h2 = l2;
        int r = 0;
        while (h1 != null && h2 != null) {
            int sum = h1.val + h2.val + r;
            r = (sum / 10);
            head.next = new ListNode(sum % 10);
            head = head.next;
            h1 = h1.next;
            h2 = h2.next;
        }

        while (h1 != null) {
            int sum = h1.val + r;
            r = (sum / 10);
            head.next = new ListNode(sum % 10);
            head = head.next;
            h1 = h1.next;
        }

        while (h2 != null) {
            int sum = h2.val + r;
            r = (sum / 10);
            head.next = new ListNode(sum % 10);
            head = head.next;
            h2 = h2.next;
        }

        while (r != 0) {
            head.next = new ListNode(r % 10);
            r /= 10;
        }
        return dummy.next;
    }

    public ListNode addOne(ListNode head) {
        ListNode cur = reverseListIter(head);
        ListNode one = new ListNode(1);
        ListNode sum = addTwoNumbers(cur, one);
        return reverseListIter(sum);
    }

    /**
     * Get Intersection Node of Two Linked Lists
     * <p>
     * Intuition:
     * Use two pointers traversing both lists. When one reaches the end, redirect it to the other list's head.
     * They will meet at the intersection point or both reach null.
     * <p>
     * Time Complexity: O(n + m)
     * Space Complexity: O(1)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA;
        ListNode l2 = headB;
        while (l1 != l2) {
            l1 = l1 == null ? headA : l1.next;
            l2 = l2 == null ? headB : l2.next;
        }
        return l1;
    }

    /**
     * Detect the start of the cycle in a linked list using Floyd’s Cycle Detection algorithm.
     * <p>
     * Intuition:
     * - Use two pointers (slow and fast) to detect a cycle.
     * - Once a cycle is found, move one pointer to head, and move both one step at a time — they’ll meet at the cycle start.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) break;
        }
        if (fast == null || fast.next == null) return null;
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * Check if a linked list is a palindrome.
     * <p>
     * Intuition:
     * - Use fast and slow pointers to find the middle.
     * - Reverse the second half of the list and compare node-by-node with the first half.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1) (modifies the list, but uses no extra space)
     */
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rev = reverseListIter(slow.next); // reverse second list
        slow.next = null;
        while (rev != null) {
            if (head.val != rev.val) {
                return false;
            }
            head = head.next;
            rev = rev.next;
        }
        return true;
    }

    /**
     * Segregate 0s, 1s, and 2s in a Linked List (Dutch National Flag Problem)
     * <p>
     * Intuition:
     * Create separate lists for 0s, 1s, and 2s and connect them in the end.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode segregate(ListNode head) {
        // code here
        ListNode zeroNode = new ListNode(0);
        ListNode oneNode = new ListNode(0);
        ListNode twoNode = new ListNode(0);

        ListNode zero = zeroNode;
        ListNode one = oneNode;
        ListNode two = twoNode;


        ListNode cur = head;
        while (cur != null) {
            if (cur.val == 0) {
                zero.next = cur;
                zero = zero.next;
            } else if (cur.val == 1) {
                one.next = cur;
                one = one.next;
            } else {
                two.next = cur;
                two = two.next;
            }

            cur = cur.next;
        }
        zero.next = oneNode.next != null ? oneNode.next : twoNode.next;

        one.next = twoNode.next;
        two.next = null;

        head = zeroNode.next;

        return head;
    }
}
