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
        this.flow = 0;
    }

    public int getDestination() {
        return destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }
}

class FlowNetwork {
    static final int INFINITY = Integer.MAX_VALUE;
    static final int NONE = 0;

    private int source, sink;
    private int nodes;
    private List<Edge>[] adjacents;

    public FlowNetwork(int nodes, int source, int sink) {
        this.nodes = nodes;
        this.source = source;
        this.sink = sink;
        
        adjacents = new ArrayList[nodes];
        for (int i = 0; i < nodes; i++) {
            adjacents[i] = new ArrayList<>();
        }
    }

    public void addEdge(int source, int destination, int capacity) {
        Edge forwardEdge = new Edge(destination, capacity);
        Edge backwardEdge = new Edge(source, 0);
        adjacents[source].add(forwardEdge);
        adjacents[destination].add(backwardEdge);
    }

    private FlowNetwork buildResidualNetwork() {
        FlowNetwork r = new FlowNetwork(nodes, source, sink);

        for (int u = 0; u < nodes; u++) {
            for (Edge e : adjacents[u]) {
                int v = e.getDestination();
                int capacity = e.getCapacity();
                int flow = e.getFlow();

                // Add forward edge (u, v) with residual capacity
                int residualCapacity = capacity - flow;
                r.addEdge(u, v, residualCapacity);

                // Add backward edge (v, u) with residual capacity
                r.addEdge(v, u, flow);
            }
        }

        return r;
    }

    private void updateResidualCapacity(int from, int to, int capacity, int flow) {
        for (Edge e : adjacents[from]) {
            if (e.getDestination() == to) {
                e.setCapacity(capacity - flow);
                break;
            }
        }
        for (Edge e : adjacents[to]) {
            if (e.getDestination() == from) {
                e.setCapacity(flow);
                break;
            }
        }
    }

    private void incrementFlow(int[] p, int increment, FlowNetwork r) {

        int v = sink;
        int u = p[v];
        while (u != NONE) {
            boolean uv = false; // (u,v) é um arco da rede?
            for (Edge e : adjacents[u]) {
                if (e.getDestination() == v) {
                    e.setFlow(e.getFlow() + increment);
                    r.updateResidualCapacity(u, v, e.getCapacity(), e.getFlow());
                    uv = true; // (u,v) é um arco da rede
                    break;
                }
            }
            if (!uv) {
                for (Edge e : adjacents[v]) {
                    if (e.getDestination() == u) {
                        e.setFlow(e.getFlow() - increment);
                        r.updateResidualCapacity(v, u, e.getCapacity(), e.getFlow());
                        break;
                    }
                }
            }
            v = u;
            u = p[v];
        }
    }

    private int findPath(int[] p) {
        int[] cf = new int[nodes];
        Queue<Integer> q = new LinkedList<>();
        for (int u = 0; u < nodes; ++u) {
            p[u] = NONE;
        }
        cf[source] = INFINITY;
        q.add(source);

        while (!q.isEmpty()) {
            int u = q.remove();
            if (u == sink) {
                break;
            }
            for (Edge e : adjacents[u]) {
                int v = e.getDestination();
                if (e.getCapacity() > 0 && cf[v] == 0) {
                    cf[v] = Math.min(cf[u], e.getCapacity());
                    p[v] = u;
                    q.add(v);
                }
            }
        }
        return cf[sink];
    }

    public int edmondsKarp() {
        FlowNetwork r = buildResidualNetwork();
        int flowValue = 0;
        int[] p = new int[nodes];
        int increment;
        while ((increment = r.findPath(p)) > 0) {
            incrementFlow(p, increment, r);
            flowValue += increment;
        }
        return flowValue;
    }
}

public class Main2 {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // Read input data
        int R = 5;
        int L = 6;
        int[][] regionData = {
            {1000, 1050},
            {5000, 6000},
            {350, 320},
            {2100, 2100},
            {780, 900}
        };
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < R + 2; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        int[][] regionConnections = {
            {2, 4},
            {2, 3},
            {2, 5},
            {4, 1},
            {5, 3},
            {1, 5}
        };
        int S = 4;

        int source = 0;
        int sink = R + 1;

        for (int i = 1; i <= R; i++) {
            adjacencyList.get(source).add(i);
            adjacencyList.get(i).add(source);
        }

        adjacencyList.get(S).add(sink);
        adjacencyList.get(sink).add(S);

        int[][] capacities = new int[R + 2][R + 2];

        for (int i = 1; i <= R; i++) {
            capacities[source][i] = regionData[i - 1][0];
        }

        for (int i = 1; i <= R; i++) {
            for (int neighbor : adjacencyList.get(i)) {
                capacities[i][neighbor] = i == S ? INF : regionData[i - 1][1];
            }
        }

        FlowNetwork flowNetwork = new FlowNetwork(R + 2, source, sink);

        for (int i = 0; i < R + 2; i++) {
            for (int j = 0; j < R + 2; j++) {
                if (capacities[i][j] > 0) {
                    flowNetwork.addEdge(i, j, capacities[i][j]);
                }
            }
        }

        int maxFlow = flowNetwork.edmondsKarp();

        System.out.println(maxFlow);
    }
}
