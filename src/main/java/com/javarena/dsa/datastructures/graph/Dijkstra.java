package com.javarena.dsa.datastructures.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Dijkstra's Shortest Path Algorithm
 *
 * <p><b>Problem Statement:</b><br>
 * Find shortest paths from a source node to all other nodes in a weighted graph with non-negative edge weights.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Greedy algorithm using priority queue (min-heap):
 * - Initialize distances array with infinity, source = 0
 * - Use min-heap to always process nearest unvisited node
 * - For each node, relax all outgoing edges:
 *   - If new path shorter: update distance, add to heap
 * - Mark nodes as visited to avoid reprocessing
 * - Continue until heap empty or all nodes processed
 * 
 * Key: Always process closest node first (greedy choice)
 * Works only with non-negative weights.
 *
 * <p><b>Time Complexity:</b> O((V + E) log V) with binary heap
 * <br><b>Space Complexity:</b> O(V + E) for adjacency list and priority queue
 */
public class Dijkstra {
    /**
     * Edge representation with target and weight.
     */
    static class Edge {
        int target;
        int weight;

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    /**
     * Finds shortest paths from source to all nodes.
     */
    public int[] dijkstra(List<List<Edge>> graph, int V, int source) {
        int[] distances = new int[V];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // (distance, node)
        pq.offer(new int[]{0, source});
        boolean[] visited = new boolean[V];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int dis = cur[0];
            int node = cur[1];
            if (visited[node]) continue;
            visited[node] = true;
            for (Edge edge : graph.get(node)) {
                int neighbour = edge.target;
                int wt = edge.weight;
                if (wt + dis < distances[neighbour]) {
                    distances[neighbour] = wt + dis;
                    pq.offer(new int[]{distances[neighbour], neighbour});
                }
            }
        }

        return distances;
    }
}
