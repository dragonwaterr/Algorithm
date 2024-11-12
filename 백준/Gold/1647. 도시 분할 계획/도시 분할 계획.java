import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    // 도시 분할 계획
    static int n, m, countRoutes, answer = 0;
    static PriorityQueue<Route> pq;
    static int[] parent;
    
    // 길 클래스
    static class Route { // 양방향 길이라 사실 from to 는 없음
        int house1;
        int house2;
        int cost;
        Route(int house1, int house2, int cost) {
            this.house1 = house1;
            this.house2 = house2;
            this.cost = cost;
        }
    }
    
    // 읽기 메소드
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 47) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    // 초기화 메소드
    static void init() throws IOException {
        n = read();
        m = read();

        parent = new int[n+1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        while(m --> 0) {
            int h1 = read();
            int h2 = read();
            int cost = read();
            pq.add(new Route(h1, h2, cost));
        }
    }
    
    // 처리 메소드
    static void solution() {
        countRoutes = 0; 
        
        // n 개의 정점이 모두 연결되려면 n-1 개의 route 필요
        while(countRoutes != n-1) {
            Route cur = !pq.isEmpty() ? pq.poll() : null;
            
            int gn1 = find(cur.house1);
            int gn2 = find(cur.house2);

            // 사이클이 존재한다면 넘어간다
            if(gn1 == gn2) continue;

            //
            parent[gn2] = parent[gn1];
            answer += cur.cost;
            countRoutes++;

            if(countRoutes == n-1) 
                answer -= cur.cost;
        }
    }
    
    // x 번 정점이 속한 그래프의 루트번호를 리턴한다
    static int find(int x) {
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    // 정답 출력 메소드
    static void printAnswer() {
        System.out.println(answer);
    }
    
    public static void main(String[] args) throws IOException {
        init();
        solution();
        printAnswer();
    }
}