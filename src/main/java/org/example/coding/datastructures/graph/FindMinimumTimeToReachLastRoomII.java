package org.example.coding.datastructures.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FindMinimumTimeToReachLastRoomII {

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
     * Computes the minimum time required to reach the bottom-right room (n-1, m-1)
     * in a dungeon grid where each room (i,j) can only be entered at or after
     * a given time moveTime[i][j]. Movement rules:
     * <ul>
     *   <li>You start at (0,0) at time 0.</li>
     *   <li>You may only move into adjacent cells (up, down, left, right).</li>
     *   <li>Moving between adjacent rooms alternates between taking 1 second
     *       and 2 seconds: the first move costs 1 second, the next costs 2 seconds,
     *       then 1 second, then 2 seconds, and so on.</li>
     *   <li>You cannot start moving into room (i,j) before time moveTime[i][j].</li>
     * </ul>
     *
     * <p><b>Intuition:</b>
     * This is a shortest path problem on a grid with alternating edge weights
     * (1s or 2s) and additional waiting constraints. Standard BFS/Dijkstra needs
     * to be modified to track both the current position and the parity of the
     * last move (whether the next step should take 1s or 2s). Each state is thus
     * defined as (row, col, stepType). The algorithm explores states with the
     * earliest possible times first using a priority queue (Dijkstra’s algorithm).
     *
     * <p><b>Approach:</b>
     * <ol>
     *   <li>Use a priority queue (min-heap) to always expand the earliest reachable state.</li>
     *   <li>Maintain a distance array dist[n][m][2], where dist[i][j][stepType] stores
     *       the minimum time to reach cell (i,j) with next move type = stepType.</li>
     *   <li>When moving to a neighbor (ni,nj):
     *       <ul>
     *         <li>Wait until max(currentTime, moveTime[ni][nj]) before starting the move.</li>
     *         <li>Add the step cost (1 or 2, depending on stepType).</li>
     *         <li>Update the state with the opposite stepType (since moves alternate).</li>
     *       </ul>
     *   </li>
     *   <li>Stop when (n-1, m-1) is reached, returning its earliest time.</li>
     * </ol>
     *
     * <p><b>Time Complexity:</b> O(n * m * log(n*m)) —
     * each of the n*m cells has 2 possible stepTypes, and every push/pop on the priority queue
     * takes logarithmic time.</p>
     *
     * <p><b>Space Complexity:</b> O(n * m) —
     * storing distances for 2 states per cell, plus the priority queue.</p>
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
