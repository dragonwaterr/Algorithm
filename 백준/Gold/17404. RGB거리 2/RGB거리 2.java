import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // RGB거리 2
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] rgb = new int[n+1][3];
        int[][] dp;

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            rgb[i][0] = Integer.parseInt(st.nextToken());
            rgb[i][1] = Integer.parseInt(st.nextToken());
            rgb[i][2] = Integer.parseInt(st.nextToken());
        }

        int answer = 987654321;

        for (int i = 0; i < 3; i++) {
            dp = new int[n+1][3];
            dp[1][0] = rgb[1][0];
            dp[1][1] = rgb[1][1];
            dp[1][2] = rgb[1][2];

            dp[2][i] = 987654321;
            for (int j = 0; j < 3; j++) {
                if(i == j) continue;
                dp[2][j] = dp[1][i] + rgb[2][j];
            }

            for (int j = 3; j <= n; j++) {
                dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2]) + rgb[j][0];
                dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2]) + rgb[j][1];
                dp[j][2] = Math.min(dp[j-1][0], dp[j-1][1]) + rgb[j][2];
            }

            for (int j = 0; j < 3; j++) {
                if(i == j) continue;
                answer = Math.min(answer, dp[n][j]);
            }
        }
        System.out.println(answer);
    }
}
