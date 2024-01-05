import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main2 {

    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static class State {
        int x, y, dist;
        public State(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] rcT = br.readLine().split(" ");
        int R = Integer.parseInt(rcT[0]);
        int C = Integer.parseInt(rcT[1]);
        int T = Integer.parseInt(rcT[2]);
        char[][] map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = row.charAt(j);
            }
        }
        for (int t = 0; t < T; t++) {
            String[] coords = br.readLine().split(" ");
            int x = Integer.parseInt(coords[0]) - 1;
            int y = Integer.parseInt(coords[1]) - 1;
            int result = bfs(map, x, y);
            if (result == -1) {
                System.out.println("Stuck");
            } else {
                System.out.println(result);
            }
        }
    }

    public static int bfs(char[][] map, int x, int y) {
        int R = map.length;
        int C = map[0].length;
        boolean[][][] visited = new boolean[R][C][4];
        Queue<State> q = new LinkedList<>();
        q.add(new State(x, y, 0));
        visited[x][y][0] = true;
        while (!q.isEmpty()) {
            State curr = q.poll();
            int cx = curr.x;
            int cy = curr.y;
            int cd = curr.dist;
            for (int i = 0; i < dirs.length; i++) {
                int nx = cx + dirs[i][0];
                int ny = cy + dirs[i][1];
                int nd = i;
                if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] == '.') {
                    if (!visited[nx][ny][nd]) {
                        visited[nx][ny][nd] = true;
                        q.add(new State(nx, ny, cd + 1));
                    }
                } else if (cd == 0 && !visited[cx][cy][i]) {
                    visited[cx][cy][i] = true;
                    q.add(new State(cx, cy, 1 + cd));
                }
            }
        }
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            if (visited[x][y][i]) {
                minDist = Math.min(minDist, 1);
            }
        }
        return (minDist == Integer.MAX_VALUE) ? -1 : minDist;
    }
}
