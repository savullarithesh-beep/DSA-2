import java.util.*;

public class CO3 {

    private int vertices;
    private LinkedList<Integer>[] adjList;
    private int[][] weight;

    public CO3(int vertices) {
        this.vertices = vertices;

        adjList = new LinkedList[vertices];
        weight = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    void addEdge(int source, int destination, int cost) {

        adjList[source].add(destination);
        adjList[destination].add(source);

        weight[source][destination] = cost;
        weight[destination][source] = cost;
    }

    void BFS(int start) {

        boolean[] visited = new boolean[vertices];

        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {

            int node = queue.poll();

            System.out.print(node + " ");

            for (int neighbour : adjList[node]) {

                if (!visited[neighbour]) {

                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }

        System.out.println();
    }

    void DFSUtil(int node, boolean[] visited) {

        visited[node] = true;

        System.out.print(node + " ");

        for (int neighbour : adjList[node]) {

            if (!visited[neighbour]) {
                DFSUtil(neighbour, visited);
            }
        }
    }

    void DFS(int start) {

        boolean[] visited = new boolean[vertices];

        System.out.print("DFS Traversal: ");

        DFSUtil(start, visited);

        System.out.println();
    }

    void primMST() {

        int[] parent = new int[vertices];
        int[] key = new int[vertices];
        boolean[] mstSet = new boolean[vertices];

        Arrays.fill(key, Integer.MAX_VALUE);

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < vertices - 1; count++) {

            int min = Integer.MAX_VALUE;
            int u = -1;

            for (int v = 0; v < vertices; v++) {

                if (!mstSet[v] && key[v] < min) {
                    min = key[v];
                    u = v;
                }
            }

            mstSet[u] = true;

            for (int v = 0; v < vertices; v++) {

                if (weight[u][v] != 0 &&
                        !mstSet[v] &&
                        weight[u][v] < key[v]) {

                    parent[v] = u;
                    key[v] = weight[u][v];
                }
            }
        }

        String[] cities = {
                "Hyderabad",
                "Warangal",
                "Vizag",
                "Araku",
                "Vijayawada"
        };

        int totalCost = 0;

        System.out.println("\nMinimum Spanning Tree:");

        for (int i = 1; i < vertices; i++) {

            System.out.println(
                    cities[parent[i]]
                            + " -> "
                            + cities[i]
                            + " = "
                            + weight[i][parent[i]]
                            + " km");

            totalCost += weight[i][parent[i]];
        }

        System.out.println("Total MST Cost = " + totalCost + " km");
    }

    public static void main(String[] args) {

        CO3 graph = new CO3(5);

        graph.addEdge(0, 1, 120);
        graph.addEdge(0, 2, 300);
        graph.addEdge(1, 2, 100);
        graph.addEdge(1, 3, 200);
        graph.addEdge(2, 4, 150);
        graph.addEdge(3, 4, 180);

        System.out.println("=== TOURPLAN CITY CONNECTIVITY SYSTEM ===\n");

        graph.BFS(0);

        graph.DFS(0);

        graph.primMST();
    }
}