import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static Graph<String, Integer> g;
    static List<String> listV = new LinkedList<>();
    static Queue<String> listE = new LinkedList<>();

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

        //bishop.save("D:/bishop.txt");
        //knight.save("D:/knight.txt");
        System.out.println(bishop.areAdjacent("A1", "H8"));
        System.out.println(bishop.areAdjacent("A1", "H7"));
        System.out.println(knight.areAdjacent("C4", "B6"));
        System.out.println(knight.areAdjacent("C3", "B6"));

        // bishop.dfs("A1");
        knight.dfs("D4");
    }

}

