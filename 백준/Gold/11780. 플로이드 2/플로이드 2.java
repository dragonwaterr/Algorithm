import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    // 플로이드 2
    static final int INF = 100001;
    static int n;
    static int[][] cost;
    static int[][] trace;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        cost = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                cost[i][j] = INF;
            }
        }

        int e = Integer.parseInt(br.readLine());
        for (int i = 0; i < e; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            cost[a][b] = Math.min(c, cost[a][b]);
        }

        trace = new int[n + 1][n + 1]; 
        for (int i = 1; i <= n; i++) {
            Arrays.fill(trace[i], i);
            trace[i][i] = 0;
        }

        floyd();
        printCostMap(sb);
        printTrace(sb);

        bw.write(sb.toString());
        bw.flush();
    }

    static void floyd() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (i == k) continue;
                for (int j = 1; j <= n; j++) {
                    if (k == j || i == j) continue;
                    if (cost[i][j] > cost[i][k] + cost[k][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                        trace[i][j] = trace[k][j]; 
                    }
                }
            }
        }
    }
    static void printCostMap(StringBuilder sb) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (cost[i][j] == INF) {
                    sb.append("0 ");
                    continue;
                }
                sb.append(cost[i][j]).append(" ");
            }
            sb.append("\n");
        }
    }

    static void printTrace(StringBuilder sb) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                if (i == j || cost[i][j] == INF) { // 두 번째 조건을 간과했다
                    sb.append("0\n");
                    continue;
                }

                Stack<Integer> stack = new Stack<>();
                stack.push(j); // 도착점
                int y = j;
                while(trace[i][y] != i) { // 사이에 다른 경로가 있다면 출발점이 나올 때까지 체크
                    stack.push(trace[i][y]);
                    y = trace[i][y];
                }
                stack.push(i); // 출발점

                sb.append(stack.size()).append(" ");
                while(!stack.isEmpty()) {
                    sb.append(stack.pop()).append(" ");
                }
                sb.append("\n");
            }
        }
    }
}