public class Main{
    public static void main(String[] args) {
        Graph<String, Integer> graph = Graph.loadGraph("Distances.txt");
        System.out.println(graph.toString());
        graph.save();
    }
}
