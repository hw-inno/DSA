public class Edge<EdgeType> {
    private Vertex from, to;
    private EdgeType weight;

    public Edge(Vertex origin, Vertex destination, EdgeType weight) {
        this.from = origin;
        this.to = destination;
        this.weight = weight;
    }


    public Vertex getOrigin() {
        return from;
    }

    public Vertex getDest() {
        return to;
    }

    public EdgeType getWeight() {
        return weight;
    }


    public void setOrigin(Vertex from) {
        this.from = from;
    }

    public void setDest(Vertex to) {
        this.to = to;
    }

    public void setWeight(EdgeType weight) {
        this.weight = weight;
    }
}
