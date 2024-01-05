import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Edge {
    private int destination;
    private int capacity;
    private int flow;

    public Edge(int destination, int capacity) {
        this.destination = destination;
        this.capacity = capacity;
        flow = 0;
    }

    public int destination() {
        return destination;
    }

    public int capacity() {
        return capacity;
    }

    public void capacity(int capacity) {
        this.capacity = capacity;
    }

    public int flow() {
        return flow;
    }

    public void flow(int flow) {
        this.flow = flow;
    }
}

class FlowNetwork {
    static final int INFINITY = Integer.MAX_VALUE;
    static final int NONE = 0;

    private int source, sink;
    private int nodes;
    private List<Edge>[] adjacents;

    /*
     * Cria e devolve uma rede residual, correspondendo ao fluxo
     * actual na rede de fluxos.
     */
    private FlowNetwork buildResidualNetwork() {
        FlowNetwork r = new FlowNetwork();
        // ... cria os arcos directos

        // e inversos (de cancelamento) ...
        return r;
    }

    /*
     * Actualiza as capacidades (residuais) do arcos (FROM,TO) e
     * (TO,FROM) de uma rede residual.
     * O arco (FROM,TO) ́e o arco da rede de fluxos, com capacidade
     * CAPACITY e fluxo FLOW.
     */
    private void updateResidualCapacity(int from, int to, int capacity, int flow) {
        for (Edge e : adjacents[from])
            if (e.destination() == to) {
                e.capacity(capacity - flow);
                break;
            }
        for (Edge e : adjacents[to])
            if (e.destination() == from) {
                e.capacity(flow);
                break;
            }
    }

    /*
     * Aumenta em INCREMENT o fluxo ao longo do caminho
     * SINK P[SINK] P[P[SINK]] ... SOURCE
     * e actualiza a rede residual R correspondente.
     */
    private void incrementFlow(int[] p, int increment, FlowNetwork r) {
        
        int v = sink;
        int u = p[v];
        while (u != NONE) {
            boolean uv = false; // (u,v) ́e um arco da rede?
            for (Edge e : adjacents[u])
                if (e.destination() == v) {
                    e.flow(e.flow() + increment);
                    r.updateResidualCapacity(u, v, e.capacity(), e.flow());
                    uv = true; // (u,v) ́e um arco da rede
                    break;
                }
            // se (u,v) n~ao ́e um arco da rede, ent~ao (v,u) ́e
            if (!uv)
                for (Edge e : adjacents[v])
                    if (e.destination() == u) {
                        e.flow(e.flow() - increment);
                        r.updateResidualCapacity(v, u, e.capacity(),
                                e.flow());
                        break;
                    }
            v = u;
            u = p[v];
        }
    }

    /*
     * Percurso em largura para encontrar um caminho de menor
     * comprimento, de SOURCE para SINK, numa rede residual.
     * Guarda o predecessor de cada nó em P e devolve a capacidade
     * residual do caminho. Devolve 0 se nao ha caminho.
     */
    private int findPath(int[] p) {
        int[] cf = new int[nodes]; // menor capac. residual no caminho
        // cf[v] = 0 at ́e o n ́o ser descoberto
        Queue<Integer> q = new LinkedList<>();
        for (int u = 0; u < nodes; ++u)
            p[u] = NONE; // predecessores dos n ́os
        cf[source] = INFINITY; // procura-se o m ́ınimo
        q.add(source);

        while (!q.isEmpty()) {
            int u = q.remove();
            if (u == sink)
                break; // encontrou caminho
            for (Edge e : adjacents[u]) {
                int v = e.destination();
                if (e.capacity() > 0 && cf[v] == 0) {
                    cf[v] = Math.min(cf[u], e.capacity());
                    p[v] = u;
                    q.add(v);
                }
            }
        }
        return cf[sink]; // capacidade residual do caminho
    }

    /*
     * 
     * Algoritmo de Edmonds-Karp.
     * Devolve o valor do
     * fluxo maximo.
     */

    public int edmondsKarp() {
        FlowNetwork r = buildResidualNetwork();
        int flowValue = 0; // valor do fluxo inicial
        int[] p = new int[nodes]; // predecessor no caminho
        int increment; // incremento do fluxo
        while ((increment = r.findPath(p)) > 0) {
            // incrementa fluxo e actualiza a rede residual
            incrementFlow(p, increment, r);
            flowValue += increment; // valor actual do fluxo
        }
        return flowValue; // valor m ́aximo do fluxo
    }
}


public class Main {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        // Read input data
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] firstString = input.readLine().split(" ");

        int R = Integer.parseInt(firstString[0]);
        int L = Integer.parseInt(firstString[1]);

        // Read region data (departure capacity and population)
        int[][] regionData = new int[R][2];
        for (int i = 0; i < R; i++) {
            String[] region = input.readLine().split(" ");
            regionData[i][0] = Integer.parseInt(region[0]);
            regionData[i][1] = Integer.parseInt(region[1]);
        }

        // Create adjacency list to store region connections
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < R + 2; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Read and store region connections
        for (int i = 0; i < L; i++) {

            String[] regionConnections = input.readLine().split(" ");

            int r1 = Integer.parseInt(regionConnections[0]);
            int r2 = Integer.parseInt(regionConnections[1]);
            adjacencyList.get(r1).add(r2);
            adjacencyList.get(r2).add(r1);
        }

        int S = Integer.parseInt(input.readLine()); // Safe region

        // Add source and sink nodes
        int source = 0;
        int sink = R + 1;

        // Connect source to each region with capacity equal to the region's population
        for (int i = 1; i <= R; i++) {
            adjacencyList.get(source).add(i);
            adjacencyList.get(i).add(source);
        }

        // Connect safe region to sink node with infinite capacity
        adjacencyList.get(S).add(sink);
        adjacencyList.get(sink).add(S);

        // Initialize capacities matrix
        int[][] capacities = new int[R + 2][R + 2];

        // Set capacities for source and regions
        for (int i = 1; i <= R; i++) {
            capacities[source][i] = regionData[i - 1][0];
        }

        // Set capacities for regions and their neighbors
        for (int i = 1; i <= R; i++) {
            for (int neighbor : adjacencyList.get(i)) {
                capacities[i][neighbor] = i == S ? INF : regionData[i - 1][1];
            }
        }

    }

}