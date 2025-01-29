import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 쉬운 계단 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[10][n + 1];

        for (int i = 1; i < 10; i++) {
            dp[i][1] = 1;
        }

        for (int i = 2; i <= n; i++) {
            dp[0][i] = dp[1][i - 1];
            for (int j = 1; j < 9; j++) {
                dp[j][i] = (dp[j - 1][i - 1] + dp[j + 1][i - 1]) % 1_000_000_000;
            }
            dp[9][i] = dp[8][i - 1];
        }

        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer = (answer + dp[i][n]) % 1_000_000_000;
        }
        System.out.println(answer);
    }
}