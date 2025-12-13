package com.javarena.dsa.datastructures.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Find Minimum Time to Reach Last Room II
 *
 * <p><b>Problem Statement:</b><br>
 * Similar to Room I, but moves alternate between 1 second and 2 seconds.
 * First move: 1s, second: 2s, third: 1s, etc. Find minimum time to reach (n-1, m-1).
 *
 * <p><b>Intuition & Approach:</b><br>
 * Modified Dijkstra with alternating edge weights:
 * - Track state as (row, col, stepType) where stepType = parity of next move
 * - stepType 0: next move costs 1 second
 * - stepType 1: next move costs 2 seconds
 * - After each move, toggle stepType
 * 
 * Algorithm:
 * - Use 3D distance array: dist[i][j][stepType]
 * - Process states by earliest time using min-heap
 * - For each neighbor:
 *   - Wait if room not open: max(currentTime, moveTime[nx][ny])
 *   - Add step cost (1 or 2 based on stepType)
 *   - Toggle stepType for next state
 * - Return earliest time to reach destination
 *
 * <p><b>Time Complexity:</b> O(N×M log(N×M)) - 2 states per cell
 * <br><b>Space Complexity:</b> O(N×M) - Distance array + PQ
 */
public class FindMinimumTimeToReachLastRoomII {

    /**
     * State tracking position and move parity.
     */
    static class State {
        int i, j, stepType, time;

        State(int i, int j, int stepType, int time) {
            this.i = i;
            this.j = j;
            this.stepType = stepType; // 0 -> next move takes 1s, 1 -> next move takes 2s
            this.time = time;
        }
    }

    /**
     * Finds minimum time with alternating move costs.
     */
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length, m = moveTime[0].length;
        int[][][] dist = new int[n][m][2];
        for (int[][] layer : dist)
            for (int[] row : layer)
                Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> a.time - b.time);
        dist[0][0][0] = 0; // start at (0,0), next step will take 1s
        pq.offer(new State(0, 0, 0, 0));

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int i = cur.i, j = cur.j, stepType = cur.stepType, t = cur.time;

            if (i == n - 1 && j == m - 1) return t;

            if (t > dist[i][j][stepType]) continue;

            for (int k = 0; k < 4; k++) {
                int ni = i + dr[k], nj = j + dc[k];
                if (ni < 0 || nj < 0 || ni >= n || nj >= m) continue;

                int moveCost = (stepType == 0 ? 1 : 2);
                int startMove = Math.max(t, moveTime[ni][nj]);
                int arriveTime = startMove + moveCost;

                int nextStepType = 1 - stepType;
                if (arriveTime < dist[ni][nj][nextStepType]) {
                    dist[ni][nj][nextStepType] = arriveTime;
                    pq.offer(new State(ni, nj, nextStepType, arriveTime));
                }
            }
        }

        return -1; // unreachable
    }

}
