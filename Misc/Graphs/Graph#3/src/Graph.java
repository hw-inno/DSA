import com.sun.javafx.geom.IllegalPathStateException;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiPredicate;
/* --- Simple graph with generic vertices and edges without self-loops */
/* -- Stores vertices in sorted fashion in order to find vertex fast */


public class Graph<V extends  Comparable<V>, E>{
    private V[] vertices;
    private E[][] matrix;
    private int size;
    private int numOfEdges; // how many edges

    private int find(V a){
        int k = Arrays.binarySearch(this.vertices, a);
        return k;
    }

    /*----------------------- Constructors -----------------------*/
    @SuppressWarnings("unchecked")
    public Graph(V... vertices){
        this.size = vertices.length;
        this.matrix = (E[][]) new Object[size][size];
        //this.vertices = (V[]) new Object[size];
        //this.matrix = (E[][]) Array.newInstance(edgeType.getClass().getComponentType(), size, size);
        this.vertices = (V[]) Array.newInstance(vertices.getClass().getComponentType(), size);
        for (int i = 0; i < size ; i++)
            this.vertices[i] = vertices[i];
        Arrays.sort(this.vertices);
    }
    /*----------------------- End of Constructors -----------------------*/

    /* ----------------------- Interface ---------------------- */
    public void addEdge(V from, V to){ addEdge(from, to, (E)(Integer)1);}
    public void addEdge(V from, V to, E w){
        int i, j;
        i = find(from);
        j = find(to);

        matrix[i][j] = w;
        numOfEdges++;
    }
    public E getEdge(V from, V to){
        int i, j;
        i = find(from);
        j = find(to);
        if(matrix[i][j] != null)
            return matrix[i][j];
        else
            throw new IllegalPathStateException();
    }
    public E removeEdge(V from, V to){
        E temp;
        int i, j;
        i = find(from);
        j = find(to);

        temp = matrix[i][j];
        matrix[i][j] = null;
        numOfEdges--;
        return temp;
    }
    public int numberOfVertices(){ return size; }
    public int numberOfEdges(){ return numOfEdges; }
    public boolean isContains(V a){
        return find(a) != -1;
    }
    public boolean areAdjacent(V from, V to){
        int i,j;
        i = find(from);
        j = find(to);
        return matrix[i][j] != null;
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
                if(matrix[i][j] != null){
                    sb.append(vertices[j] + "");
                    E road = getEdge(vertices[i], vertices[j]);
                    if( road != null)
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

    /* ----------------- Graph creating methods -------------------- */
    public static Graph<String, Integer> loadGraph(String fileName){
        Graph<String, Integer> g = null;
        BufferedReader br = null;
        List<String> list = new LinkedList<>();
        try {
            File file = new File(fileName);
            br = new BufferedReader(new FileReader(file));
            String[] vertices = br.readLine().split(" ");
            g = new Graph<>(vertices);

            String[] edges = br.readLine().split(" ");
            for (int i = 0; i < edges.length; i+= 3) {
                g.addEdge(edges[i], edges[i+1], Integer.parseInt(edges[i+2]));
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
    public static Graph<String, Integer> chessGraph(BiPredicate<String, String> p) {
        String[] cells = new String[64];
        int i = 0;
        for (char a = 'A'; a <= 'H'; a++) {
            for (byte b = 1; b <= 8; b++) {
                cells[i++] = "" + a + b;
            }
        }
        Graph<String, Integer> g = new Graph<>(cells);
        for (String cell1 : cells)
            for (String cell2 : cells) {
                if (p.test(cell1, cell2))
                    g.addEdge(cell1, cell2, 1);
            }
        return g;
    }
    /* -------------- End of Graph creating methods ---------------- */


    /* ------------------------- Graph Traversals -------------------- */
    /* Recursive dfs */
    public void dfsRecur(V vertex){
        boolean[] visited = new boolean[size];
        _dfsRecur(vertex, visited);
    }
    private void _dfsRecur(V vertex, boolean[] visited){
        System.out.print(vertex.toString() + " ");
        int i = find(vertex);
        visited[i] = true;

        List<V> list = neighbours(vertex);
        for(V v : list){
            i = find(v);
            if(!visited[i])
                _dfsRecur(v, visited);
        }
    }

    /* Iterative dfs */
    public void dfsIter(V vertex) {
        boolean[] visited = new boolean[size];
        Stack<V> stack = new Stack<V>();
        stack.add(vertex);
        int i = find(vertex);
        visited[i] = true;

        while (!stack.isEmpty()){
            V elem = stack.pop();
            System.out.print(elem.toString() + " ");
            List<V> list = neighbours(elem);
            for(int j = 0; j < list.size(); j++){
                V node = list.get(j);
                int k = find(node);
                if(node != null && !visited[k]){
                    stack.add(node);
                    visited[k] = true;
                }
            }
        }

    }

    public void bfs(V vertex){
        Queue<V> q = new ArrayDeque<>();
        boolean[] visited = new boolean[size];
        q.add(vertex);
        int i = find(vertex);
        visited[i] = true;

        while (!q.isEmpty()){
            V elem = q.poll();
            System.out.print(elem.toString() + " ");

            for(V v : neighbours(elem)){
                i = find(v);
                if(v != null && !visited[i]){
                    q.add(v);
                    visited[i] = true;
                }
            }
        }
        /*for(boolean bool : visited)
            if(bool == false) System.out.println("Not connected graph");*/
    }

    /* ------------------------- End of Graph Traversals -------------------- */
}