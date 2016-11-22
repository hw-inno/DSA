import java.io.*;
import java.util.LinkedList;
import java.util.List;
public class Main{
    public static void main(String[] args) {
        List<String> input = loadInputTXT();
        StringBuilder sb = new StringBuilder();

        input.forEach(item ->{
            Graph<Float> g = Graph.loadRussiaTXT();
            MaxFlow mf = new MaxFlow(g);

            String[] s = item.split(" ");
            String source = s[0];
            String sink = s[1];

            sb.append(source + " " + sink + " ");
            sb.append(mf.fordFulkerson(source, sink));
            sb.append("\n");
        });
        writeToFile(sb.toString());
    }

    public static List<String> loadInputTXT(){
        BufferedReader br = null;
        List<String> list = new LinkedList<>();
        try {
            File file = new File("input.txt");
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (br != null)
                br.close();
                return list;
            } catch (IOException e) {
                e.printStackTrace();
            }
         }
        return list;
    }
    public static void writeToFile(String s) {
        try {
            PrintWriter writer = new PrintWriter("output.txt");
            writer.write(s);
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

