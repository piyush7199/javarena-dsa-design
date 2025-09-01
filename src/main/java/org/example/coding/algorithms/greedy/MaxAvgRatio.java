package org.example.coding.algorithms.greedy;

import java.util.PriorityQueue;

/**
 * Intuition:
 * -----------
 * We are given several classes, each with a number of passing students (i)
 * and a total number of students (j). The pass ratio for a class is i/j.
 * <p>
 * When we add one more passing student, the new ratio becomes (i+1)/(j+1).
 * The important observation is that we should always assign an extra student
 * to the class where this operation yields the **largest gain**:
 * <p>
 * gain(i, j) = (i+1)/(j+1) - i/j
 * <p>
 * By repeatedly applying this greedy choice, we ensure that every student
 * contributes maximally to the overall average.
 * <p>
 * Approach:
 * -----------
 * 1. Use a max-heap (PriorityQueue) ordered by "gain" instead of current avg.
 * 2. For each class, compute its initial gain and push it into the heap.
 * 3. While we still have extra students:
 * - Pop the class with the maximum gain.
 * - Add one passing student to it (i++, j++).
 * - Push it back with its updated gain.
 * 4. Finally, compute the average ratio across all classes.
 * <p>
 * Time Complexity:
 * - Building the heap: O(n log n), where n = number of classes.
 * - For each extra student, we do one poll and one offer: O(extraStudents * log n).
 * - Final accumulation: O(n).
 * - Total: O((n + extraStudents) * log n).
 * <p>
 * Space Complexity:
 * - PriorityQueue holds all n classes → O(n).
 */
public class MaxAvgRatio {

    /**
     * Node representing a class with i passing students and j total students
     */
    static class Node {
        int i, j;

        Node(int i, int j) {
            this.i = i;
            this.j = j;
        }

        /**
         * Current average ratio
         */
        double avg() {
            return (double) i / j;
        }

        /**
         * Gain if we add one more passing student
         */
        double gain() {
            return ((double) (i + 1) / (j + 1)) - ((double) i / j);
        }
    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // Max-heap ordered by gain
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(b.gain(), a.gain())
        );

        // Insert all classes into the heap
        for (int[] arr : classes) {
            pq.offer(new Node(arr[0], arr[1]));
        }

        // Assign extra students one by one
        for (int k = 0; k < extraStudents; k++) {
            Node node = pq.poll();   // take the class with max gain
            node.i++;                // add one passing student
            node.j++;                // total students also increases
            pq.offer(node);          // push it back with updated gain
        }

        // Compute final average ratio
        double ans = 0;
        int n = pq.size();
        while (!pq.isEmpty()) {
            ans += pq.poll().avg();
        }

        return ans / n;
    }
}
