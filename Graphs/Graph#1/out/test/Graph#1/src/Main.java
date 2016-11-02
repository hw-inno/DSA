import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main{
    static Graph<String> g;
    static List<String> listV = new LinkedList<>();
    static Queue<String> listE = new LinkedList<>();

    public static void main(String[] args) {
        FileManipulator f = new FileManipulator();
        StringBuilder sb = new StringBuilder();
        readFromFile();
        g = new Graph<String>(_getVertices());

        //_getEdges();
        g.printVertices();
        f.writeToFile(g.toString());
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
    public static String[] _getVertices(){
        String[] str = new String[listV.size()];
        for (int i = 0; i < listV.size(); i++) {
            str[i] = listV.get(i);
        }
        return str;
    }
    public static void _getEdges(){
        int i = 0;
        while (!listE.isEmpty()) {

            System.out.println(i+=3);
            g.addEdge(listE.poll(), listE.poll(), listE.poll());
        }
    }
}
