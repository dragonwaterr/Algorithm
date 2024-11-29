import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 키 순서
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
        }

        floyd(n, graph);
        System.out.println(checkLine(n, graph));
    }

    static void floyd(int n, int[][] graph) {
        for (int k = 1; k <= n; k++)
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n; j++)
                    if (graph[i][j] > graph[i][k] + graph[k][j])
                        graph[i][j] = graph[i][k] + graph[k][j];
    }

    static int checkLine(int n, int[][] graph) {

        int answer = 0;

        // 1. 모든 노드마다 자신보다 앞뒤에 있는 노드 수를 세자
        // degrees[n][0] == n 보다 뒤에 있는 노드 숫자, degrees[n][1] == n 보다 앞에 있는 노드 숫자
        int[][] degrees = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] != INF) {
                    degrees[j][0]++;
                    degrees[i][1]++;
                }
            }
        }

        // 2. in + out == n-1 인 노드 수를 구해서 리턴
        for (int i = 1; i <= n; i++)
            if (degrees[i][0] + degrees[i][1] == n - 1) answer++;

        return answer;
    }
}