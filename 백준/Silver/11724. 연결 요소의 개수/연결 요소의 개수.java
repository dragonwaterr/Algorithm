import java.io.*;
import java.util.StringTokenizer;

public class Main {
    // 연결 요소의 개수
    static final int MAX_N = 1001; // N의 최대입력값
    static int N, M; // 노드와 간선의 개수
    static int[][] graph = new int[MAX_N][MAX_N]; // 서로 연결된 간선을 나타낼 그래프배열
    static boolean[] visited = new boolean[MAX_N]; // 연결된 노드를 표현할 방문배열
    static int cnt = 1; // 연결요소의 숫자를 셀 변수

    static void dfs(int node) {
        visited[node] = true;

        for(int next = 1; next <= N; next++) {
            if(!visited[next] && graph[node][next] != 0) {
                dfs(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str1 = br.readLine();
        StringTokenizer st = new StringTokenizer(str1);
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());

        for(int i=0; i<M; i++) {
            String str2 = br.readLine();
            st = new StringTokenizer(str2);
            int u = Integer.valueOf(st.nextToken());
            int v = Integer.valueOf(st.nextToken());
            graph[u][v] = graph[v][u] = 1;
        }
        dfs(1);
        // 1을 기준으로 dfs가 끝나고,
        // 만약 visited 배열에 false가 있으면 거기부터 다시 dfs시작하고 cnt++
        // 단, 이렇게 하면 초기 cnt값이 1이어야함
        for(int i=2; i<=N; i++) {
            if(!visited[i]) {
                dfs(i);
                cnt++;
            }
        }
        bw.write(cnt+"\n");
        bw.flush();
    }
}