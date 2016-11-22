import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main{
    public static void main(String[] args) {
        //knight
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

        //knight.save();
        //knight.save("D:/knight.txt");
        /*System.out.println(bishop.areAdjacent("A1", "H8"));
        System.out.println(bishop.areAdjacent("A1", "H7"));
        System.out.println(knight.areAdjacent("C4", "B6"));
        System.out.println(knight.areAdjacent("C3", "B6"));*/

        Graph<String, Integer> city = Graph.loadGraph("Distances.txt");
        city.save();
        city.bfs("Елец");
        //city.dfs("Тула");
        //city.save();
        //city.dfs("Тула");
        //knight.dfs("D4");
        /*System.out.println("Iter: ");
        bishop.dfsIter("D4");
        System.out.println("\nRecur: ");
        bishop.dfsRecur("D4");*/
        /*Graph<String, Integer> g = new Graph<>("40", "20", "50", "10", "30", "60", "70", "55");
        g.addEdge("40", "20");
        g.addEdge("40", "10");
        g.addEdge("20", "10");
        g.addEdge("20", "30");
        g.addEdge("20", "60");
        g.addEdge("20", "50");
        g.addEdge("50", "70");
        g.addEdge("60", "70");
        g.addEdge("10", "30");
        g.addEdge("30", "60");
        g.bfs("40");*/

        //g.save();

        /*System.out.println("Bishop bfs: ");
        bishop.bfs("D4");
        System.out.println("\nIter: ");
        bishop.dfsIter("D4");
        System.out.println("\nRecur: ");
        bishop.dfsRecur("D4");*/

    }

    public static void saveInfo(String s){
        BufferedWriter bw = null;
        try {
            File output = new File("output.txt");
            bw = new BufferedWriter(new FileWriter((output.getAbsoluteFile())));
            bw.write(s);
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
