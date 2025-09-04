import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 뮤탈리스크
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] scv = new int[3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[scv[0] + 1][scv[1] + 1][scv[2] + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(check(scv[0], scv[1], scv[2]));
    }

    static int check(int hp1, int hp2, int hp3) {
        hp1 = Math.max(hp1, 0);
        hp2 = Math.max(hp2, 0);
        hp3 = Math.max(hp3, 0);

        if (hp1 == 0 && hp2 == 0 && hp3 == 0) {
            return 0;
        }

        if (dp[hp1][hp2][hp3] != -1) {
            return dp[hp1][hp2][hp3];
        }

        int result = 987654321;

        result = Math.min(result, check(hp1 - 9, hp2 - 3, hp3 - 1) + 1);
        result = Math.min(result, check(hp1 - 9, hp2 - 1, hp3 - 3) + 1);
        result = Math.min(result, check(hp1 - 3, hp2 - 9, hp3 - 1) + 1);
        result = Math.min(result, check(hp1 - 3, hp2 - 1, hp3 - 9) + 1);
        result = Math.min(result, check(hp1 - 1, hp2 - 3, hp3 - 9) + 1);
        result = Math.min(result, check(hp1 - 1, hp2 - 9, hp3 - 3) + 1);

        return dp[hp1][hp2][hp3] = result;
    }
}
