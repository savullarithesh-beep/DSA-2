import java.util.*;

public class CO4 {

    // Heap Sort
    static void heapSort(int arr[]) {

        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {

            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    static void heapify(int arr[],
                        int n,
                        int i) {

        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n &&
                arr[left] > arr[largest])
            largest = left;

        if (right < n &&
                arr[right] > arr[largest])
            largest = right;

        if (largest != i) {

            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    // Greedy Knapsack
    static void knapsack() {

        int budget = 5000;

        String places[] = {
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
                "\nSelected Tourist Packages:");

        for (int i = 0;
             i < places.length;
             i++) {

            if (budget >= cost[i]) {

                budget -= cost[i];

                System.out.println(
                        places[i]
                                + " Cost = ₹"
                                + cost[i]);
            }
        }
    }

    // LCS
    static int LCS(String a,
                   String b) {

        int m = a.length();
        int n = b.length();

        int dp[][] =
                new int[m + 1][n + 1];

        for (int i = 1;
             i <= m;
             i++) {

            for (int j = 1;
                 j <= n;
                 j++) {

                if (a.charAt(i - 1)
                        == b.charAt(j - 1))

                    dp[i][j] =
                            dp[i - 1][j - 1] + 1;

                else

                    dp[i][j] =
                            Math.max(
                                    dp[i - 1][j],
                                    dp[i][j - 1]);
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {

        int popularity[] = {
                95, 80, 75, 90, 85
        };

        System.out.println(
                "=== TOURPLAN PACKAGE RANKING SYSTEM ===\n");

        System.out.println(
                "Popularity Scores Before Sorting:");

        System.out.println(
                Arrays.toString(popularity));

        heapSort(popularity);

        System.out.println(
                "\nPopularity Scores After Heap Sort:");

        System.out.println(
                Arrays.toString(popularity));

        knapsack();

        String tourist1 = "ARAKUVIZAG";
        String tourist2 = "ARAKUWARANGAL";

        int result =
                LCS(tourist1,
                        tourist2);

        System.out.println(
                "\nLCS Length = "
                        + result);

        System.out.println(
                "\nCO4 Execution Completed Successfully");
    }
}