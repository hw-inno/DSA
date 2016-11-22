import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Prim's algorithm

public class MST {
    private Float distance = 0f;       // total distance of MST
    private List<Edge> mst;     // edges in the MST
    private PQ<Float, Edge> pq;
    Graph graph;

    public MST(Graph graph) {
        this.graph = graph;
        mst = new LinkedList<>();
        pq = new PQ<Float, Edge>();

        Arrays.asList(graph.getAllVertices()).forEach(v ->{
            if(((Vertex)v).isMarked() == false){
                prim(graph, ((Vertex) v));
            }
        });
    }

    private void prim(Graph graph, Vertex s) {
        scan(graph, s);
        while (!pq.isEmpty()) {
            Edge edge = pq.poll().value;  // smallest edge in pq
            Vertex v1 = edge.getOrigin();
            Vertex v2 = edge.getDest();

            if(v1.isMarked() && v2.isMarked())
                continue;
            mst.add(edge);

            distance += edge.getDist();
            if( !v1.isMarked() )
                scan(graph, v1); // Include v1 to mst
            if( !v2.isMarked() )
                scan(graph, v2); // Include v2 to mst
        }
    }
    // add all edges e incident to v onto pq if the other endpoint has not yet been scanned
    private void scan(Graph graph, Vertex v) {
        v.setMarked();
        for (Object edge : graph.getIncidentEdges(v))
            if(((Edge)edge).getOther(v).isMarked() == false)
                pq.add(((Edge)edge).getDist(), (Edge)edge);
    }
    public List<Edge> edges() {return mst;}
}
