
package eda2.newmain;

/**
 *
 * @author miguel
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NewMain {

       public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // number of test cases
        
        while (t-- > 0) {
            int r = Integer.parseInt(br.readLine()); // grid size
            int[][] grid = new int[r+1][r+1]; // 1-indexed grid
            
            String[] startCoords = br.readLine().split(" ");
            int sx = Integer.parseInt(startCoords[0]);
            int sy = Integer.parseInt(startCoords[1]);
            
            String[] endCoords = br.readLine().split(" ");
            int ex = Integer.parseInt(endCoords[0]);
            int ey = Integer.parseInt(endCoords[1]);
            
            int b = Integer.parseInt(br.readLine()); // number of blockages
            
            // mark blockages on the grid
            for (int i = 0; i < b; i++) {
                String[] blockage = br.readLine().split(" ");
                int px = Integer.parseInt(blockage[0]);
                int py = Integer.parseInt(blockage[1]);
                String dir = blockage[2];
                if (dir.equals("N")) {
                    grid[px][py] |= 1;
                    if (px < r) {
                        grid[px+1][py] |= 4;
                    }
                } else if (dir.equals("E")) {
                    grid[px][py] |= 2;
                    if (py < r) {
                        grid[px][py+1] |= 8;
                    }
                } else if (dir.equals("S")) {
                    // convert to opposite direction
                    grid[px][py-1] |= 1;
                    if (px > 1) {
                        grid[px-1][py-1] |= 4;
                    }
                } else if (dir.equals("W")) {
                    // convert to opposite direction
                    grid[px-1][py] |= 2;
                    if (py > 1) {
                        grid[px-1][py-1] |= 8;
                    }
                }
            }
            
            // use dynamic programming to count paths
            long[][] dp = new long[r+1][r+1];
            dp[sx][sy] = 1;
            for (int i = sx; i <= ex; i++) {
                for (int j = sy; j <= ey; j++) {
                    if (i > sx) {
                        dp[i][j] += dp[i-1][j];
                    }
                    if (j > sy) {
                        dp[i][j] += dp[i][j-1];
                    }
                    if ((grid[i][j] & 1) != 0 && i > 1) {
                        dp[i][j] = 0;
                    }
                    if ((grid[i][j] & 2) != 0 && j > 1) {
                        dp[i][j] = 0;
                    }
                }
            }
            System.out.println(dp[ex][ey]);
        }
    }
}

