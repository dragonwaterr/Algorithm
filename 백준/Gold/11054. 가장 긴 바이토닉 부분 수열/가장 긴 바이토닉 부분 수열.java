import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 가장 긴 바이토닉 부분 수열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        int[] rdp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        for (int i = n-1; i >= 0; i--) {
            rdp[i] = 1;
            for (int j = n-1; j > i; j--) {
                if(arr[j] < arr[i]) {
                    rdp[i] = Math.max(rdp[i], rdp[j] + 1);
                }
            }
        }

        int answer = 1;
        for (int i = 0; i < n; i++) {
            int dc = 0;
            for (int j = i+1; j < n; j++) {
                if(arr[i] > arr[j]) {
                    dc = Math.max(dc, rdp[j]);
                }
            }
            answer = Math.max(dp[i] + dc, answer);
        }

        System.out.println(answer);
    }
}