import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    enum TYPES {
        EMPTY, HOLE, DEST
    };

    int x, y;
    TYPES type;

    public Node(int x, int y, char c) {
        this.x = x;
        this.y = y;

        switch (c) {
            case '.':
                this.type = TYPES.EMPTY;
                break;
            case 'O':
                this.type = TYPES.HOLE;
                break;
            case 'H':
                this.type = TYPES.DEST;
                break;
        }
    }

    public boolean isHole() {
        return this.type == TYPES.HOLE;
    }

    public boolean isDest() {
        return this.type == TYPES.DEST;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

}

public class Main {

    static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String[] line = input.readLine().split(" ");

        int rows = Integer.parseInt(line[0]);
        int cols = Integer.parseInt(line[1]);
        int tests = Integer.parseInt(line[2]);

        Node[][] map = new Node[rows][cols];

        char[] row = new char[cols];

        for (int r = 0; r < rows; r++) {
            row = input.readLine().toCharArray();

            for (int c = 0; c < cols; c++) {
                map[r][c] = new Node(r, c, row[c]);
            }
        }

        int[] sx = new int[tests], sy = new int[tests];

        for (int t = 0; t < tests; t++) {
            line = input.readLine().split(" ");

            sx[t] = Integer.parseInt(line[0]) - 1;
            sy[t] = Integer.parseInt(line[1]) - 1;
        }

        int res;

        for (int t = 0; t < tests; t++) {
            res = search(map, rows, cols, sx[t], sy[t]);
            if (res >= 0) {
                System.out.println(res);
            } else {
                System.out.println("Stuck");
            }
        }

    }

    private static int search(Node[][] map, int rows, int cols, int sx, int sy) {

        if (map[sx][sy].isHole())
            return -1;
        if (map[sx][sy].isDest())
            return 0;

        Queue<Node> Q = new LinkedList<>();

        int[][] moves = new int[rows][cols];

        boolean[][] visits = new boolean[rows][cols];

        int nx, ny; // Next x, Next y.

        Q.add(map[sx][sy]);
        moves[sx][sy] = 0;

        Node currNode;

        while (!Q.isEmpty()) {
            currNode = Q.remove();

            visits[currNode.x][currNode.y] = true;

            for (int d = 0; d < 4; d++) {

                nx = currNode.x;
                ny = currNode.y;

                // Loop enquanto estiver dentro dos limites e a próxima posição na respetiva direção não for um buraco
                while (nx + dirs[d][0] >= 0 && nx + dirs[d][0] < rows && ny + dirs[d][1] >= 0 && ny + dirs[d][1] < cols
                        && !map[nx + dirs[d][0]][ny + dirs[d][1]].isHole()) {

                    // Verifica se o destino está no meio do caminho
                    if (map[nx][ny].isDest()) {
                        return moves[currNode.x][currNode.y] + 1;
                    }

                    // Define a posição como visitada
                    visits[nx][ny] = true;

                    // Vai continuar o caminho na respetiva direção
                    nx = nx + dirs[d][0];
                    ny = ny + dirs[d][1];

                }

                // Verifica se o destino está nas bordas do tabuleiro
                if (map[nx][ny].isDest()) {
                    return moves[currNode.x][currNode.y] + 1;
                }

                if (
                    nx + dirs[d][0] >= 0 && nx + dirs[d][0] < rows && ny + dirs[d][1] >= 0 && ny + dirs[d][1] < cols // Verifica a próxima posição está dentro dos limites
                    && map[nx + dirs[d][0]][ny + dirs[d][1]].isHole() && !visits[nx][ny] // Verifica se a próxima posição é um buraco
                    && (moves[nx][ny] <= 0 || moves[nx][ny] > moves[currNode.x][currNode.y] + 1)) // Verifica se a quantidade de movimentos para chegar aqui foi inferior
                    {
                    moves[nx][ny] = moves[currNode.x][currNode.y] + 1;

                    Q.add(map[nx][ny]);
                }

            }
        }

        return -1;
    }

}