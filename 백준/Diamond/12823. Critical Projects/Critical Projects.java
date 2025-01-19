import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    // Critical_Projects

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = read();
        int m = read();

        int[] indegree = new int[n+1];
        ArrayList<Integer>[] edges = new ArrayList[n+1];
        for(int i = 0; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++) {
            int from = read();
            int to = read();
            edges[from].add(to);
            indegree[to]++;
        }

        int[] top = new int[n+1];
        int[] left = new int[n+1];
        int[] right = new int[n+1];
        Arrays.fill(right, n+1);

        int k = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
                top[i] = ++k;
            }
        }

        while(!queue.isEmpty()) {
            int cur =  queue.poll(); 
            for(int dest : edges[cur]) {

                indegree[dest]--;
                if(indegree[dest] == 0) {
                    queue.offer(dest);
                    top[dest] = ++k; 
                }

            }
        }

        for(int i = 1; i <= n; i++) {
            for(int j : edges[i]) {
                left[j] = Math.max(left[j], top[i]);
                right[i] = Math.min(right[i], top[j]);
            }
        }

        int[] top2node = new int[n+1];
        for(int i = 1; i <= n; i++) {
            top2node[top[i]] = i;
        }

        boolean[] nonCritical = new boolean[n+1];
        for(int i = 1; i <= n; i++) {
            int l1 = left[i] + 1;
            int l2 = top[i] - 1;
            for(int j = l1; j <= l2; j++) {
                nonCritical[top2node[j]] = true;
            }
            int r1 = top[i] + 1;
            int r2 = right[i] - 1;
            for(int j = r1; j <= r2; j++) {
                nonCritical[top2node[j]] = true;
            }
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            if(!nonCritical[i]) {
                cnt++;
                sb.append(i).append(" ");
            }
        }
        bw.write(cnt + "\n" + sb);
        bw.flush();
    }
}