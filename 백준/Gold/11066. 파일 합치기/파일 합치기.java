import java.io.IOException;

public class Main {
    // 파일 합치기
    private static int t, k;
    private static int[][] dp;
    private static int[] sum;
    private static int[] arr;
    private static StringBuilder sb;

    // 읽기 메소드
    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 47) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    // 초기화 메소드
    private static void init() throws IOException {
        k = read();
        sum = new int[k+1];
        dp = new int[k+1][k+1];
        arr = new int[k+1];
        for(int i = 1; i <= k; i++) {
            arr[i] = read();
            sum[i] = sum[i-1] + arr[i];
        }
    }

    // 처리 메소드
    private static int solution() {
        for (int n = 1; n <= k; n++) {
            for (int from = 1; from + n <= k; from++) {
                int to = from + n;
                dp[from][to] = Integer.MAX_VALUE;
                for (int divide = from; divide < to; divide++) {
                    dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide + 1][to] + sum[to] - sum[from - 1]);
                }
            }
        }
        return dp[1][k];
    }

    // 정답 출력 메소드
    private static void printAnswer(int total) {
        sb.append(total).append("\n");
    }

    public static void main(String[] args) throws IOException {
        t = read();
        sb = new StringBuilder();
        while(t --> 0) {
            init();
            printAnswer(solution());
        }
        System.out.println(sb.toString());
    }
}