import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            
            int n = Integer.parseInt(br.readLine());
            int[] vals = new int[n+1];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                vals[j] = Integer.parseInt(st.nextToken()); 
            }
            
            int m = Integer.parseInt(br.readLine()); 
            
            int[][] dp = new int[n+1][m+1];
            dp[0][0] = 1;
            
            for(int j = 1; j <= n; j++) {
                dp[j][0] = 1;
                for(int k = 1; k <= m; k++) {
                    if(k - vals[j] < 0) {
                        dp[j][k] = dp[j-1][k];
                        continue;
                    }
                    dp[j][k] = dp[j-1][k] + dp[j][k - vals[j]];
                }
            }


            System.out.println(dp[n][m]);
        }
    }
}