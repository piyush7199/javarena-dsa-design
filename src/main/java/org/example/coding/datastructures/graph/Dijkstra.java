package org.example.coding.datastructures.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    static class Edge {
        int target;
        int weight;

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    /**
     * Finds shortest paths from source to all nodes in a weighted graph.
     *
     * @TimeComplexity O(( V + E) log V) with binary heap
     * @SpaceComplexity O(V + E) for adjacency list and priority queue
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
