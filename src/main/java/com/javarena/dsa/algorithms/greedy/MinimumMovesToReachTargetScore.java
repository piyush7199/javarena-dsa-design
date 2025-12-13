package com.javarena.dsa.algorithms.greedy;

/**
 * Minimum Moves to Reach Target Score
 *
 * <p><b>Problem Statement:</b><br>
 * Start at 1, reach target using minimum moves. Operations allowed:
 * - Increment by 1
 * - Double the value (limited to maxDoubles times)
 * Return minimum number of moves.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Work backwards (target → 1) using greedy:
 * - Doubling (forward) = halving (backward)
 * - Dividing by 2 much more powerful than decrementing
 * - Greedily use division whenever target is even and maxDoubles available
 * - Once maxDoubles exhausted: must decrement (target-1) times
 * 
 * Strategy:
 * - While target > 1:
 *   - If even and maxDoubles > 0: divide by 2, decrement maxDoubles
 *   - Else: decrement by 1
 * - Count all operations
 * 
 * Greedy choice: Use division operations optimally.
 *
 * <p><b>Time Complexity:</b> O(log target) - Each division halves target
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class MinimumMovesToReachTargetScore {
    
    /**
     * Calculates minimum moves using greedy backwards approach.
     */
    public int minMoves(int target, int maxDoubles) {
        int moves = 0;
        
        while (target > 1 && maxDoubles > 0) {
            if (target % 2 == 0) {
                target /= 2;
                maxDoubles--;
            } else {
                target--;
            }
            moves++;
        }
        
        // After exhausting doubles, only decrements remain
        moves += (target - 1);
        
        return moves;
    }
}
