public class Edge<EdgeType> {
    private Vertex from, to;
    private EdgeType weight;
    private Float cost;
    private Float time;

    public Edge(Vertex origin, Vertex destination, EdgeType weight, Float cost, Float time) {
        this.from = origin;
        this.to = destination;
        this.weight = weight;
        this.cost = cost;
        this.time = time;
    }
    public Edge(Vertex origin, Vertex destination, EdgeType weight) {
        this.from = origin;
        this.to = destination;
        this.weight = weight;
    }


    public Vertex getOrigin() { return from; }
    public Vertex getDest() {
        return to;
    }
    public EdgeType getWeight() {
        return weight;
    }
    public Float getCost() {
        return cost;
    }
    public Float getTime() {
        return time;
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
    public void setCost(Float cost) {
        this.cost = cost;
    }
    public void setTime(Float time) {
        this.time = time;
    }
}
