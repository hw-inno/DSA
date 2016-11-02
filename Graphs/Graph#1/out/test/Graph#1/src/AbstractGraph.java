import java.util.Map;

public class AbstractGraph<V>{
    protected Map<Integer, V> vertex;
    protected int[] vertices;
    protected int[][] matrix;
    protected int V; // number of vertices
    protected int E; // number of edges

    protected Boolean validate(int i, int j){
        if(i >= V || j >= V || i < 0 || j < 0)
            return false;
        return true;
    }

    public int numberOfVertices(){ return V; }
    public int numberOfEdges(){ return E; }
    public void addEdge(int i, int j){
        if(validate(i, j))
            throw new IllegalArgumentException("Indices must be in a range [0, " + V + ")");
        matrix[i][j] = 1;
        E++;
    }
    public void removeEdge(int i, int j){
        if(validate(i, j))
            throw new IllegalArgumentException("Indices must be in a range [0, " + V + ")");
        matrix[i][j] = 0;
        E--;
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
