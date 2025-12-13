package com.javarena.dsa.datastructures.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Find Minimum Time to Reach Last Room I
 *
 * <p><b>Problem Statement:</b><br>
 * Given n×m grid where moveTime[i][j] = earliest time room (i,j) opens.
 * Start at (0,0) at time 0. Moving between adjacent rooms takes 1 second.
 * Cannot enter room before it opens. Find minimum time to reach (n-1, m-1).
 *
 * <p><b>Intuition & Approach:</b><br>
 * Shortest path on weighted grid using Dijkstra:
 * - Each move costs 1 second
 * - Must wait if room not open yet
 * - Time to enter neighbor (nx, ny) = max(currentTime, moveTime[nx][ny]) + 1
 * 
 * Algorithm:
 * - Use min-heap to process cells by earliest arrival time
 * - Track minimum time to reach each cell
 * - For each neighbor: calculate arrival time considering wait
 * - Update if found shorter path
 * - Return time when reaching (n-1, m-1)
 *
 * <p><b>Time Complexity:</b> O(N×M log(N×M)) - Each cell enters PQ once
 * <br><b>Space Complexity:</b> O(N×M) - Distance array + visited + PQ
 */
public class FindMinimumTimeToReachLastRoomI {
    private static final int INF = 0x3f3f3f3f;

    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length, m = moveTime[0].length;
        int[][] d = new int[n][m];
        boolean[][] v = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(d[i], INF);
        }

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        d[0][0] = 0;
        PriorityQueue<State> q = new PriorityQueue<>();
        q.offer(new State(0, 0, 0));

        while (!q.isEmpty()) {
            State s = q.poll();
            if (v[s.x][s.y]) {
                continue;
            }
            v[s.x][s.y] = true;
            for (int[] dir : dirs) {
                int nx = s.x + dir[0];
                int ny = s.y + dir[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                // Earliest time we can reach (nx, ny)
                int dist = Math.max(d[s.x][s.y], moveTime[nx][ny]) + 1;
                if (d[nx][ny] > dist) {
                    d[nx][ny] = dist;
                    q.offer(new State(nx, ny, dist));
                }
            }
        }
        return d[n - 1][m - 1];
    }

    /**
     * Helper class representing a state in the priority queue.
     * Each state holds (x, y) coordinates and the time 'dis' when it is reached.
     * Implements Comparable to allow ordering by distance in the PQ.
     */
    static class State implements Comparable<State> {

        int x, y, dis;

        State(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.dis, other.dis);
        }
    }
}
