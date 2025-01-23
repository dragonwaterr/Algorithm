import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 트리
    static int n, q;
    static int[][] parent; // 0 : 현재, 1 : 예약
    static int[][] query;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        parent = new int[2][n+1];

        Arrays.fill(parent[0], -1);
        parent[1][1] = 1;
        for(int i = 2; i <= n; i++) {
            parent[1][i] = Integer.parseInt(br.readLine());
        }

        query = new int[q + n][3];
        int ptr = q + n - 1;
        for(int i = 0; i < q + n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int qType = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());

            query[ptr][0] = qType;
            query[ptr][1] = n1;

            if(qType == 1) {
                query[ptr][2] = Integer.parseInt(st.nextToken());
            }

            ptr--;
        }

        sb = new StringBuilder();
        boolean[] answer = new boolean[q];
        int ptr2 = q-1;

        ptr = 0;
        while(++ptr < q + n) {
            int[] cur = query[ptr];
            int n1 = cur[1];
            
            if(cur[0] == 0) {
                union(n1, parent[1][n1]);
            } else {
                int n2 = cur[2];
                int p1 = find(n1);
                int p2 = find(n2);

                if(p1 == p2) answer[ptr2] = true;
                else answer[ptr2] = false;
                ptr2--;
            }
        }

        for(boolean yes : answer) {
            if(yes) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }

        System.out.println(sb);
    }

    static int find(int x) {
        if(parent[0][x] < 0) return x;
        return parent[0][x] = find(parent[0][x]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        parent[0][pb] = pa;
    }
}