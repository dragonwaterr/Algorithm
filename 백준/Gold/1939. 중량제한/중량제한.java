import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 중량제한
    static class Bridge {
        int d1, d2, w;
        public Bridge(int d1, int d2, int w) {
            this.d1 = d1;
            this.d2 = d2;
            this.w = w;
        }
    }

    static int fac1, fac2;
    static int[] parent;
    static HashMap<Integer, List<int[]>> spanTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Bridge> pq = new PriorityQueue<>((o1, o2) -> o1.w > o2.w ? -1 : 1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            Bridge bridge = new Bridge(from, to, w);
            pq.add(bridge);
        }
        
        st = new StringTokenizer(br.readLine());
        fac1 = Integer.parseInt(st.nextToken());
        fac2 = Integer.parseInt(st.nextToken());
        if(fac1 > fac2) {
            fac1 = fac1 ^ fac2 ^ (fac2 = fac1); // swap
        }

        parent = new int[n+1];
        for(int i = 1; i < n+1; i++) {
            parent[i] = i;
        }
        
        // 최대 스패닝 트리를 만들고
        spanTree = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            spanTree.put(i, new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            Bridge b1 = pq.poll();
            if(find(b1.d1) != find(b1.d2)) {
                union(b1.d1, b1.d2);
                spanTree.get(b1.d1).add(new int[]{b1.d2, b1.w});
                spanTree.get(b1.d2).add(new int[]{b1.d1, b1.w});
            }
        }
        
        // A to B bfs 를 돌린다
        int answer = bfs();
        System.out.println(answer);
    }
    
    static int bfs() {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[parent.length];
        queue.add(new int[]{fac1, Integer.MAX_VALUE});
        visited[fac1] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int weight = current[1];

            if (node == fac2) return weight;

            for (int[] neighbor : spanTree.get(node)) {
                int nextNode = neighbor[0];
                int nextWeight = neighbor[1];
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.add(new int[]{nextNode, Math.min(weight, nextWeight)});
                }
            }
        }

        return 0;
    }
    
    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x <= y) parent[y] = x;
        else parent[x] = y;
    }
}