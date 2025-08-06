import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 욕심쟁이 판다

    static final int[][] dt = {{-1, 0},  {0, 1}, {1, 0}, {0, -1}};
    static int n;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }
        System.out.println(answer);
    }

    static int dfs(int x, int y) {
        if(dp[x][y] != 0) return dp[x][y];
        dp[x][y] = 1;

        int curDp = dp[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x + dt[i][0];
            int ny = y + dt[i][1];

            if(nx < 0 || nx > n-1 || ny < 0 || ny > n-1) continue;
            if(map[x][y] >= map[nx][ny]) continue;
            if(dp[nx][ny] != 0) {
                dp[x][y] = Math.max(dp[x][y], curDp + dp[nx][ny]);
                continue;
            }

            dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
        }

        return dp[x][y];
    }
}
