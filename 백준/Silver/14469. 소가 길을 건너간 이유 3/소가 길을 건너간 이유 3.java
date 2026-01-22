import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n  = Integer.parseInt(br.readLine());
        int[][] cows = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int enter = Integer.parseInt(st.nextToken());
            int latency = Integer.parseInt(st.nextToken());
            cows[i][0]= enter;
            cows[i][1] = latency;
        }

        Arrays.sort(cows, ((o1, o2) -> o1[0] - o2[0]));

        int result = 0;
        for (int i = 0; i < n; i++) {
            if(result <= cows[i][0]) { // 소 입장시간이 더 늦다면
                result = cows[i][0];
            }
            result += cows[i][1];
        }

        System.out.println(result);
    }
}
