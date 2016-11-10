import com.sun.javafx.geom.IllegalPathStateException;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
/* --- Simple graph without self-loops */


public class Graph<V>{
    private V[] vertices;
    private HashMap<V, Integer> addr;
    private int[][] matrix;
    private int size;
    private int numOfEdges; // how many edges

    /*----------------------- Constructors -----------------------*/
    public Graph(V[] vertices){
        size = vertices.length;
        matrix = new int[size][size];
        this.vertices = (V[]) new Object[size];
        this.addr = new HashMap<>();
        for (int i = 0; i < size ; i++) {
            this.vertices[i] = vertices[i];
            addr.put(this.vertices[i], i);
        }
    }
    /*----------------------- End of Constructors -----------------------*/

    private int find(V a){
        for (int i = 0; i < size; i++) {
            if(a.equals(vertices[i])){
                return i;
            }
        }
        return -1;
    }



    /* ----------------------- Interface ---------------------- */
    public int numberOfVertices(){ return size; }
    public int numberOfEdges(){ return numOfEdges; }
    public boolean isContains(V a){
        return find(a) != -1;
    }
    public boolean areAdjacent(V from, V to){
        int i,j;
        i = addr.get(from);
        j = addr.get(to);
        return matrix[i][j] != 0;
    }
    public List<V> neighbours(V node){
        List<V> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            if(areAdjacent(node, vertices[i])){
                list.add(vertices[i]);
            }
        }
        return list;
    }

    public void addEdge(V from, V to, int w){
        int i, j;
        i = addr.get(from);
        j = addr.get(to);

        matrix[i][j] = w;
        //matrix[j][i] = w;
        numOfEdges++;
    }
    public void addEdge(V from, V to, String ws){
        addEdge(from, to, Integer.parseInt(ws));
    }
    public void removeEdge(V from, V to){
        int i, j;
        i = addr.get(from);
        j = addr.get(to);

        matrix[i][j] = 0;
        //matrix[j][i] = 0;
        numOfEdges--;
    }
    public int getEdge(V from, V to){
        int i, j;
        i = addr.get(from);
        j = addr.get(to);
        if(matrix[i][j] != 0)
            return matrix[i][j];
        else
            throw new IllegalPathStateException();
    }

    public void printVertices(){
        for (V vertex : vertices){
            System.out.println(vertex);
        }
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("V : E\n");
        sb.append("-----\n");
        //sb.append(V + " " + E + "\n");
        for(int i = 0; i < size; i++){
            sb.append(vertices[i] + " : ");
            for(int j = 0; j < size; j++){
                if(matrix[i][j] != 0){
                    sb.append(vertices[j] + "");
                    int road = getEdge(vertices[i], vertices[j]);
                    if( road > 0)
                        sb.append("(" +  road + ") ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
    public void save(){
        BufferedWriter bw = null;
        try {
            File output = new File("output.txt");
            bw = new BufferedWriter(new FileWriter((output.getAbsoluteFile())));
            bw.write(this.toString());
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
    /* ------------------ End of Interface ------------------ */


    public static Graph<String> loadGraph(String fileName){
        Graph<String> g = null;
        BufferedReader br = null;
        List<String> list = new LinkedList<>();
        try {
            File file = new File(fileName);
            br = new BufferedReader(new FileReader(file));
            String[] vertices = br.readLine().split(" ");
            g = new Graph<>(vertices);

            String[] edges = br.readLine().split(" ");
            for (int i = 0; i < edges.length; i+= 3) {
                g.addEdge(edges[i], edges[i+1], edges[i+2]);
            }

        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                if (br != null)
                    br.close();
                return g;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return g;
    }
}