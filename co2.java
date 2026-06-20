import java.util.*;

public class CO2 {

    static int[] tree;
    static int n;

    static void build(int[] prices, int node, int start, int end) {

        if (start == end) {
            tree[node] = prices[start];
        } else {

            int mid = (start + end) / 2;

            build(prices, 2 * node, start, mid);
            build(prices, 2 * node + 1, mid + 1, end);

            tree[node] = Math.min(tree[2 * node],
                                  tree[2 * node + 1]);
        }
    }

    static int rangeMinQuery(int node,
                             int start,
                             int end,
                             int left,
                             int right) {

        if (right < start || left > end)
            return Integer.MAX_VALUE;

        if (left <= start && end <= right)
            return tree[node];

        int mid = (start + end) / 2;

        int q1 = rangeMinQuery(
                2 * node,
                start,
                mid,
                left,
                right);

        int q2 = rangeMinQuery(
                2 * node + 1,
                mid + 1,
                end,
                left,
                right);

        return Math.min(q1, q2);
    }

    static void update(int node,
                       int start,
                       int end,
                       int index,
                       int value) {

        if (start == end) {
            tree[node] = value;
        } else {

            int mid = (start + end) / 2;

            if (index <= mid)
                update(2 * node,
                       start,
                       mid,
                       index,
                       value);
            else
                update(2 * node + 1,
                       mid + 1,
                       end,
                       index,
                       value);

            tree[node] = Math.min(tree[2 * node],
                                  tree[2 * node + 1]);
        }
    }

    public static void main(String[] args) {

        String[] hotels = {
                "Hotel Paradise",
                "Green Valley Resort",
                "Royal Stay",
                "Ocean View",
                "Hilltop Inn",
                "City Comfort",
                "Sunrise Residency"
        };

        int[] prices = {
                2500,
                1800,
                3200,
                1500,
                2800,
                2100,
                1700
        };

        n = prices.length;
        tree = new int[4 * n];

        build(prices, 1, 0, n - 1);

        System.out.println("=== TOURPLAN HOTEL PRICE QUERY ===\n");

        System.out.println("Hotels and Prices:");

        for (int i = 0; i < n; i++) {
            System.out.println(
                    hotels[i] + " -> ₹" + prices[i]);
        }

        int minPrice =
                rangeMinQuery(1,
                              0,
                              n - 1,
                              1,
                              5);

        System.out.println(
                "\nMinimum Hotel Price from index 1 to 5: ₹"
                        + minPrice);

        System.out.println(
                "\nUpdating Ocean View price from ₹1500 to ₹1400");

        prices[3] = 1400;

        update(1,
               0,
               n - 1,
               3,
               1400);

        minPrice =
                rangeMinQuery(1,
                              0,
                              n - 1,
                              1,
                              5);

        System.out.println(
                "New Minimum Hotel Price from index 1 to 5: ₹"
                        + minPrice);

        System.out.println(
                "\nSegment Tree Query Executed Successfully");
    }
}