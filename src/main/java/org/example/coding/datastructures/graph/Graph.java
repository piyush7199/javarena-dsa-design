package org.example.coding.datastructures.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Representation of Undirected Graph as Adjacency Matrix</b>
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
