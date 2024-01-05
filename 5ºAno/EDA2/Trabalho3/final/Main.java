import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


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

        int S = Integer.parseInt(input.readLine()); //Safe region

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

        System.out.println(maxPopulationReachable(adjacencyList, capacities, source, sink, L));
    }

    private static int maxPopulationReachable(List<List<Integer>> adjacencyList, int[][] capacities, int source, int sink, int numberOfLinks) {
        int maxFlow = 0;
        int[] parent = new int[adjacencyList.size()];
        int cont = numberOfLinks;

        // While there's a path in the residual graph
        while (bfs(adjacencyList, capacities, parent, source, sink) && cont>0) {
            int pathCapacity = INF;

            // Find the minimum capacity in the current path
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathCapacity = Math.min(pathCapacity, capacities[u][v]);
            }

            // Update capacities in the residual graph
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                capacities[u][v] -= pathCapacity;
                capacities[v][u] += pathCapacity;
            }
            cont--;
            maxFlow += pathCapacity;
        }
        return maxFlow;
    }

    private static boolean bfs(List<List<Integer>> adjacencyList, int[][] capacities, int[] parent, int source, int sink) {
        Arrays.fill(parent, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        parent[source] = source;

        // While there are still nodes to explore
        while (!queue.isEmpty()) {

            // Get the next node to explore from the queue
            int currentNode = queue.poll();

            // Check all neighbors of the current node
            for (int neighbor : adjacencyList.get(currentNode)) {

                // If the neighbor has not been visited yet and there's available capacity
                if ( (parent[neighbor] == -1 && capacities[currentNode][neighbor] > 0) ) {

                    // Set the parent of the neighbor to the current node
                    parent[neighbor] = currentNode;

                    // If we reached the sink, a path has been found
                    if (neighbor == sink) {
                        return true;
                    }

                    // Add the neighbor to the queue to explore its neighbors
                    queue.add(neighbor);
                }
            }
        }

        // no path was found from source to sink
        return false;
    }
}
