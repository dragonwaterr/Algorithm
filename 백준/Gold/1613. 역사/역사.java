import java.io.*;
import java.util.StringTokenizer;

public class Main {
    // 역사
    static int[][] graph;
    static int INF = 987654321;

    static void FW(int size) {
        for(int k=0; k < size; k++) { // 중간에 거치게될 노드를 추가
            for(int i=0; i < size; i++) {
                for(int j=0; j < size; j++) {
                    if(i == j) continue;
                    if(graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str0 = br.readLine();
        StringTokenizer st = new StringTokenizer(str0);

        int N = Integer.valueOf(st.nextToken()); // 노드의 수
        int M = Integer.valueOf(st.nextToken()); // 간선의 수
        graph = new int[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                graph[i][j] = INF;
            }
        }

        for(int i=0; i < M; i++) {
            String str = br.readLine();
            st = new StringTokenizer(str);

            int a = Integer.valueOf(st.nextToken()); // 먼저 일어난 사건
            int b = Integer.valueOf(st.nextToken()); // 뒤에 일어난 사건

            graph[a-1][b-1] = 1;
        }

        FW(N);

        int T = Integer.valueOf(br.readLine());
        for(int i=0; i < T; i++) {
            String str2 = br.readLine();
            st = new StringTokenizer(str2);

            int c = Integer.valueOf(st.nextToken());
            int d = Integer.valueOf(st.nextToken());

            if(graph[c-1][d-1] == INF) {
                if(graph[d-1][c-1] == INF) System.out.println(0);
                else System.out.println(1);
            }
            else System.out.println(-1);
        }
    }
}