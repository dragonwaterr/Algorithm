import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // 퇴사 2
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        
        // key = 종료일, value : (시작일, 보상) list
        HashMap<Integer, List<int[]>> map = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            map.computeIfAbsent(i + t - 1, k -> new ArrayList<>());
            List<int[]> valList = map.get(i + t - 1);
            valList.add(new int[]{i, p});
        }

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i-1];
            if(map.get(i) == null) continue;

            List<int[]> taskList = map.get(i);
            for(int[] task : taskList) {
                int from = task[0];
                int pay = task[1];

                dp[i] = Math.max(dp[i], dp[from - 1] + pay);
            }
        }

        System.out.println(dp[n]);
    }
}
