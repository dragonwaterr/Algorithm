import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 웜홀
    static int[] dist;
    static ArrayList<edges>[] bus;
    static StringTokenizer st;
    
    static class edges {
        int v;
        int val;

        edges(int v, int val) {
            this.v = v;
            this.val = val;
        }
    }

    static boolean TimeMachine(int N, int start) {
        Arrays.fill(dist, Integer.MAX_VALUE); // 최단거리 배열을 INF로 갱신
        dist[start] = 0;
        boolean update;

        for(int i = 1; i <= N; i++) { // 정점의 수 -1 만큼 반복
            update = false;

            for(int j = 1; j <= N; j++) {
                for(edges e : bus[j]) {
                    if (dist[j] != Integer.MAX_VALUE && dist[e.v] > dist[j] + e.val) {

                        if(i == N)
                            return true;

                        dist[e.v] = dist[j] + e.val;
                        update = true;
                    }
                }
            }
            // 더 이상 최단거리 갱신이 일어나지 않는다면
            if(!update)
                break;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < T; i++) { // 테스트케이스 만큼 반복
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()); // 정점의 수
            int b = Integer.parseInt(st.nextToken()); // 간선의 수
            int c = Integer.parseInt(st.nextToken()); // 웜홀의 수
            int d = b + c;

            dist = new int[a + 1]; // 최단거리를 담을 배열
            bus = new ArrayList[a + 1];

            for(int j = 0; j <= a; j++) {
                bus[j] = new ArrayList<>();
            }

            int j = 0;
            for(; j < b; j++) { // 양의 가중치 간선 입력
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());

                bus[u].add(new edges(v, val)); // 도로는 양방향 입력
                bus[v].add(new edges(u, val));
            }

            for(; j < d; j++) { // 음의 가중치 간선 입력
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());

                bus[u].add(new edges(v, -val)); // 웜홀은 단방향 입력
            }


            boolean isMinusCycle = false; // 음수 사이클 발생 여부
            for(int k = 1; k <= a; k++) { // 시작점이 1 ~ a인 경우를 모두 체크
                if(TimeMachine(a, k)) { // 정점의 개수와 시작점을 넘겨주기
                    isMinusCycle = true;
                    sb.append("YES\n");
                    break;
                }
            }

            if(!isMinusCycle)
                sb.append("NO\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}