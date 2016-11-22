// Directed/Undirected, without parallel edges, without self loops

import java.io.*;
import java.util.*;

public class Graph<EdgeType> {
    private Map<String, Vertex> vertices = new HashMap<>();
    public Vertex[] arr_vert;
    private Edge[][] matrix;
    private int size = 0;
    private int E = 0;

    public Graph( Vertex... vertices) {
        this.size = vertices.length;
        matrix = new Edge[size][size];
        this.arr_vert = vertices;
        for (Vertex v : vertices) {
            this.vertices.put(v.getName(), v);
        }
    }
    public int getSize(){ return size; }

    public Edge addEdge(Vertex v1, Vertex v2, EdgeType w){
        Edge old = matrix[v1.getId()][v2.getId()];

        Edge newEdge = new Edge(v1, v2, w);
        matrix[v1.getId()][v2.getId()] = newEdge;
        return old;
    }
    public void addBothEdge(Vertex v1, Vertex v2, EdgeType w){
        addEdge(v1, v2, w);
        addEdge(v2, v1, w);
    }

    public Edge removeEdge(String from, String to) {
        Vertex v1 = vertices.get(from);
        Vertex v2 = vertices.get(to);

        Edge e = matrix[v1.getId()][v2.getId()];
        matrix[v1.getId()][v2.getId()] = null;
        return e;
    }
    public Edge removeEdge(Vertex v1, Vertex v2) {
        Edge e = matrix[v1.getId()][v2.getId()];
        matrix[v1.getId()][v2.getId()] = null;
        return e;
    }

    public void addVertex(String name){
        Vertex newest = new Vertex(name, arr_vert.length);
        vertices.put(name, newest);
        Vertex[] arr = new Vertex[size+1];
        for (int i = 0; i < size; i++) {
            arr[i] = arr_vert[i];
        }

        arr[size] = newest;

        arr_vert = arr;
        Edge[][] newMatrix = new Edge[size+1][size+1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        matrix = newMatrix;
        size++;
    }
    public void removeVertex(String name){
        vertices.remove(name);
        int i = 0;

        for (i = 0; i < size; i++) {
            if(arr_vert[i].getName().equals(name))
                break;
        }
        if(i == size)
            return; // Vertex not found

        Vertex[] arr = new Vertex[size-1];
        for (int j = 0; j < i; j++) {
            arr[j] = arr_vert[j];
        }
        for (int j = i+1; j < size; j++) {
            arr[j-1] = arr_vert[j];
        }
        arr_vert = arr;

        Edge[][] newMatrix = new Edge[size-1][size-1];
        for (int j = 0; j < i; j++) {
            for (int k = 0; k < i; k++) {
                newMatrix[j][k] = matrix[j][k];
            }
        }
        for (int j = i; j < size-1; j++) {
            for (int k = i; k < size-1; k++) {
                newMatrix[j-1][k-1] = matrix[j][k];
            }
        }

        matrix = newMatrix;
        size--;
    };

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
}
