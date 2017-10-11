package ua.nure.draban.Practice6.part4;

public class Graph {
    private int[][] graph;

    public Graph(int countTop) {
        graph = new int[countTop][countTop];
    }

    public boolean add(int v1, int v2) {
        boolean result = false;
        if (graph[v1][v2] == 0 && graph[v2][v1] == 0) {
            graph[v1][v2] = 1;
            graph[v2][v1] = 1;
            result = true;
        }
        return result;
    }

    public boolean remove(int v1, int v2) {
        boolean result = false;
        if (graph[v1][v2] == 1 && graph[v2][v1] == 1) {
            graph[v1][v2] = 0;
            graph[v2][v1] = 0;
            result = true;
        }
        return result;
    }
}
