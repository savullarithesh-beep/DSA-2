public import java.util.*;

public class CO5 {

    static class Edge {

        int destination;
        int distance;

        Edge(int destination,
             int distance) {

            this.destination = destination;
            this.distance = distance;
        }
    }

    static void dfs(int city,
                    boolean visited[],
                    ArrayList<Edge>[] graph,
                    String cities[]) {

        visited[city] = true;

        System.out.print(
                cities[city] + " ");

        for (Edge edge : graph[city]) {

            if (!visited[edge.destination]) {

                dfs(edge.destination,
                        visited,
                        graph,
                        cities);
            }
        }
    }

    static void greedyPackageSelection() {

        int budget = 5000;

        String packages[] = {
                "Araku",
                "Vizag",
                "Warangal",
                "Hyderabad"
        };

        int cost[] = {
                1500,
                2500,
                1000,
                3000
        };

        System.out.println(
                "\nSelected Travel Packages:");

        for (int i = 0;
             i < packages.length;
             i++) {

            if (budget >= cost[i]) {

                budget -= cost[i];

                System.out.println(
                        packages[i]
                        + " -> ₹"
                        + cost[i]);
            }
        }
    }

    static int itineraryDP(int days[],
                           int n,
                           int maxDays) {

        int dp[][] =
                new int[n + 1][maxDays + 1];

        for (int i = 1;
             i <= n;
             i++) {

            for (int j = 1;
                 j <= maxDays;
                 j++) {

                if (days[i - 1] <= j)

                    dp[i][j] =
                            Math.max(
                                    days[i - 1]
                                            + dp[i - 1][j - days[i - 1]],
                                    dp[i - 1][j]);

                else

                    dp[i][j] =
                            dp[i - 1][j];
            }
        }

        return dp[n][maxDays];
    }

    public static void main(String[] args) {

        String cities[] = {
                "Hyderabad",
                "Warangal",
                "Vizag",
                "Araku",
                "Vijayawada"
        };

        int n = cities.length;

        ArrayList<Edge>[] graph =
                new ArrayList[n];

        for (int i = 0;
             i < n;
             i++) {

            graph[i] =
                    new ArrayList<>();
        }

        graph[0].add(new Edge(1,120));
        graph[1].add(new Edge(2,100));
        graph[2].add(new Edge(3,150));
        graph[3].add(new Edge(4,180));

        System.out.println(
                "=== TOURPLAN ROUTE RECOMMENDATION SYSTEM ===\n");

        System.out.println(
                "Tourist Route Traversal:");

        boolean visited[] =
                new boolean[n];

        dfs(0,
                visited,
                graph,
                cities);

        System.out.println();

        greedyPackageSelection();

        int tripDays[] = {
                2,3,4,5
        };

        int result =
                itineraryDP(
                        tripDays,
                        tripDays.length,
                        7);

        System.out.println(
                "\nOptimized Itinerary Days = "
                        + result);

        System.out.println(
                "\nCO5 Execution Completed Successfully");
    }
} {
    
}