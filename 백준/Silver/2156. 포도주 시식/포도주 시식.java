import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 포도주 시식
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n  = Integer.parseInt(br.readLine());
        int[][] dp = new int[3][n+1];

        int ptr = 0;
        while(++ptr < n+1) {
            int k = Integer.parseInt(br.readLine());
            // 안마실게
            dp[0][ptr] = Math.max(dp[0][ptr-1], Math.max(dp[1][ptr-1], dp[2][ptr-1]));
            // 마실게
            dp[1][ptr] = dp[0][ptr-1] + k;
            dp[2][ptr] = dp[1][ptr-1] + k;
        }
        System.out.println(Math.max(dp[0][n], Math.max(dp[1][n], dp[2][n])));
    }
}