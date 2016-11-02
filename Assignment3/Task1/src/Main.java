import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static AVLTree<Character, Integer> t = new AVLTree<>();
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        readFromFile();
        justDoIt();
        writeToFile();
    }

    public static void justDoIt() {
        for(String s : list) {
            for (char c : s.toCharArray()) {
                if (t.isContains(c)) {
                    t.set(c, 1 + t.get(c).value);
                } else
                    t.put(c, 1);
            }
        }
        t.traverseDeleter(t.root); // Clean tree from ":;<=>?@[\\]^_` .*-"
        t.traverse(t.root); // Traverse tree and write output to StringBuilder
    }

    public static void writeToFile(){
        try{
            PrintWriter writer = new PrintWriter("output.txt");
            writer.write(t.sb.toString());
            writer.close();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void readFromFile(){
        try {
            File input = new File("input.txt");
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line;
            while((line = br.readLine()) != null){
                list.add(line.toLowerCase());
            }
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}

