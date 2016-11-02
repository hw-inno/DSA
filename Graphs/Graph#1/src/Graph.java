import java.util.HashMap;

public class Graph<V>{
    private V[] vertices;
    private HashMap<V, Integer> addr;
    private int[][] matrix;
    private int size;
    private int E; // how many edges

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



    public int numberOfVertices(){ return size; }
    public int numberOfEdges(){ return E; }
    public boolean isContains(V a){
        return find(a) != -1;
    }

    public void addEdge(V from, V to, int w){
        int i, j;
        i = addr.get(from);
        j = addr.get(to);

        matrix[i][j] = w;
        matrix[j][i] = w;
        E++;
    }
    public void addEdge(V from, V to, String ws){
        addEdge(from, to, Integer.parseInt(ws));
    }
    public void removeEdge(V from, V to){
        int i, j;
        i = addr.get(from);
        j = addr.get(to);

        matrix[i][j] = -1;
        matrix[j][i] = -1;
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
                    sb.append(vertices[j] + " ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }


    /*public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj(v)) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }*/
}