public class Program {
    public static void main(String args[])
    {
        int val[] = new int[]{60, 100, 120};
        int wt[] = new int[]{10, 20, 45};
       /* int val[] = new int[]{57, 43, 10, 20, 88, 90, 71, 77, 96, 39, 72,
                52, 15, 83, 33, 42, 80, 36, 97, 21, 49, 43, 40, 56, 17,
                25, 52, 61, 1, 82, 87, 11, 22, 92, 35, 1, 22, 65, 25, 58,
                49, 51, 77, 15, 41, 64, 61, 87, 28, 52, 48, 73, 58, 99,
                27, 65, 10, 99, 45, 68, 31, 87, 36, 87
        };
        int wt[] = new int[]{46, 36, 9, 43, 51, 95, 23, 93, 15, 90, 43,
                95, 84, 96, 60, 2, 96, 54, 65, 73, 92, 20, 11, 28, 32,
                19, 86, 97, 43, 28, 68, 24, 98, 41, 21, 87, 82, 71, 97,
                66, 46, 14, 41, 2, 15, 58, 34, 5, 72, 26, 76, 29, 78,
                7, 46, 82, 39, 86, 9, 91, 2, 25, 44, 43
        };*/

        int  W = 50;


        long T0, T1;
      /*  T0 = System.currentTimeMillis();
        System.out.println("BruteForce: " + KnapsackBruteForce.DP(W, wt, val, n));
        T1 = System.currentTimeMillis();
        System.out.println("Time complexity: " + (T1 - T0));
        System.out.println();*/

        T0 = System.currentTimeMillis();
        System.out.println("Knapsack: " + Knapsack.DP(W, wt, val));
        T1 = System.currentTimeMillis();
        System.out.println("Time complexity: " + (T1 - T0));
    }

   static public String addSymbol(String input){
      return input.replaceAll(" ", ", ");
    }
}

