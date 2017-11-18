import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    static Tree<Character, Integer> t = new Tree<>();

    public static void main(String[] args) {
        String str = read();
        justDoIt(str);

    }

    public static String  read(){
        try {
            File input = new File("input.txt");
            BufferedReader br = new BufferedReader(new FileReader(input));

            return br.readLine();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    return null;
    }

    public static void justDoIt(String str) {
        for (char c : str.toCharArray()){
            if(t.isContains(c)) {
                t.put(c, 1 + t.get(c).value);
            }
            else
                t.put(c, 1);
        }

        t.display();
    }
}

