package org.example.coding.datastructures.graph;

import java.util.*;

public class GraphAlgorithms {
    /**
     * Performs Breadth-First Search (BFS) starting from a source node.
     *
     * <p><b>Time Complexity:</b> O(V+E)
     * <br><b>Space Complexity:</b> O(V)
     */
    public void bfs(List<List<Integer>> adj, int s, boolean[] visited, List<Integer> res) {
        Queue<Integer> queue = new LinkedList<>();
        visited[s] = true;
        queue.offer(s);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            res.add(node);

            for (int newNode : adj.get(node)) {
                if (!visited[newNode]) {
                    visited[newNode] = true;
                    queue.offer(newNode);
                }
            }
        }
    }

    /**
     * Returns BFS traversal of a graph starting from node 's'.
     * Also handles disconnected graphs by checking unvisited nodes.
     */
    public List<Integer> bfs(List<List<Integer>> adj, int V, int s) {
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[V];
        bfs(adj, s, visited, res);

        // For disconnected components
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                bfs(adj, i, visited, res);
            }
        }

        return res;
    }

    /**
     * Recursive Depth-First Search (DFS) from a given node.
     *
     * <p><b>Time Complexity:</b> O(V+E)
     * <br><b>Space Complexity:</b> O(V) (recursive call stack + visited array)
     */
    public void dfs(List<List<Integer>> adj, int s, boolean[] visited, List<Integer> res) {
        visited[s] = true;
        res.add(s);

        for (int newNode : adj.get(s)) {
            if (!visited[newNode]) {
                dfs(adj, newNode, visited, res);
            }
        }
    }

    /**
     * Iterative DFS using a stack.
     * Useful when recursion depth is a concern.
     *
     * <p><b>Time Complexity:</b> O(V+E)
     * <br><b>Space Complexity:</b> O(V) (stack + visited array)
     */
    private void dfsUsingStack(List<List<Integer>> adj, int s, boolean[] visited, List<Integer> res) {
        Stack<Integer> stack = new Stack<>();
        stack.push(s);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (visited[node]) {
                continue;
            }

            visited[node] = true;
            res.add(node);

            // Add neighbors in reverse to simulate recursive DFS
            List<Integer> neighbors = adj.get(node);
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                int v = neighbors.get(i);
                if (!visited[v]) {
                    stack.push(v);
                }
            }
        }
    }

    /**
     * Returns DFS traversal of a graph starting from node 's'.
     * Also handles disconnected components.
     *
     * <p><b>Time Complexity:</b> O(V+E)
     * <br><b>Space Complexity:</b> O(V)
     */
    public List<Integer> dfs(List<List<Integer>> adj, int V, int s) {
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[V];
        dfs(adj, s, visited, res);

        // For disconnected components
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(adj, i, visited, res);
            }
        }

        return res;
    }
}
