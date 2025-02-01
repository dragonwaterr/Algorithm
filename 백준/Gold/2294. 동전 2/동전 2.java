import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 동전 2
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        final int MAX = 987654321;

        int[] dp = new int[k+1];
        Arrays.fill(dp, MAX);

        for(int i = 1; i <= n; i++) {
            int coin = Integer.parseInt(br.readLine());
            if(coin > k) continue; 
            dp[coin] = 1;

            for(int j = 1; j <= k; j++) {
                if(j - coin < 0 || dp[j - coin] == MAX) continue;

                dp[j] = Math.min(dp[j-coin] + 1, dp[j]);
            }
        }

        int answer = dp[k] == MAX ? -1 : dp[k];
        System.out.println(answer);

    }
}