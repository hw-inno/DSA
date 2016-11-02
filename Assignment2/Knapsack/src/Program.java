import java.io.*;

public class Program {
    public static void main(String args[])
    {
        Box box = readFromFile();
        int answer = Knapsack.bestCost(box.limit, box.weight, box.cost);
        writeToFile(answer);
    }

    public static Box readFromFile(){
        Box box = new Box();
        try {
            int i = 0;
            File input = new File("input.txt");
            BufferedReader br = new BufferedReader(new FileReader(input));

            String line = br.readLine();
            String[] parts = line.split("\\s");
            box.weight = new int[parts.length];
            for (String token : parts) {
                box.weight[i++] = Integer.parseInt(token);
            }
            i = 0;

            line = br.readLine();
            parts = line.split("\\s");
            box.cost = new int[parts.length];
            for (String token : parts) {
                box.cost[i++] = Integer.parseInt(token);
            }

            line = br.readLine();
            box.limit = Integer.parseInt(line);
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return box;
    }

    public static void writeToFile(int answer){
        try{
            PrintWriter writer = new PrintWriter("output.txt");
            writer.write(String.valueOf(answer));
            writer.close();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}

