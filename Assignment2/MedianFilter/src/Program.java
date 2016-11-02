import java.io.*;
import java.util.Arrays;

public class Program {

    public static void main(String[] args) {
        Box box = new Box();
        box = readFromFile();
        filter(box);
    }

    public static Box readFromFile(){
        Box box = new Box();
        try {
            File input = new File("input.txt");
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line = br.readLine();
            String[] parts = line.split("\\s");
            box.arr = new int[parts.length];
            int i = 0;
            for (String token : parts) {
                box.arr[i++] = Integer.parseInt(token);
            }
            line = br.readLine();
            box.windowSize = Integer.parseInt(line);

        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return box;
    }
    public static void writeToFile(int[] res){
        try{
            PrintWriter writer = new PrintWriter("output.txt");
            for(int i = 0; i < res.length; i++){
                writer.write(res[i] + " ");
            }

            writer.close();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void filter(Box box){
        int i, j;
        int window = box.windowSize;
        int N = box.arr.length;
        int[] signal = new int[N+window-1];
        int[] result = new int[N];
        int[] sub = new int[window];


        for(i = window/2; i < N + window/2; i++){
            signal[i] = box.arr[i - window/2];
        }
        for(i = 0; i < window/2; i++){
            signal[i] = box.arr[0];
            signal[signal.length - window/2 + i] = box.arr[N-1];
        }

        for(i = 0; i < N; i++ ){
            for(j = 0; j < window; j++){
                sub[j] = signal[i+j];
            }
            insertionSort(sub);
            result[i] = sub[window/2];
        }

        writeToFile(result);
    }

    public static void insertionSort(int[] arr){
        int temp;
        for(int i = 1; i < arr.length; i++){
            temp = arr[i];
            for (int j = i; j > 0; j--){
                if(arr[j] < arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
                else break;
            }
        }
    }
}
