import java.io.*;
import java.util.*;

public class Main {
    static int n, m, start, finish;
    static ArrayList<Node>[] forward;
    static ArrayList<Node>[] reverse;

    static class Node implements Comparable<Node> {
        int city, time;
        public Node(int city, int time) {
            this.city = city;
            this.time = time;
        }
        public int compareTo(Node o) {
            return Integer.compare(o.time, this.time); 
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        int[] dist = new int[n + 1];
        int[] indegree = new int[n + 1];
        forward = new ArrayList[n + 1];
        reverse = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            forward[i] = new ArrayList<Node>();
            reverse[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            forward[from].add(new Node(to, weight));
            reverse[to].add(new Node(from, weight));
            indegree[to]++;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        finish = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Node node : forward[cur]) {
                indegree[node.city]--;
                dist[node.city] = Math.max(dist[node.city], dist[cur] + node.time);
                if (indegree[node.city] == 0) {
                    q.offer(node.city);
                }
            }
        }

        int edgeCount = 0;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> qReverse = new ArrayDeque<>();
        q.offer(finish);
        visited[finish] = true;

        while (!q.isEmpty()) {
            int cur =  q.poll();
            for(Node prev : reverse[cur]) {
                if(dist[prev.city] == dist[cur] - prev.time) {
                    edgeCount++;
                    if(!visited[prev.city]) {
                        q.offer(prev.city);
                        visited[prev.city] = true;
                    }
                }
            }
        }

        System.out.println(dist[finish]);
        System.out.println(edgeCount);
    }
}