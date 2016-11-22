/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;

/**
 *
 * @author s.protasov
 */
public class GraphTests {

    static long ts = -1;

    public static void tic() {
        ts = System.nanoTime();
    }

    /**
     *
     * @return milliseconds
     */
    public static float tac() {
        return (System.nanoTime() - ts) / 1e6f;
    }

    public static long mem() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //TODO use your graph class
        Graph<Integer> g = null;

        int SIZES = 8;
        String[] names = new String[]{"sparce", "low", "medium", "high", "dense"};
        float[] ratios = new float[]{0.03f, 0.25f, 0.5f, 0.75f, 0.97f};

        String[] params = new String[] { "mem", "addedge", "removeedge", "addnode", "removemnode", "areadj", "adjnodes" };

        // stores all resulting information as [matrix][size][methods]
        float[][][] cube = new float[names.length][SIZES][params.length];

        // for each density
        for (int i = 0; i < names.length; i++) {
            // for each size
            System.gc();
            System.out.printf("%s graph [%.2f]:\n", names[i], ratios[i]);

            //TODO: tune your numbers here
            for (int s = 0, size=100;  s < SIZES; s++,size *=2) {
                System.gc();
                System.out.printf("    %d nodes:\n", size);

                //TODO: adjust getGraph method for your class
                g = getMyGraph(size, ratios[i]);

                // ------------------ MEM ----------------------------------
                float memory =  mem() / 1024;
                cube[i][s][0] = memory;
                System.out.printf("        Mem: %.0f KB\n", memory);
                //-----------------------------------------------------------

                Random rand = new Random();
                int N = 100;
                float time;

                //--------------------- ADD EDGE ----------------------------
                tic();
                for (int x = 0; x < N; x++) {
                    int from = rand.nextInt(size), to = rand.nextInt(size);
                    //TODO: your code here
                    g.addEdge(g.arr_vert[from], g.arr_vert[to], 1);
                }
                time = tac() / N;
                cube[i][s][1] = time;
                System.out.printf("        Add edge: %f ms\n", time);
                //------------------------------------------------------------

                //--------------------- REMOVE EDGE --------------------------
                tic();
                for (int x = 0; x < N; x++) {
                    int from = rand.nextInt(size), to = rand.nextInt(size);
                    //TODO: your code here
                    g.removeEdge(g.arr_vert[from], g.arr_vert[to]);
                }
                time = tac() / N;
                cube[i][s][2] = time;
                System.out.printf("        Remove edge: %f ms\n", time);
                //------------------------------------------------------------

                //--------------------- ADD VERTEX ---------------------------
                tic();
                for (int x = 0; x < N; x++) {
                    int from = rand.nextInt(size), to = rand.nextInt(size);
                    //TODO: your code here
                    g.addVertex(String.valueOf(g.getSize()+1));
                    //g.addVertex(String.valueOf(from));
                }
                time = tac() / N;
                cube[i][s][3] = time;
                System.out.printf("        Add vertex: %f ms\n", time);
                //------------------------------------------------------------

                //--------------------- REMOVE VERTEX ------------------------
                tic();
                for (int x = 0; x < N; x++) {
                     int from = rand.nextInt(size), to = rand.nextInt(size);
                    //TODO: your code here
                     g.removeVertex(String.valueOf(g.getSize()));

                }
                time = tac() / N;
                cube[i][s][4] = time;
                System.out.printf("        Remove vertex: %f ms\n", time);
                //------------------------------------------------------------

                //--------------------- ARE ADJACENT ------------------------
                tic();
                for (int x = 0; x < N; x++) {
                    int from = rand.nextInt(size), to = rand.nextInt(size);
                    //TODO: your code here
                    g.areAdjacent(g.arr_vert[from].getName(), g.arr_vert[to].getName());
                }
                time = tac() / N;
                cube[i][s][5] = time;
                System.out.printf("        Are adjacent: %f ms\n", time);
                //------------------------------------------------------------

                //--------------------- ADJACENT NODES -----------------------
                tic();
                for (int x = 0; x < N; x++) {
                    int from = rand.nextInt(size);
                    //TODO: your code here
                    g.neighbours(g.arr_vert[from]);
                }
                time = tac() / N;
                cube[i][s][6] = time;
                System.out.printf("        Adjacent nodes: %f ms\n", time);
                //------------------------------------------------------------
            }
        }
    }

    public static Graph<Integer> getMyGraph(int nodes, float edgeRatio) {
        Random rand = new Random();
        String[] ids = new String[nodes];
        for (int i = 0; i < nodes; i++) {
            ids[i] = Integer.toString(new Integer(i).hashCode());
        }
        // keeping only unique values
        Vertex[] unique = new Vertex[nodes];
        for (int i = 0; i < nodes; i++) {
            unique[i] = new Vertex(ids[i], i);
        }
        Graph<Integer> g = new Graph<>(unique);
        if (edgeRatio > 0.5) {
            int total = nodes * (nodes - 1) / 2;
            int count = total;
            // add all nodes
            for (int i = 0; i < nodes; i++) {
                for (int j = i + 1; j < nodes; j++) {
                    //TODO: place your code here
                    g.addBothEdge(g.arr_vert[i], g.arr_vert[j], 1);
                }
            }

            // remove while more
            while (edgeRatio * total < count) {
                int from = rand.nextInt(nodes), to = rand.nextInt(nodes);
                //TODO: place your code here
                if (from != to) {
                    if (g.removeEdge(g.arr_vert[from].getName(), g.arr_vert[to].getName()) != null) {
                        count--;
                    }
                }
            }
        } else {
            int total = nodes * (nodes - 1) / 2;
            int count = 0;
            // add while less
            while (edgeRatio * total > count) {
                int from = rand.nextInt(nodes), to = rand.nextInt(nodes);
                //TODO: place your code here
                if (from != to) {
                    if (g.addEdge(g.arr_vert[from], g.arr_vert[to], 1) == null) {
                        count++;
                    }
                }
            }
        }
        return g;
    }
}
