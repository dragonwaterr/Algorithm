import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 서울에서 경산까지
    static class Path {
        int t;
        int v;

        Path(int t, int v) {
            this.t = t;
            this.v = v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Path[][] paths = new Path[2][n + 1]; // 0 : 도보  / 1 : 자전거
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int t1 = Integer.parseInt(st.nextToken());
            int v1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            paths[0][i] = new Path(t1, v1);
            paths[1][i] = new Path(t2, v2);
        }

        int[][] dp = new int[n + 1][k + 1];

        dp[1][paths[0][1].t] = paths[0][1].v;
        dp[1][paths[1][1].t] = Math.max(dp[1][paths[1][1].t], paths[1][1].v);

        for (int i = 2; i <= n; i++) {
            for (int q = 0; q < 2; q++) {
                Path path = paths[q][i];
                int pt = path.t;
                int pv = path.v;
                for (int j = 1; j <= k; j++) {
                    if (dp[i - 1][j] > 0) {
                        if (j + pt <= k) {
                            dp[i][j + pt] = Math.max(dp[i][j + pt], dp[i-1][j] + pv);
                        }
                    }
                }
            }
        }
        int maxV = -1;
        for (int i = 1; i <= k; i++) {
            maxV = Math.max(maxV, dp[n][i]);
        }
        System.out.println(maxV);
    }
}
