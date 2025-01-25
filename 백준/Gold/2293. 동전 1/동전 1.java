import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 동전
    static int n, k, cnt;
    static int[] coins;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coins = new int[n];
        int[] dp = new int[k+1];

        for(int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1; 
        for(int i = 0; i < n; i++) {
            for(int j = 1; j <= k; j++) {
                if(j - coins[i] < 0) continue;

                dp[j] += dp[j - coins[i]];
            }
        }

        System.out.println(dp[k]);
    }
}