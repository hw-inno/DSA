import java.util.*;
import java.io.*;
import java.util.function.BiPredicate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s.protasov
 * @param <VertexType> type of object stored in vertex
 * @param <EdgeWeight> weight type of edge
 */
public class Graph<VertexType, EdgeWeight> {
    
    int size;
    EdgeWeight[][] adj;
    VertexType[] vertices;
    HashMap<VertexType, Integer> addr;
    
    public Graph(VertexType[] vertices) {
        this.size = vertices.length;
        this.vertices = vertices;
        this.adj = (EdgeWeight[][])(new Object[size][size]);
        this.addr = new HashMap<>();
        for (int i = 0; i < size; i++) {
            this.addr.put(vertices[i], i);
        }       
    }

    public void setEdge(VertexType from, VertexType to, EdgeWeight weight) {
        adj[addr.get(from)][addr.get(to)] = weight;
    }
    
    public EdgeWeight getEdge(VertexType from, VertexType to) {
        return adj[addr.get(from)][addr.get(to)];
    }
    
    public void removeEdge(VertexType from, VertexType to) {
        setEdge(from, to, null);
    }
    
    public boolean areAdjacent(VertexType from, VertexType to) {
        return getEdge(from, to) != null;
    }
    
    public List<VertexType> neighbours(VertexType v) {
        ArrayList<VertexType> nbs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (areAdjacent(v, this.vertices[i]))
                nbs.add(this.vertices[i]);
        }
        return nbs;
    }
    
    public static Graph<String, Integer> loadDirected(String filename) {
        File file = new File(filename);
        Graph<String, Integer> graph = null;
        try {
            Scanner scanner = new Scanner(file);
            String[] names = scanner.nextLine().split(" ");
            for (int i = 0; i < names.length; i++) names[i] = names[i].trim();
            graph = new Graph<>(names);
            while (scanner.hasNext()) {
                String from = scanner.next().trim();
                String to = scanner.next().trim();
                int dist = Integer.parseInt(scanner.next().trim());
                graph.setEdge(from, to, dist);
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            ex.printStackTrace();
        }
        return graph;
    }
    
    public static Graph<String, Integer> loadUnDirected(String filename) {
        File file = new File(filename);
        Graph<String, Integer> graph = null;
        try {
            Scanner scanner = new Scanner(file);
            String[] names = scanner.nextLine().split(" ");
            for (int i = 0; i < names.length; i++) names[i] = names[i].trim();
            graph = new Graph<>(names);
            while (scanner.hasNext()) {
                String from = scanner.next().trim();
                String to = scanner.next().trim();
                int dist = Integer.parseInt(scanner.next().trim());
                graph.setEdge(from, to, dist);
                graph.setEdge(to, from, dist);
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            ex.printStackTrace();
        }
        return graph;
    }

    public void save(String filename) {
        try {
            try (PrintWriter writer = new PrintWriter(filename, "UTF-8")) {
                for (int i = 0; i < size - 1; i++) {
                    writer.print(this.vertices[i]); writer.print(" ");
                }
                if(size > 0) writer.print(this.vertices[size - 1]);
                writer.println();
                for (VertexType v1: vertices)
                    for (VertexType v2: neighbours(v1)) {
                        if (!v1.equals(v2)) {
                            writer.print(v1); writer.print(" ");
                            writer.print(v2); writer.print(" ");
                            writer.print(getEdge(v1, v2)); writer.print(" ");
                        }
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public static Graph<String, Integer> chessGraph(BiPredicate<String, String> p) {
        String[] cells = new String[64];
        int i = 0;
        for (char a = 'A'; a <= 'H'; a++) {
            for (byte b = 1; b <= 8; b++) {
                cells[i++] = "" + a + b;
            }
        }
        Graph<String, Integer> g = new Graph<>(cells);
        for (String cell1 : cells)     
            for (String cell2 : cells) {
                if (p.test(cell1, cell2))
                g.setEdge(cell1, cell2, 1);
            }
        return g;
    }

    public void dfs(VertexType v){
        boolean visited[] = new boolean[size];

        _dfs(v, visited);
        System.out.println();

        boolean flag = true;
        for (boolean b: visited) {
            if(b == false)
                flag = false;
        }
        System.out.println("isConnected : " + flag);
    }
    private void _dfs(VertexType v, boolean[] visited){
        visited[addr.get(v)] = true;
        System.out.println(v + " ");

        for(VertexType m : neighbours(v)){
            if(m.equals("D5"))
                return;
            if(!visited[addr.get(m)])
                _dfs(m, visited);
        }
    }
}

