import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class Main{
    public static void main(String[] args) {
        Graph<Float> graph = Graph.loadRussiaTXT();
        MST mst = new MST(graph);
        Graph<Float> cutted = new Graph<>(mst.edges(), graph.getAllVertices()); // Min Spanning Tree of 'graph'
        ShortestPath path = new ShortestPath(cutted);

        List<String> input = loadInputTXT();
        StringBuilder sb = new StringBuilder();

        input.forEach(item ->{
            String[] s = item.split(" ");
            Vertex v1 = cutted.getVertex(s[0]);
            Vertex v2 = cutted.getVertex(s[1]);
            Float f = Float.parseFloat(s[2]);

            List<Vertex> list = path.buildShortestPath(v1, v2);

            sb.append(v1 + " " + v2 + " ");
            sb.append(f + " ");
            sb.append(path.calculateWeight(list, f));
            sb.append("\n");
        });
         writeToFile(sb.toString());

        graph.serialize();
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

