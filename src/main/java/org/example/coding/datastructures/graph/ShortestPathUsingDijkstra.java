package org.example.coding.datastructures.graph;

import java.util.*;

public class ShortestPathUsingDijkstra {

    static class Edge {
        int target;
        int weight;

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public int[] dijkstra(int V, int[][] edges, int src) {
        // code here
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] row : edges) {
            int u = row[0];
            int v = row[1];
            int wt = row[2];
            adj.get(u).add(new Edge(v, wt));
            adj.get(v).add(new Edge(u, wt));
        }
        int[] distances = new int[V];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // (distance, node)
        pq.offer(new int[]{0, src});
        boolean[] visited = new boolean[V];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int dis = cur[0];
            int node = cur[1];
            if (visited[node]) continue;
            visited[node] = true;
            for (Edge edge : adj.get(node)) {
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
