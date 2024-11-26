import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        for(int i = 1; i < n+1; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, weight));
            graph[to].add(new Node(from, weight));
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int[] distFrom1 = dijkstra(1);
        int[] distFromV1 = dijkstra(v1);
        int[] distFromV2 = dijkstra(v2);

        // 형변환 안하면 int 끼리 연산하고 - 값이 loute 에 들어갈 수 있음
        long loute1 = (long) distFrom1[v1] + distFromV1[v2] + distFromV2[n]; // 1 -> v1 -> v2 -> n
        long loute2 = (long) distFrom1[v2] + distFromV2[v1] + distFromV1[n]; // 1 -> v2 -> v1 -> n

        long answer = Math.min(loute1, loute2);

        if(answer >= Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);
    }

    static class Node {
        int index;
        int weight;
        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }

    static int n, e;
    static int v1, v2;
    static ArrayList<Node>[] graph;

    static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.weight - n2.weight);

        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(cur.weight > dist[cur.index]) continue;

            for(Node n : graph[cur.index]) {
                if(dist[n.index] > n.weight + cur.weight) {
                    dist[n.index] = n.weight + cur.weight;
                    pq.offer(new Node(n.index, dist[n.index]));
                }
            }
        }
       return dist;
    }
}