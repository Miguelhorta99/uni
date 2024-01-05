/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package eda2.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        while (cases-- > 0) {
            int r = Integer.parseInt(br.readLine());
            int[][] grid = new int[r + 1][r + 1];
            for (int[] row : grid) {
                Arrays.fill(row, 1);
            }
            String[] s = br.readLine().split(" ");
            int sx = Integer.parseInt(s[0]);
            int sy = Integer.parseInt(s[1]);
            s = br.readLine().split(" ");
            int ex = Integer.parseInt(s[0]);
            int ey = Integer.parseInt(s[1]);
            int b = Integer.parseInt(br.readLine());
            while (b-- > 0) {
                s = br.readLine().split(" ");
                int bx = Integer.parseInt(s[0]);
                int by = Integer.parseInt(s[1]);
                String dir = s[2];
                if (dir.equals("S")) {
                    by++;
                } else if (dir.equals("W")) {
                    bx++;
                }
                grid[bx][by] = 0;
            }
            int[][] dp = new int[r + 1][r + 1];
            dp[sx][sy] = 1;
            for (int i = sx; i <= ex; i++) {
                for (int j = sy; j <= ey; j++) {
                    if (grid[i][j] == 0) {
                        continue;
                    }
                    if (i > sx) {
                        dp[i][j] += dp[i - 1][j];
                    }
                    if (j > sy) {
                        dp[i][j] += dp[i][j - 1];
                    }
                }
            }
            System.out.println(dp[ex][ey]);
        }
        br.close();
    }
}
