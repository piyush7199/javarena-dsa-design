package org.example.coding.datastructures.stackAndQueue;

import java.util.HashMap;
import java.util.PriorityQueue;

public class TaskScheduler {
    /**
     * Solution to the Task Scheduler problem.
     *
     * <p>Intuition:
     * We are given a list of tasks (represented as characters) and a cooldown interval 'n'.
     * The same task cannot be executed again until after 'n' units of time.
     * To minimize total time, we should:
     *  1. Always execute the task with the highest remaining frequency first.
     *  2. Fill idle slots with other tasks if possible.
     *  3. If no other tasks are available, the CPU stays idle.
     *
     * <p>Approach:
     *  1. Count the frequency of each task using a HashMap.
     *  2. Use a max-heap (PriorityQueue) to always pick the most frequent task first.
     *  3. For each cycle of size (n + 1), schedule up to (n + 1) tasks:
     *      - Decrease their frequency by 1.
     *      - If a task still has remaining instances, put it into a temporary queue.
     *  4. After processing one cycle, push tasks from the temporary queue back into the max-heap.
     *  5. Repeat until all tasks are completed.
     *
     * <p>Time Complexity: O(T * log K)
     *  - T = total number of tasks
     *  - K = number of unique tasks
     *  - Each task insertion and removal from the priority queue takes O(log K).
     *
     * <p>Space Complexity: O(K)
     *  - Storing task frequencies and priority queues.
     */

    /**
     * Helper class to store a task and its remaining count.
     */
    static class Pair {
        char ch;
        int cnt;

        public Pair(char ch, int cnt) {
            this.ch = ch;
            this.cnt = cnt;
        }
    }

    /**
     * Calculates the least amount of time required to finish all tasks
     * given the cooldown interval 'n'.
     */
    public int leastInterval(char[] tasks, int n) {
        // Step 1: Count the frequency of each task
        HashMap<Character, Integer> map = new HashMap<>();
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }

        // Step 2: Max-heap based on task count (most frequent task at top)
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.cnt - a.cnt);
        for (char key : map.keySet()) {
            pq.offer(new Pair(key, map.get(key)));
        }

        int cnt = 0; // Total time counter

        // Step 3: Process tasks in chunks of size (n + 1)
        while (!pq.isEmpty()) {
            // Temporary queue to hold tasks waiting for the next cycle
            PriorityQueue<Pair> helper = new PriorityQueue<>((a, b) -> b.cnt - a.cnt);

            // Loop for one cycle of (n + 1)
            for (int i = 0; i <= n; i++) {
                if (!pq.isEmpty()) {
                    // Execute the most frequent task
                    Pair p = pq.poll();
                    p.cnt--; // Reduce remaining count
                    if (p.cnt > 0) {
                        // If the task is not finished, keep it for future
                        helper.offer(p);
                    }
                }
                cnt++; // Increase total time

                // If no tasks left to process, break early
                if (pq.isEmpty() && helper.isEmpty()) break;
            }

            // Push tasks from helper back into the main priority queue
            while (!helper.isEmpty()) {
                pq.offer(helper.poll());
            }
        }

        return cnt; // Return the total time required
    }

    /**
     * Solution to the Task Scheduler problem (Optimized).
     *
     * <p>Intuition:
     * Instead of simulating every cycle, we can directly calculate the minimum required
     * time by focusing on the task that appears most frequently.
     *
     * Key insights:
     * 1. The most frequent task determines the structure of the schedule because it
     *    needs to be spaced out by at least 'n' intervals.
     * 2. Imagine placing the most frequent task first, creating "gaps" that other tasks
     *    can fill.
     * 3. If there are multiple tasks with the same maximum frequency, they all need to
     *    be handled equally at the end.
     *
     * Example:
     * tasks = [A, A, A, B, B, B], n = 2
     *
     * - A and B both appear 3 times.
     * - Layout: A _ _ A _ _ A
     * - Fill blanks with B: A B _ A B _ A B
     * - Final answer = max(total tasks, (maxCount - 1) * (n + 1) + maxFreqCount)
     *
     * <p>Formula:
     * result = max(totalTasks, (maxCount - 1) * (n + 1) + maxFreqCount)
     *
     * Where:
     * - maxCount = frequency of the most frequent task
     * - maxFreqCount = number of tasks that appear maxCount times
     *
     * <p>Time Complexity: O(T)
     * - T = total number of tasks
     * - We iterate over the array and the map once.
     *
     * <p>Space Complexity: O(1) or O(K)
     * - O(K) if counting unique tasks (K = number of unique tasks).
     * - Maximum unique tasks = 26 (A-Z), so effectively O(1).
     */
    /**
     * Calculates the least amount of time required to finish all tasks
     * given the cooldown interval 'n'.
     *
     * @param tasks array of tasks represented by uppercase letters
     * @param n     cooldown interval between same tasks
     * @return minimum total time to finish all tasks
     */
    public int leastIntervalOpt(char[] tasks, int n) {
        // Step 1: Count the frequency of each task
        int[] freq = new int[26]; // Only 26 uppercase letters
        for (char task : tasks) {
            freq[task - 'A']++;
        }

        // Step 2: Find the maximum frequency of any task
        int maxCount = 0;
        for (int count : freq) {
            maxCount = Math.max(maxCount, count);
        }

        // Step 3: Count how many tasks have this maximum frequency
        int maxFreqCount = 0;
        for (int count : freq) {
            if (count == maxCount) {
                maxFreqCount++;
            }
        }

        // Step 4: Apply the formula
        // (maxCount - 1) * (n + 1) creates the base layout
        // Adding maxFreqCount accounts for multiple most frequent tasks
        int partCount = (maxCount - 1) * (n + 1) + maxFreqCount;

        // Step 5: Final result is the max between total tasks and calculated layout
        return Math.max(tasks.length, partCount);
    }
}
