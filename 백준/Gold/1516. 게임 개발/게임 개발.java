import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    // 게임 개발
    static int n;
    static int[] minTime;
    static int[] expense;
    static int[] indegree;
    static ArrayList<ArrayList<Integer>> tech;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        expense = new int[n+1];
        minTime = new int[n+1];
        indegree = new int[n+1];
        tech = new ArrayList<>(n+1);
        for(int i = 0; i <= n; i++)
            tech.add(new ArrayList<>());

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            expense[i] = Integer.parseInt(st.nextToken());
            minTime[i] = expense[i];
            while(true) {
                int cur = Integer.parseInt(st.nextToken());
                if(cur == -1) break;
                tech.get(cur).add(i); 
                indegree[i]++;
            }
        }

        topSort();

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            sb.append(minTime[i]).append("\n");
        }
        System.out.println(sb);
    }

    static void topSort() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            if (indegree[i] == 0) q.add(i);
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int dest : tech.get(cur)) {
                minTime[dest] = Math.max(minTime[cur] + expense[dest], minTime[dest]);
                indegree[dest]--;
                if(indegree[dest] == 0) q.add(dest);
            }
        }
    }
}