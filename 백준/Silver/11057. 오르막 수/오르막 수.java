import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 오르막 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[11][n+1];

        for (int i = 1; i <= 10; i++) {
            dp[i][1] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= 10; j++) {
                dp[j][i] = (dp[j-1][i] + dp[j][i-1]) % 10007;
            }
        }

        int answer = 0;
        for(int i = 1; i <= 10; i++) {
            answer += dp[i][n] % 10007;
        }
        System.out.println(answer % 10007);
    }
}