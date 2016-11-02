import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knapsack {
    static int max(int a, int b) { return (a > b)? a : b; }
    static int n;

    static int bestCost(int limit, int wt[], int val[]) {
        n = val.length;
        int bag[][] = new int[n+1][limit+1];

        for (int i = 0; i <= n ; i++) {
            for (int j = 0; j <= limit; j++) {
                if (i==0 || j==0)
                    bag[i][j] = 0;

                else if (wt[i-1] > j)
                    bag[i][j] = bag[i-1][j];

                else {
                    bag[i][j] = max(val[i - 1] + bag[i - 1][j - wt[i - 1]], bag[i - 1][j]);
                }
            }
        }

        /* calculating proper item order */
        int w = limit-1;
        List<Integer> order = new ArrayList<>();

        for(int i = n-1; i >= 0; i--){
            if(w - wt[i] + 1 >= 0 &&
                    bag[i+1][w+1] - bag[i][w - wt[i] + 1] == val[i]){
                order.add(i+1);
                w -= wt[i];
            }
        }

        System.out.println("Item's number: " + Arrays.toString(order.toArray()));
        System.out.println("Best value: " + bag[n][limit]);
        return bag[n][limit];
    }
}
