package com.javarena.dsa.datastructures.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph Representations and Utilities
 *
 * <p><b>Problem Statement:</b><br>
 * Implement graph data structures using adjacency matrix and adjacency list representations.
 * Provide utilities for graph conversion and manipulation.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Two main representations:
 * 
 * 1. Adjacency Matrix: 2D array where matrix[i][j] = 1 if edge exists
 *    - Space: O(V²)
 *    - Edge lookup: O(1)
 *    - Good for dense graphs
 * 
 * 2. Adjacency List: Array of lists, each list contains neighbors
 *    - Space: O(V + E)
 *    - Edge lookup: O(degree)
 *    - Good for sparse graphs
 * 
 * Includes Pair class for coordinates/values in graph problems.
 *
 * <p><b>Time Complexity:</b> Varies by operation
 * <br><b>Space Complexity:</b> O(V²) for matrix, O(V+E) for list
 */

/**
 * Adjacency Matrix representation for undirected graph.
 */
class AdjacencyMatrixUndirectedGraph {
    private final int[][] graph;
    private final int n;

    public AdjacencyMatrixUndirectedGraph(int n) {
        this.n = n;
        this.graph = new int[n][n];
    }

    public boolean isValidEdge(int i, int j) {
        return i >= 0 && j >= 0 && i < n && j < n;
    }

    public void displayMatrix() {
        for (int[] row : graph) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public void addEdge(int i, int j) {
        if (isValidEdge(i, j)) {
            graph[i][j] = 1;
            graph[j][i] = 1;
        }
    }
}

class AdjacencyListUndirectedGraph {
    private final List<List<Integer>> graph;
    private final int n;

    public AdjacencyListUndirectedGraph(int n) {
        this.n = n;
        this.graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public boolean isValidEdge(int i, int j) {
        return i >= 0 && j >= 0 && i < n && j < n;
    }

    public void displayMatrix() {
        for (int i = 0; i < n; i++) {
            System.out.print(i + ": "); // Print the vertex
            for (int j : graph.get(i)) {
                System.out.print(j + " "); // Print its adjacent
            }
            System.out.println();
        }
    }

    public void addEdge(int i, int j) {
        if (isValidEdge(i, j)) {
            graph.get(i).add(j);
            graph.get(j).add(i);
        }
    }
}

class Pair {
    int row;
    int col;
    int value;

    public Pair(int i, int j, int k) {
        this.row = i;
        this.col = j;
        this.value = k;
    }

    public Pair(int i, int j) {
        this.row = i;
        this.col = j;
    }
}

public class Graph {

    public static List<List<Integer>> convertMatrixToAdjList(int[][] matrix) {
        List<List<Integer>> adj = new ArrayList<>();
        int v = matrix.length;
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (matrix[i][j] != 0) {
                    adj.get(i).add(j);
                }
            }
        }

        return adj;
    }

    public static void main(String[] args) {
        AdjacencyMatrixUndirectedGraph graph1 = new AdjacencyMatrixUndirectedGraph(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(0, 2);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);
        System.out.println("Adjacency Matrix Representation");
        graph1.displayMatrix();
        System.out.println("===========================================");
        AdjacencyListUndirectedGraph graph2 = new AdjacencyListUndirectedGraph(4);
        graph2.addEdge(0, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 3);
        System.out.println("Adjacency List Representation");
        graph2.displayMatrix();
    }
}
