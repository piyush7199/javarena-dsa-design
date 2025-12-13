package com.javarena.dsa.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Network Delay Time
 *
 * <p><b>Problem Statement:</b><br>
 * Given network of n nodes and list of travel times [u, v, time], find minimum time for signal
 * sent from node k to reach all nodes. Return -1 if impossible.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Classic Dijkstra's algorithm application:
 * - Build weighted directed graph from times array
 * - Use min-heap to always process node with shortest delay
 * - Track shortest time to reach each node
 * - Answer = maximum of all shortest times (last node to receive signal)
 * - If any node unreachable (distance = infinity), return -1
 * 
 * Key: Need maximum because all nodes must receive signal.
 *
 * <p><b>Time Complexity:</b> O(E log V) - E edges, priority queue operations
 * <br><b>Space Complexity:</b> O(V + E) - Adjacency list + distance array
 */
public class NetworkDelay {

    /**
     * Finds minimum time for signal to reach all nodes.
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        // Build adjacency list
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int wt = time[2];
            adj.get(u).add(new int[]{v, wt});
        }

        // Dijkstra's algorithm
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

        // Find maximum time (last node to receive signal)
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (dis[i] == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, dis[i]);
        }
        return ans;
    }
}
