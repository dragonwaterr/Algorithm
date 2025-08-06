import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 내리막 길
    static final int[][] dt = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int n, m;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int x, int y) {
        if(x == n-1 && y == m-1) return 1;
        if(dp[x][y] != -1) return dp[x][y];

        dp[x][y] = 0;
        int h = map[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x + dt[i][0];
            int ny = y + dt[i][1];

            if(nx < 0 || nx > n-1 || ny < 0 || ny > m-1) continue;
            if(map[nx][ny] >= h) continue;

            dp[x][y] += dfs(nx, ny);
        }

        return dp[x][y];
    }
}
