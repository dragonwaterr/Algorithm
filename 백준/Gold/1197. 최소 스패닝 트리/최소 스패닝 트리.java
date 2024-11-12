import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0; // mst의 가중치 합

        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        if(v == 1) {
            System.out.println(0);
            return;
        }

        // 0행 : 출발점, 1행 : 도착점, 2행 : 가중치
        parent = new int[v+1];
        for(int i = 1; i <= v; i++)
            parent[i] = i;

        int[][] edges = new int[e+1][3];
        edges[0][0] = edges[0][1] = 10001;
        edges[0][2] = 1000001; // 사용안하는 0번 인덱스는 정렬했을 때 가장 뒤로 가도록
        for(int i = 1; i <= e; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken()); 
            edges[i][1] = Integer.parseInt(st.nextToken()); 
            edges[i][2] = Integer.parseInt(st.nextToken()); 
        }

        Arrays.sort(edges, (o1, o2) -> {    // 가중치를 기준으로 오름차순 정렬
            return o1[2] - o2[2];
        });
        
        for(int i = 0; i < e; i++) {
            if(check(edges[i][0], edges[i][1])) { // 출발정점과 도착정점이 연결되어있는가?
                union(edges[i][0], edges[i][1]);
                sum += edges[i][2];
            }
        }
        System.out.println(sum);
    }
    
    static int[] parent;
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }
    static boolean check(int a, int b) {
        return find(a) != find(b);
    }
}