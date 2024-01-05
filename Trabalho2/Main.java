import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] rcT = br.readLine().split(" ");
        int R = Integer.parseInt(rcT[0]);
        int C = Integer.parseInt(rcT[1]);
        int T = Integer.parseInt(rcT[2]);

        char[][] map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < T; i++) {
            String[] initial = br.readLine().split(" ");
            int r = Integer.parseInt(initial[0]) - 1;
            int c = Integer.parseInt(initial[1]) - 1;

            int[][] dist = new int[R][C];
            for (int j = 0; j < R; j++) {
                for (int k = 0; k < C; k++) {
                    dist[j][k] = Integer.MAX_VALUE;
                }
            }
            dist[r][c] = 0;

            boolean[][] visited = new boolean[R][C];
            visited[r][c] = true;

            int qSize = 1;
            int[] qR = new int[R * C];
            int[] qC = new int[R * C];
            qR[0] = r;
            qC[0] = c;

            boolean found = false;

            while (qSize > 0) {
                int currR = qR[0];
                int currC = qC[0];
                qSize--;

                if (map[currR][currC] == 'H') {
                    System.out.println(dist[currR][currC]);
                    found = true;
                    break;
                }

                for (int j = 0; j < 4; j++) {
                    int newR = currR + dx[j];
                    int newC = currC + dy[j];

                    if (newR >= 0 && newR < R && newC >= 0 && newC < C && map[newR][newC] != 'O') {
                        if ((j == 0 || j == 1) && !visited[newR][newC]) {
                            visited[newR][newC] = true;
                            dist[newR][newC] = dist[currR][currC] + 1;
                            qR[qSize] = newR;
                            qC[qSize] = newC;
                            qSize++;
                        } else if ((j == 2 || j == 3) && !visited[currR][currC]) {
                            visited[currR][currC] = true;
                            dist[currR][currC] = dist[currR][currC] + 1;
                            qR[qSize] = currR;
                            qC[qSize] = currC;
                            qSize++;
                        }
                    }
                }
            }

            if (!found) {
                System.out.println("Stuck");
            }
        }
    }
}
