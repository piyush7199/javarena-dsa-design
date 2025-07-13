package org.example.coding.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class NetworkDelay {

    /**
     * Dijkstra’s Algorithm to find the time it takes for all nodes to receive a signal sent from node `k`.
     * <p>
     * Intuition:
     * - Use a min-heap (priority queue) to always pick the node with the least accumulated delay.
     * - Track the shortest time to reach each node using a distance array.
     * - Return the maximum time from source to all other nodes, or -1 if any node is unreachable.
     * <p>
     * Time Complexity: O(E * log V) — E edges, V vertices due to priority queue operations.
     * Space Complexity: O(V + E) — adjacency list and distance array.
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int wt = time[2];
            adj.get(u).add(new int[]{v, wt});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pq.offer(new int[]{k, 0});
        int[] dis = new int[n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[k] = 0;
        while (!pq.isEmpty()) {
            int node = pq.peek()[0];
            int time = pq.peek()[1];
            pq.poll();
            for (int[] edge : adj.get(node)) {
                int nn = edge[0];
                int nTime = time + edge[1];
                if (nTime < dis[nn]) {
                    pq.offer(new int[]{nn, nTime});
                    dis[nn] = nTime;
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (dis[i] == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, dis[i]);
        }
        return ans;
    }
}
