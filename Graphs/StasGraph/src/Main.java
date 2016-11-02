import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static Graph<String, Integer> g;
    static List<String> listV = new LinkedList<>();
    static Queue<String> listE = new LinkedList<>();

    public static void main(String[] args) {
        readFromFile();
        g = new Graph<>(_createGraph());
        _createEdges();
        g.save("output.txt");
    }

    public static void readFromFile(){
        BufferedReader br = null;
        try {
            File input = new File("Distances.txt");
            br = new BufferedReader(new FileReader(input));
            String line;
            /*while ((line = br.readLine()) != null) {
                listV.add(line);
            }*/
            line = br.readLine();
            for(String s : line.split(" ")){
                listV.add(s);
            }

            line = br.readLine();
            for(String s : line.split(" ")){
                listE.add(s);
            }

        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String[] _createGraph(){
        String[] s = new String[listV.size()];
        for (int i = 0; i < listV.size(); i++) {
            s[i] = listV.get(i);
        }
        return s;
    }
    public static void _createEdges(){
        while (!listE.isEmpty()){
            //System.out.printf("/%s/ /%s/ /%s/\n", listE.poll(), listE.poll(), listE.poll());
            g.setEdge(listE.poll(), listE.poll(), Integer.parseInt(listE.poll()));
        }

    }

    public static void writeToFile(String s) {
        BufferedWriter bw = null;
        try {
            File output = new File("output.txt");
            bw = new BufferedWriter(new FileWriter((output.getAbsoluteFile())));
            //PrintWriter writer = new PrintWriter("output.txt");
            bw.write(s);
            //bw.write("");

            //writer.close();
            bw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

/*//knight
        Graph<String, Integer> knight = Graph.chessGraph((String c1, String c2) -> {
            if (Math.abs((int) (c1.charAt(0)) - (int) (c2.charAt(0))) == 1 &&
                Math.abs((int) (c1.charAt(1)) - (int) (c2.charAt(1))) == 2) {
                return true;
            } else
            if (Math.abs((int) (c1.charAt(0)) - (int) (c2.charAt(0))) == 2 &&
                Math.abs((int) (c1.charAt(1)) - (int) (c2.charAt(1))) == 1) {
                return true;
            }
            return false;
        });

        // bishop
        Graph<String, Integer> bishop = Graph.chessGraph((String c1, String c2) -> {
            if (Math.abs((int) (c1.charAt(0)) - (int) (c2.charAt(0))) ==
                Math.abs((int) (c1.charAt(1)) - (int) (c2.charAt(1)))) {
                return true;
            }
            return false;
        });

        //bishop.save("D:/bishop.txt");
        //knight.save("D:/knight.txt");
        System.out.println(bishop.areAdjacent("A1", "H8"));
        System.out.println(bishop.areAdjacent("A1", "H7"));
        System.out.println(knight.areAdjacent("C4", "B6"));
        System.out.println(knight.areAdjacent("C3", "B6"));

       // bishop.dfs("A1");
        knight.dfs("D4");*/