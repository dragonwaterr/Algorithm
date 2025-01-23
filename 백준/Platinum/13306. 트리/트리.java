import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    // 트리
    static int n, q;
    static int[][] parent; // 0 : 현재, 1 : 예약
    static Stack<int[]> query;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        parent = new int[2][n+1];

        parent[0][1] = 1;
        parent[1][1] = 1;
        for(int i = 2; i <= n; i++) {
            parent[1][i] = Integer.parseInt(br.readLine());
            parent[0][i] = i;
        }

        query = new Stack<>();
        for(int i = 0; i < q + n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int qType = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());

            if(qType == 0) {
                query.add(new int[]{n1});
            } else {
                int n2 = Integer.parseInt(st.nextToken());
                query.add(new int[]{n1, n2});
            }
        }

        sb = new StringBuilder();
        String[] answer = new String[q];
        int ptr = q-1;

        while(!query.isEmpty()) {
            int[] cur = query.pop();
            int n1 = cur[0];
            if(cur.length == 1) {
                parent[0][n1] = parent[1][n1];
            } else {
                int n2 = cur[1];
                int p1 = find(n1);
                int p2 = find(n2);

                if(p1 == p2) answer[ptr] = "YES";
                else answer[ptr] = "NO";
                ptr--;
            }
        }

        for(String str : answer) {
            sb.append(str).append("\n");
        }

        System.out.println(sb);
    }

    static int find(int x) {
        if(x == parent[0][x]) return x;
        return find(parent[0][x]);
    }
}
