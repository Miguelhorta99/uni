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
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] firstString = input.readLine().split(" ");
        int R = Integer.parseInt(firstString[0]);
        int L = Integer.parseInt(firstString[1]);

        int[][] regionData = new int[R][2];
        for (int i = 0; i < R; i++) {
            String[] region = input.readLine().split(" ");
            regionData[i][0] = Integer.parseInt(region[0]);
            regionData[i][1] = Integer.parseInt(region[1]);
        }

        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < R + 2; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int i = 0; i < L; i++) {
            String[] regionConnections = input.readLine().split(" ");
            int r1 = Integer.parseInt(regionConnections[0]);
            int r2 = Integer.parseInt(regionConnections[1]);
            adjacencyList.get(r1).add(r2);
            adjacencyList.get(r2).add(r1);
        }

        int S = Integer.parseInt(input.readLine());

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

        int maxPopulation = maxPopulationReachable(adjacencyList, capacities, source, sink);
        System.out.println(maxPopulation);
    }

    private static int maxPopulationReachable(List<List<Integer>> adjacencyList, int[][] capacities, int source, int sink) {
        int maxFlow = 0;
        int[] parent = new int[adjacencyList.size()];

        while (bfs(adjacencyList, capacities, parent, source, sink)) {
            int pathCapacity = INF;

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathCapacity = Math.min(pathCapacity, capacities[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                capacities[u][v] -= pathCapacity;
                capacities[v][u] += pathCapacity;
            }

            maxFlow += pathCapacity;
        }

        return maxFlow;
    }

    private static boolean bfs(List<List<Integer>> adjacencyList, int[][] capacities, int[] parent, int source, int sink) {
        Arrays.fill(parent, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        parent[source] = -2;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : adjacencyList.get(u)) {
                if (parent[v] == -1 && capacities[u][v] > 0) {
                    parent[v] = u;
                    if (v == sink) {
                        return true;
                    }
                    queue.offer(v);
                }
            }
        }

        return false;
    }
}
