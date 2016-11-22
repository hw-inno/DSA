import java.util.*;

public class MaxFlow {
    private int size;
    private List<Edge> edgeList = new LinkedList<>();
    private List<Vertex> vertexList = new LinkedList<>();
    private List<Vertex> minCutVertexList = new LinkedList<>();
    Graph graph;

    public MaxFlow(Graph g) {
        this.graph = g;
        this.size = g.getSize();
        edgeList = g.getAllEdges();
        vertexList = Arrays.asList(g.getAllVertices());
    }

    private List<Edge> bfs(Vertex source, Vertex sink) {
        boolean hasPathTo = false;
        Queue<Vertex> queue = new ArrayDeque<>();
        queue.add(source);
        minCutVertexList.clear();
        minCutVertexList.add(source);

        while (queue.size() > 0) {
            Vertex v = queue.poll();
            for (Object edge : graph.getIncidentEdges(v)) {
                Vertex dest = ((Edge)edge).getDest();
                if (minCutVertexList.contains(dest) || ((Edge) edge).remainCapacity() == 0)
                    continue;
                dest.edgeTo = (Edge)edge;
                minCutVertexList.add(dest);
                if (dest.equals(sink)) {
                    hasPathTo = true;
                    break;
                }
                queue.add(dest);
            }
        }

        List<Edge> edgePathList = new LinkedList<>();
        if (hasPathTo) {
            Vertex vertex = sink;
            while (!vertex.equals(source)) {
                edgePathList.add(vertex.edgeTo);
                vertex = vertex.edgeTo.getOrigin();
            }
        }
        return edgePathList.size() > 0 ? edgePathList : null;
    }

    protected int fordFulkerson(String sourceName, String sinkName) {
        Float maxFlow = 0f;
        Vertex source = graph.getVertex(sourceName);
        Vertex sink = graph.getVertex(sinkName);

        List<Edge> augmentPath;
        while ((augmentPath = bfs(source, sink)) != null) {
            Float maxCapacity = Float.MAX_VALUE;
            for (Edge e : augmentPath)
                maxCapacity = Math.min(e.remainCapacity(), maxCapacity);
            for (Edge e : augmentPath) {
                e.increaseFlow(maxCapacity);
                for (int i = 0; i < edgeList.size(); i++) {
                    if (edgeList.get(i).isReversed(e)){
                        edgeList.get(i).decreaseFlow(maxCapacity);
                        break;
                    }
                }
            }
            maxFlow += maxCapacity;
        }
        return  Math.round(maxFlow);
    }





}