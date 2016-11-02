public class UndirectedUnweightedGraph extends AbstractGraph {
    /*----------------------- Constructors -----------------------*/
    public UndirectedUnweightedGraph(int v){
        if(v < 0){
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        }
        matrix = new int[v][v];
        vertices = new int[v];
    }
    /*----------------------- End of Constructors -----------------------*/
}
