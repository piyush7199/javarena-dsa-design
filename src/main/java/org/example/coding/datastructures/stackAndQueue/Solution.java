package org.example.coding.datastructures.stackAndQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {

    /**
     * Generates binary numbers from 1 to n in string format using BFS.
     *
     * <p><b>Intuition:</b> Use a queue to generate numbers by appending "0" and "1" to previously generated binary strings.
     * This ensures that the numbers are generated in level-order (i.e., lexicographically increasing in binary form).
     *
     * <p><b>Time Complexity:</b> O(n) — One operation per binary number.
     * <p><b>Space Complexity:</b> O(n) — For the queue and output list.
     */
    public static ArrayList<String> generate(int n) {
        ArrayList<String> ans = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer("1");
        while (n > 0) {
            String s1 = queue.poll();
            ans.add(s1);
            queue.offer(s1 + "0");
            queue.offer(s1 + "1");
            n--;
        }
        return ans;
    }

    /**
     * Reverses the first k elements of a queue.
     *
     * <p><b>Intuition:</b> Use a stack to reverse the first k elements,
     * then enqueue them back, followed by the remaining queue elements.
     *
     * <p><b>Time Complexity:</b> O(n) — All elements are touched once.
     * <p><b>Space Complexity:</b> O(k) — Stack stores first k elements.
     */
    public Queue<Integer> reverseFirstK(Queue<Integer> q, int k) {
        // code here
        int qSize = q.size();
        if (qSize < k) return q;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < k; i++) {
            st.push(q.poll());
        }
        Queue<Integer> ans = new LinkedList<>();
        while (!st.isEmpty()) {
            ans.offer(st.pop());
        }
        while (!q.isEmpty()) {
            ans.offer(q.poll());
        }
        return ans;
    }
}
