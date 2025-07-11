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

    static class State {
        int city;
        int cost;
        int stops;

        State(int city, int cost, int stops) {
            this.city = city;
            this.cost = cost;
            this.stops = stops;
        }
    }

    static class Pair {
        int row;
        int col;
        int effort;

        public Pair(int row, int col, int effort) {
            this.row = row;
            this.col = col;
            this.effort = effort;
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

    /**
     * Finds the minimum effort required to travel from top-left to bottom-right cell in a 2D grid.
     * <p>
     * Intuition:
     * - Use modified Dijkstra's algorithm where instead of minimizing distance, we minimize the *maximum* height difference along any path.
     * - Each move adds the max of current path effort and the height difference between adjacent cells.
     * - Stop once we reach the destination cell with the minimum possible max effort.
     * <p>
     * Time Complexity: O(N * M * log(N * M)) — each cell can be visited once with priority queue operations
     * Space Complexity: O(N * M) — for effort matrix and priority queue
     */
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        int[][] effort = new int[n][m];
        for (int[] row : effort) Arrays.fill(row, Integer.MAX_VALUE);
        effort[0][0] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.effort));

        pq.offer(new Pair(0, 0, 0));

        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int r = cur.row;
            int c = cur.col;
            int currEffort = cur.effort;

            if (r == n - 1 && c == m - 1) return currEffort; // reached destination

            for (int i = 0; i < 4; i++) {
                int newRow = r + dRow[i];
                int newCol = c + dCol[i];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m) {
                    int heightDiff = Math.abs(heights[newRow][newCol] - heights[r][c]);
                    int maxEffort = Math.max(currEffort, heightDiff);

                    if (maxEffort < effort[newRow][newCol]) {
                        effort[newRow][newCol] = maxEffort;
                        pq.offer(new Pair(newRow, newCol, maxEffort));
                    }
                }
            }
        }

        return -1; // should never reach here
    }

    /**
     * Finds the cheapest price to reach the destination from the source with at most `k` stops.
     * <p>
     * Intuition:
     * - Use Dijkstra’s-like approach but with a modification to track number of stops.
     * - Use a priority queue to always expand the cheapest path next.
     * - Skip paths that exceed the allowed number of stops or are not better than a previously found one.
     * <p>
     * Time Complexity: O(E * logV) — where E is the number of flights and V is the number of cities
     * Space Complexity: O(V + E) — for adjacency list and visited array
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] flight : flights)
            adj.get(flight[0]).add(new Edge(flight[1], flight[2]));

        // PriorityQueue by cost
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        pq.offer(new State(src, 0, 0));

        // best[city][stops] = min cost to reach that city using `stops` stops
        int[] visitedStops = new int[n];
        Arrays.fill(visitedStops, Integer.MAX_VALUE);

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int city = cur.city, cost = cur.cost, stops = cur.stops;

            // If destination is reached
            if (city == dst) return cost;

            // Don't process if more than k stops
            if (stops > k || visitedStops[city] < stops) continue;
            visitedStops[city] = stops;

            for (Edge edge : adj.get(city)) {
                pq.offer(new State(edge.target, cost + edge.weight, stops + 1));
            }
        }

        return -1;
    }
}
