package com.javarena.dsa.algorithms.greedy;

import java.util.PriorityQueue;

/**
 * Maximum Average Pass Ratio
 *
 * <p><b>Problem Statement:</b><br>
 * Given classes with (pass, total) students and extraStudents to assign,
 * maximize average pass ratio across all classes by assigning students optimally.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Greedy with max-heap on gain:
 * - Gain of adding student to class (i,j): (i+1)/(j+1) - i/j
 * - Always assign to class with maximum gain (greedy choice)
 * - Gain decreases as students added, so recalculate each time
 * 
 * Strategy:
 * 1. Build max-heap ordered by gain function
 * 2. For each extra student:
 *    - Pop class with max gain
 *    - Add one student (pass++, total++)
 *    - Recalculate gain and push back
 * 3. Calculate final average of all pass ratios
 * 
 * Greedy works: Maximum gain choice always optimal for overall average.
 *
 * <p><b>Time Complexity:</b> O((N + E) log N) - N classes, E extra students
 * <br><b>Space Complexity:</b> O(N) - Priority queue storage
 */
public class MaxAvgRatio {
    
    /**
     * Maximizes average pass ratio using greedy heap approach.
     */
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Double.compare(b[2], a[2])  // Sort by gain descending
        );
        
        for (int[] c : classes) {
            int pass = c[0];
            int total = c[1];
            double gain = calculateGain(pass, total);
            maxHeap.offer(new double[]{pass, total, gain});
        }
        
        for (int i = 0; i < extraStudents; i++) {
            double[] best = maxHeap.poll();
            int pass = (int) best[0] + 1;
            int total = (int) best[1] + 1;
            double newGain = calculateGain(pass, total);
            maxHeap.offer(new double[]{pass, total, newGain});
        }
        
        double totalRatio = 0.0;
        while (!maxHeap.isEmpty()) {
            double[] c = maxHeap.poll();
            totalRatio += c[0] / c[1];
        }
        
        return totalRatio / classes.length;
    }
    
    /**
     * Calculates gain from adding one student to class (pass, total).
     */
    private double calculateGain(int pass, int total) {
        return ((double)(pass + 1) / (total + 1)) - ((double) pass / total);
    }
}
