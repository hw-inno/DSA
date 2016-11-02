public interface IGraph {
    int numberOfVertices();
    int numberOfEdges();
    void addEdge(int i, int j);
    //void addEdge(int i, int j, int w);
    void removeEdge(int i, int j);
}

/*----------------------- Constructors -----------------------*//*
    public AbstractGraph(int v){
        if(v < 0){
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        }
        matrix = new int[v][v];
        vertices = new int[v];
    }

    public void addEdge(int i, int j, int w){
        if(validate(i, j))
            throw new IllegalArgumentException("Indices must be in a range [0, " + V + ")");
        matrix[i][j] = w;
        E++;
    }
    *//*----------------------- End of Constructors -----------------------*/
