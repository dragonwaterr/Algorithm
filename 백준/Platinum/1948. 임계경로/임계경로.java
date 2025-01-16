import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 임계경로
    static int n, m;
    static int start, finish;
    static ArrayList<HashMap<Integer, Integer>> forward;
    static ArrayList<HashMap<Integer, Integer>> reverse;
    static int[] dist; // 각 도시까지의 최대 비용

    static class Buddy implements Comparable<Buddy> {
        int city, time;
        public Buddy(int city, int time) {
            this.city = city; // 현재 도시
            this.time = time; // 누적 시간
        }
        @Override
        public int compareTo(Buddy o) {
            return o.time - this.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dist = new int[n + 1];
        forward = new ArrayList<>(n + 1);
        reverse = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            forward.add(new HashMap<>());
            reverse.add(new HashMap<>());
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            int former = forward.get(from).getOrDefault(to, 0);
            forward.get(from).put(to, Math.max(forward.get(from).getOrDefault(to, 0), weight));
            reverse.get(to).put(from, Math.max(reverse.get(to).getOrDefault(from, 0), weight));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        finish = Integer.parseInt(st.nextToken());
        
        // 다익스트라 : 최대 시간 계산
        Buddy buddy = new Buddy(start, 0);
        PriorityQueue<Buddy> pq = new PriorityQueue<>();
        pq.add(buddy);
        while (!pq.isEmpty()) {
            Buddy cur = pq.poll();

            if(cur.time < dist[cur.city]) continue;

            for (Map.Entry<Integer, Integer> next : forward.get(cur.city).entrySet()) {
                int nextCity = next.getKey();
                int nextTime = next.getValue();

                if (dist[cur.city] + nextTime > dist[nextCity]) {
                    dist[nextCity] = cur.time + nextTime;
                    pq.add(new Buddy(nextCity, dist[nextCity]));
                }
            }
        }

        // 역추적
        Set<Integer> set = new HashSet<>(); // 임계경로 간선들을 저장
        Queue<Buddy> q = new ArrayDeque<>();
        q.add(new Buddy(finish, dist[finish]));
        while (!q.isEmpty()) {
            Buddy cur = q.poll();

            for (Map.Entry<Integer, Integer> prev : reverse.get(cur.city).entrySet()) {
                int prevCity = prev.getKey();
                int prevTime = prev.getValue();

                if (dist[cur.city] - prevTime == dist[prevCity]) {
                    if (!set.contains(cur.city * 100000 + prevCity)) {
                        set.add(cur.city * 100000 + prevCity);
                        q.add(new Buddy(prevCity, dist[prevCity])); // 맞네.. 이거네
                    }
                }
            }
        }
        
        System.out.println(dist[finish]);
        System.out.println(set.size());
    }
}