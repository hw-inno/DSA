// Directed/Undirected, without parallel edges, without self loops

import java.io.*;
import java.util.*;

public class Graph<EdgeType> {
    private Map<String, Vertex> vertices = new HashMap<>();
    private Vertex[] arr_vert;
    private Edge[][] matrix;
    private int size;

    public Graph( Vertex... vertices) {
        this.size = vertices.length;
        matrix = new Edge[size][size];
        this.arr_vert = vertices;
        for (Vertex v : vertices) {
            this.vertices.put(v.getName(), v);
        }
    }
    public Graph(List<Edge> edges, Vertex[] vs){
        this.size = vs.length;
        matrix = new Edge[size][size];
        this.arr_vert = vs;
        for (Vertex v : vs) {
            this.vertices.put(v.getName(), v);
        }
        edges.forEach(edge -> {
            int i = edge.getOrigin().getId();
            int j = edge.getDest().getId();
            matrix[i][j] = edge;
            // edge1 is reversed edge
            Edge edge1 = new Edge(edge.getDest(), edge.getOrigin(), edge.getWeight(), edge.getDist(), edge.getTime(), edge.getCost());
            matrix[j][i] = edge1;
        });

    }
    public int getSize(){ return size; }

    public Edge addEdge(String from, String to, EdgeType weight) {
        return addEdge(from, to, weight, null, null, null);
    }
    public Edge addEdge(String from, String to, EdgeType weight, Float dist, Float time, Float cost) {
        Vertex v1 = vertices.get(from);
        Vertex v2 = vertices.get(to);

        Edge newEdge = new Edge(v1, v2, weight, dist, time, cost);
        matrix[v1.getId()][v2.getId()] = newEdge;
        return newEdge;
    }

    public void addBothEdge(String from, String to, EdgeType weight, Float dist, Float time, Float cost){
        addEdge(from, to, weight, dist, time, cost);
        addEdge(to, from, weight, dist, time, cost);
    }
    public void addBothEdge(String from, String to, EdgeType weight){
        addEdge(from, to, weight, null, null, null);
        addEdge(to, from, weight, null, null, null);
    }

    public void removeEdge(String from, String to) {
        Vertex v1 = vertices.get(from);
        Vertex v2 = vertices.get(to);

        matrix[v1.getId()][v2.getId()] = null;
    }
    public void removeBothEdge(String from, String to){
        removeEdge(from, to);
        removeEdge(to ,from);
    }

    public boolean areAdjacent(String from, String to) {
        Vertex v1 = vertices.get(from);
        Vertex v2 = vertices.get(to);

        return matrix[v1.getId()][v2.getId()] != null;
    }
    public List<Vertex> neighbours(Vertex v) {
        List<Vertex> list = new LinkedList<>();

        vertices.forEach((s, vertex) -> {
            if (areAdjacent(v.getName(), s))
                list.add(vertex);
        });
        return list;
    }

    public Vertex getVertex(String name){
        return vertices.get(name);
    }
    public Edge getEdge(String from, String to){
        int i = vertices.get(from).getId();
        int j = vertices.get(to).getId();
        return matrix[i][j];
    }

    public Vertex[] getAllVertices(){
        return arr_vert;
    }
    public List<Edge> getAllEdges(){
        List<Edge> edgeList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(matrix[i][j] != null)
                    edgeList.add(matrix[i][j]);
            }
        }
        return edgeList;
    }

    public List<Edge> getIncidentEdges(Vertex v){
        List<Edge> edgeList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(matrix[i][j] != null && matrix[i][j].getOrigin().equals(v))
                    edgeList.add(matrix[i][j]);
            }
        }
        return edgeList;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arr_vert[i]).append(" : ");
            for (int j = 0; j < size; j++) {
                Edge edge = matrix[i][j];
                if(edge != null){
                    sb.append(edge.getDest() + "(" + edge.getWeight() + ") ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public void serialize(){
        BufferedWriter bw = null;
        try {
            File output = new File("output.txt");
            bw = new BufferedWriter(new FileWriter((output.getAbsoluteFile())));
            bw.write(this.toString());
            bw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Graph<Float> loadRussiaTXT() {
        Graph<Float> g = null;
        BufferedReader br = null;
        try {
            File file = new File("russia.txt");
            br = new BufferedReader(new FileReader(file));
            String[] list = br.readLine().split(" ");
            Vertex[] arr_vert = new Vertex[list.length];

            for (int i = 0; i < list.length; i++) {
                arr_vert[i] = new Vertex(list[i], i);
            }
            g = new Graph<>(arr_vert);

            String[] edges = br.readLine().split(" ");
            for (int i = 0; i < edges.length; i += 3) {
                String v1 = edges[i];
                String v2 = edges[i+1];
                String info = edges[i+2];

                Float d, c, t;
                String[] arr = info.split(":");
                d = Float.parseFloat(arr[0]);
                t = Float.parseFloat(arr[1]);
                c = Float.parseFloat(arr[2]);

                g.addBothEdge(v1, v2, t, d, t, c);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (br != null)
                    br.close();
                return g;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return g;
    }
}
