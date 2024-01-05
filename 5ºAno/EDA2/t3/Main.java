import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
            adjacencyList.get(r1-1).add(r2-1);
            adjacencyList.get(r2-1).add(r1-1);
        }

        int S = Integer.parseInt(input.readLine()) - 1; // Safe region

        System.out.println(maxPopulationReachable(adjacencyList, regionData, S));
    }

    private static int maxPopulationReachable(List<List<Integer>> adjacencyList, int[][] regionData,
            int safe) {

        Queue<Integer> searchList = new LinkedList<>();
        int max = regionData[safe][0];
        int nextSearch, nextCapacity, currCapacity, currPopulation, currNode, toSend = 0;
        boolean add = false;

        searchList.add(safe);

        while (!searchList.isEmpty()) {
            nextSearch = searchList.poll();
            nextCapacity = regionData[nextSearch][1];

            if (nextCapacity > 0 || nextSearch == safe) {
                for (int i = 0; i < adjacencyList.get(nextSearch).size(); i++) {
                    currNode = adjacencyList.get(nextSearch).get(i);
                    currPopulation = regionData[currNode][0];
                    currCapacity = regionData[currNode][1];
                    nextCapacity = regionData[nextSearch][1];
                    toSend = 0;
                    add = false;

                    if (currCapacity > 0 && currNode != nextSearch) {
                        if (nextSearch == safe) {
                            if (currCapacity > currPopulation) {
                                toSend = currPopulation;

                                add = true;
                            } else {
                                toSend = currCapacity;
                            }

                            max += toSend;
                            regionData[currNode][0] -= toSend;
                            regionData[currNode][1] -= toSend;

                        } else if (currNode != safe) {
                            if (currCapacity > currPopulation && nextCapacity >= currPopulation) {
                                toSend = currPopulation;

                                add = true;
                            } else if (currCapacity >= currPopulation && currPopulation >= nextCapacity) {
                                toSend = nextCapacity;
                            } else {
                                toSend = currCapacity;
                            }

                            max += toSend;
                            regionData[currNode][0] -= toSend;
                            regionData[currNode][1] -= toSend;
                            regionData[nextSearch][1] -= toSend;
                        }

                        if (add && toSend > 0) {
                            searchList.add(currNode);
                        }
                    }
                }
            }
        }

        return max;
    }
}