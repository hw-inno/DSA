public class Edge<EdgeType> {
    private Vertex from, to;
    private EdgeType weight;
    private Float capacity;
    private Float flow = 0f;
    private Float dist;
    private Float time;
    private Float cost;

    public Edge(Vertex origin, Vertex destination, EdgeType weight, Float dist, Float time, Float cost) {
        this.from = origin;
        this.to = destination;
        this.weight = weight;
        this.dist = dist;
        this.time = time;
        this.cost = cost;
        this.capacity = 50 * dist / time;
    }
    public Edge(Vertex origin, Vertex destination, EdgeType weight) {
        this(origin, destination, weight, null, null, null);
    }


    public Vertex getOrigin() { return from; }
    public Vertex getDest() {
        return to;
    }
    public EdgeType getWeight() {
        return weight;
    }
    public Float getDist() {return (Float)dist; }
    public Float getCost() {
        return cost;
    }
    public Float getTime() {
        return time;
    }
    public Float getCapacity() { return capacity; }
    public Float getFlow() { return flow; }
    public Float remainCapacity() { return capacity - flow; }

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
    public void setFlow(Float flow) {this.flow = flow;}

    public void increaseFlow(Float flow){
        this.flow += flow;
        //this.reversed.flow -= flow;
    }
    public void decreaseFlow(Float flow){
        this.flow -= flow;
    }
    public boolean isReversed(Edge e){
        return e.getOrigin().equals(to) && e.getDest().equals(from);
    }
}
