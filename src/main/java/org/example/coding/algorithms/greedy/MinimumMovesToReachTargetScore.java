package org.example.coding.algorithms.greedy;

public class MinimumMovesToReachTargetScore {
    /**
     * Calculates the minimum number of moves required to reduce a target
     * number to 1 using at most a given number of "double" operations.
     *
     * <p>
     * Allowed operations:
     * - Decrement the target by 1.
     * - If maxDoubles > 0 and target is even, divide target by 2.
     * </p>
     *
     * <p><b>Intuition:</b><br>
     * Work backwards from target → 1.
     * Dividing by 2 is much more powerful than decrementing by 1,
     * so we greedily apply division whenever possible and allowed.
     * Once we run out of "double" operations, the only option is to decrement
     * step-by-step until reaching 1.
     * </p>
     *
     * <p><b>Approach:</b><br>
     * 1. While target > 1:
     * - If target is even and maxDoubles > 0 → divide by 2 (use a double operation).
     * - Else → decrement target by 1.
     * - Decrease maxDoubles if a double operation was used.
     * 2. If maxDoubles becomes 0, we must decrement the rest (target - 1).
     * 3. Count all operations and return.
     * </p>
     *
     * <p><b>Time Complexity:</b> O(log target)<br>
     * - Each division by 2 halves the target, so there are at most O(log target) such steps.<br>
     * - Each decrement operation reduces target by 1, but those only occur after maxDoubles is exhausted.<br>
     * </p>
     *
     * <p><b>Space Complexity:</b> O(1)<br>
     * - Uses only a few variables for counters and input tracking.<br>
     * </p>
     */
    public int minMoves(int target, int maxDoubles) {
        int cnt = 0;
        while (target > 1) {
            if (maxDoubles > 0 && target % 2 == 0) {
                target /= 2;
                maxDoubles--;
            } else {
                target--;
            }
            if (maxDoubles == 0) {
                return cnt + target;
            }
            cnt++;
        }
        return cnt;
    }

}
