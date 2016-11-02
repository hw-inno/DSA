import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileManipulator {
    private static List<String> list = new LinkedList<>();

    public static List<String> readFromFile(){
        BufferedReader br = null;
        try {
            File input = new File("input.txt");
            br = new BufferedReader(new FileReader(input));
            String line;
            /*while ((line = br.readLine()) != null) {
                listV.add(line);
            }*/
            line = br.readLine();
            for(String s : line.split(" ")){
                list.add(s);
            }
            line = br.readLine();


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
            return list;
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