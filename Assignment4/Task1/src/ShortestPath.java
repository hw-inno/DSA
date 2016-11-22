import java.util.*;

public class ShortestPath {
    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> visited;
    private Set<Vertex> notVisited;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Float> distance;
    private Graph g;

    private void findMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex v : adjacentNodes) {
            if (getShortestDistance(v) > getShortestDistance(node) + getDistance(node, v)) {
                distance.put(v, getShortestDistance(node) + getDistance(node, v));
                predecessors.put(v, node);
                notVisited.add(v);
            }
        }
    }
    private Float getDistance(Vertex node, Vertex v) {
        for (Edge edge : edges) {
            if (edge.getOrigin().equals(node) && edge.getDest().equals(v)) {
                return (Float) edge.getWeight();
            }
        }
        throw new NoSuchElementException();
    }

    // Returns list of adjacent vertices, which are unvisited
    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getOrigin().equals(node) && !isVisited(edge.getDest())) {
                neighbors.add(edge.getDest());
            }
        }
        return neighbors;
    }

    // Finds the closest vertex to the source from unvisited set
    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            }
            else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum))
                    minimum = vertex;
            }
        }
        return minimum;
    }
    private boolean isVisited(Vertex vertex) {
        return visited.contains(vertex);
    }
    private Float getShortestDistance(Vertex destination) {
        Float d = distance.get(destination);
        if (d == null) {
            return Float.MAX_VALUE;
        }
        else {
            return d;
        }
    }

    // Public methods
    public ShortestPath(Graph graph) {
        this.nodes = new ArrayList<Vertex>(graph.getVertices());
        this.edges = new ArrayList<Edge>(graph.getEdges());
        this.g = graph;
    }
    public List<Vertex> buildShortestPath(Vertex v1, Vertex v2){
        execute(v1);
        return getPath(v2);
    }
    public String calculateWeight(List<Vertex> path, Float f){
        Float time = 0f;
        Float cost = 0f;


        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < path.size() - 1; i++){
            Edge edge = g.getEdge(path.get(i).getName(), path.get(i+1).getName());
            time += edge.getTime();
            cost += edge.getCost();
        }
        cost *= f;
        return String.format("%.1f %.1f", time, cost);
    }
    public void execute(Vertex source) {
        visited = new HashSet<Vertex>();
        notVisited = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Float>();
        predecessors = new HashMap<Vertex, Vertex>();
        distance.put(source, 0f);
        notVisited.add(source);
        while (notVisited.size() > 0) {
            Vertex node = getMinimum(notVisited);
            visited.add(node);
            notVisited.remove(node);
            findMinimalDistances(node);
        }
    }

    // Returns shortest path of vertices from source to 'to'
    public List<Vertex> getPath(Vertex to) {
        List<Vertex> path = new ArrayList<>();
        Vertex step = to;

        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }
}