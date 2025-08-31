package org.example.coding.datastructures.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Problem:
 * 3341. Find Minimum Time to Reach Last Room I
 * <p>
 * You are given an n x m grid where moveTime[i][j] represents the earliest time
 * when the room (i, j) opens. You start from (0, 0) at time t = 0.
 * Moving between adjacent rooms (up, down, left, right) takes exactly 1 second.
 * You cannot enter a room before it has opened.
 * Return the minimum time needed to reach the bottom-right room (n-1, m-1).
 * <p>
 * --------------------------------------------------------------------
 * Intuition:
 * This is essentially a shortest path problem on a weighted grid.
 * - Each move takes 1 second.
 * - A room (i, j) cannot be entered until time >= moveTime[i][j].
 * So, when moving into a neighbor cell (nx, ny), the time is:
 * max(currentTime, moveTime[nx][ny]) + 1
 * → currentTime: time when we're at current cell
 * → moveTime[nx][ny]: minimum opening time of the neighbor
 * → +1: movement cost
 * <p>
 * This structure exactly matches Dijkstra’s algorithm:
 * - Nodes = grid cells.
 * - Edge weight = 1 second (plus waiting if door not open).
 * - Goal = shortest time to reach (n-1, m-1).
 * <p>
 * --------------------------------------------------------------------
 * Approach:
 * - Use Dijkstra’s algorithm with a min-heap (PriorityQueue).
 * - Maintain a dist[][] array for minimum arrival time at each cell.
 * - Start from (0,0) with time = 0.
 * - Pop the cell with smallest time from PQ, mark visited.
 * - For each neighbor (nx, ny):
 * newTime = max(dist[x][y], moveTime[nx][ny]) + 1
 * If newTime < dist[nx][ny], update and push into PQ.
 * - Stop when we reach (n-1, m-1).
 * <p>
 * --------------------------------------------------------------------
 * Time Complexity:
 * - O(n*m log(n*m)), since each of the n*m cells can enter PQ once
 * and each PQ operation costs O(log(n*m)).
 * <p>
 * Space Complexity:
 * - O(n*m) for distance array, visited array, and PQ.
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
