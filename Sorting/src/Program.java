import java.util.Comparator;
import java.util.Random;

public class Program {
    public static void main(String[] args) {
        int size = 10000;
        int ITERS = 10;
        long time = 0, tx = 0;
        int arr[] = new int[size];
        Sorter sort = new Sorter();


        for(int iter = 0; iter < ITERS; iter++) {
           //fillRandom(arr);
            //fillSorted(arr);
            fillReverseSorted(arr);
            tx = now();
            sort.quickSort(arr, 0, size-1);
            time += now() - tx;
            System.out.print((now() - tx) + " ");
        }
        time /= ITERS;
        System.out.println("\nTime sort: " + time);
    }

    static public void print(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    static public void fillRandom(int[] arr){
        Random rand = new Random(System.nanoTime());
        int n = arr.length;
        for(int i = 0; i < arr.length; i++){
            arr[i] = rand.nextInt(n+n);
        }
    }
    static public void fillSorted(int[] arr){
        int n = arr.length;
        for(int i = 0; i < arr.length; i++){
            arr[i] = i;
        }
    }
    static public void fillReverseSorted(int[] arr){
        int n = arr.length;
        for(int i = 0; i < arr.length; i++){
            arr[i] = n - i - 1;
        }
    }
    public static long now() {
        return System.currentTimeMillis();
        //return System.nanoTime();
    }
}
