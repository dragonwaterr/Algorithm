import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    // 게리맨더링
    
    static final int MAX = 987654321;
    static int answer = MAX;
    static int n, cnt;
    
    static int[] group;
    static int[] population;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;

    static int pops[] = new int[2];

    static void dfs(int node, int depth, int limitSize, int groupNum) {
        pops[groupNum] += population[node];

        if (depth == limitSize) {
            return;
        }

        for (int next : adj[node]) {
            if (group[next] == groupNum && !visited[next]) {
                cnt++;
                visited[next] = true;
                dfs(next, depth + 1, limitSize, groupNum);
            }
        }
    }

    static void comb_by_size(int size) { 
        if (size == 0) {
            comb(0, 0, 0);
            return;
        }
        comb(2, 0, size);
    }

    static void renew(int p1, int p2) {
        if (p1 == -1 || p2 == -1) return;

        answer = Math.min(answer, Math.abs(p1 - p2));
    }

    static void comb(int idx, int depth, int limit) {

        // 뽑아야 할 숫자보다 뒤에 남은 숫자가 적다면
        if (n - idx + 1 < limit - depth) { 
            return;
        }

        if (depth == limit) {
            cnt = 2; 
            visited = new boolean[n + 1]; 
            
            check_g1(limit + 1);
            check_g2(n - (limit + 1));

            if (cnt == n) {
                renew(pops[1], pops[0]);
            }

            return;
        }

        for (int i = idx; i <= n; i++) { 
            group[i] = 1;
            comb(i + 1, depth + 1, limit);
            group[i] = 0;
        }
    }


    static void check_g1(int limitSize) {
        pops[1] = 0;
        visited[1] = true;
        dfs(1, 1, limitSize, 1);
    }

    static void check_g2(int limitSize) {
        int depth = 0;
        pops[0] = 0;
        for (int node = 2; node <= n; node++) {
            if (group[node] == 0 && !visited[node]) {
                visited[node] = true;
                dfs(node, ++depth, limitSize, 0);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        population = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            int sectors = Integer.parseInt(st.nextToken());
            for (int j = 0; j < sectors; j++) {
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        group = new int[n + 1];
        group[1] = 1; // 1이 있는 그룹을 항상 1로 저장 <-> 0 저장

        for (int size = 1; size < n; size++) {
            comb_by_size(size - 1);
        }


        if (answer == MAX) {
            System.out.println(-1);
            return;
        }

        System.out.println(answer);
    }
}