import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    // ACM Craft
    static int n, k, target;
    static int[] expense;
    static int[] minTime;
    static int[] indegree;
    static ArrayList<ArrayList<Integer>> edge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            edge = new ArrayList<>(n+1);
            indegree = new int[n+1];
            expense = new int[n+1];
            minTime = new int[n+1];
            
            k = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            edge.add(new ArrayList<>());
            for(int i = 1; i <= n; i++) {
                expense[i] = Integer.parseInt(st.nextToken());
                edge.add(i, new ArrayList<>());
            }
            
            for(int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                edge.get(from).add(to);
                indegree[to]++;
            }
            target = Integer.parseInt(br.readLine());


            ArrayDeque<Integer> q = new ArrayDeque<>();
            for(int i = 1; i <= n; i++) {
                if(indegree[i] == 0) {
                    q.add(i);
                    minTime[i] = expense[i];
                }
            }

            Loop:
            while(!q.isEmpty()) {
                int from = q.poll();
                int outdegree = edge.get(from).size();
                for(int i = outdegree - 1; i >= 0; i--) {
                    int to = edge.get(from).get(i);
                    minTime[to] = Math.max(minTime[from] + expense[to], minTime[to]);
                    edge.get(from).remove(i);
                    indegree[to]--;

                    if(indegree[to] == 0) {
                        if(to == target) break Loop;
                        q.add(to);
                    }
                }
            }
            sb.append(minTime[target]).append("\n");
        }
        System.out.println(sb);
    }
}