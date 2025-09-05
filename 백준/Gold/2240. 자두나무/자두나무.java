import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 자두나무
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        
        int firstJadu = Integer.parseInt(br.readLine());
        int[] jadu = new int[t+1];
        for (int i = 2; i <= t; i++) {
            jadu[i] = Integer.parseInt(br.readLine());
        }

        // x번 움직인 자두가 y초에 z번 나무에 서있을 때 먹은 최대 자두 개수를 저장
        int[][][] dp = new int[w+1][t+1][2];

        if(firstJadu == 1) {
            dp[0][1][0]++;
        } else {
            dp[1][1][1]++;
        }

        for (int i = 2; i <= t; i++) {
            
            if(jadu[i] == 1) {
                dp[0][i][0] = dp[0][i-1][0] + 1;
                dp[0][i][1] = dp[0][i-1][1];
            } else {
                dp[0][i][1] = dp[0][i-1][1] + 1;
                dp[0][i][0] = dp[0][i-1][0];
            }
            
            for (int j = 1; j <= Math.min(i, w); j++) {
                if(jadu[i] == 1) {
                    dp[j][i][0] = Math.max(dp[j-1][i-1][1], dp[j][i-1][0]) + 1;
                    dp[j][i][1] = Math.max(dp[j-1][i-1][0], dp[j][i-1][1]);
                    continue;
                }
                dp[j][i][1] = Math.max(dp[j-1][i-1][0], dp[j][i-1][1]) + 1;
                dp[j][i][0] = Math.max(dp[j-1][i-1][1], dp[j][i-1][0]);
            }
        }

        int answer = Math.max(dp[0][1][0], dp[1][1][1]);
        for (int i = 0; i <= w; i++) {
            answer = (i+2) % 2 == 0
                ? Math.max(answer, dp[i][t][0])
                : Math.max(answer, dp[i][t][1]);
        }

        System.out.println(answer);
    }
}