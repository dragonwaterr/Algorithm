import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 선물 전달
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[n+2];
        dp[0] = dp[1] = 0;
        dp[2] = 1;

        // D(n) = (n-1) * (D(n-1)+D(n-2));
        for (int i = 3; i <= n; i++) {
            dp[i] = (i-1)*(dp[i-1] + dp[i-2]) % 1_000_000_000L;
        }

        System.out.println(dp[n]);
    }
}
