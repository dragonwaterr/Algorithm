import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Vertex implements Comparable<Vertex> {
    int end;
    int weight;

    Vertex(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
    @Override
    public int compareTo(Vertex o) {
        return weight - o.weight;
    }
}

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Vertex>> arr; // 인접리스트
    static int[] dist; // 시작점 - 다른 모든점 최단거리 갱신할 배열
    static boolean[] check; // 방문한 노드 체크할 배열

    // 다익스트라 알고리즘
    static void dijkstra(int start) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Vertex now = pq.poll();
            int cur = now.end;

            if(!check[cur]) {
                check[cur] = true;
                // 현재 노드와 연결된 다른 노드들까지의 dist갱신
                for(Vertex nd : arr.get(cur)) {
                    if(dist[nd.end] > dist[cur] + nd.weight) {
                        dist[nd.end] = dist[cur] + nd.weight;
                        pq.add(new Vertex(nd.end, dist[nd.end]));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int std_node = Integer.parseInt(br.readLine());

        arr = new ArrayList<>();
        dist = new int[N + 1];
        check = new boolean[N + 1];

        // dist배열 INF로 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i <= N; i++)
            arr.add(new ArrayList<>());

        // 인접리스트
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // Arraylist의 start 번째 인덱스에 있는 Arraylist 에
            // end, weight 정보를 가진 노드 삽입
            arr.get(start).add(new Vertex(end, weight));
        }

        br.close();
        dijkstra(std_node);
        for(int i = 1; i < N+1; i++) {
            if(dist[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }
}