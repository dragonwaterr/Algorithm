import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    // 수열과 쿼리 15
    static int n;
    static int[] sequence;
    static PriorityQueue<node> pq;
    
    static class node {
        int val;
        int idx;
        public node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sequence = new int[n+1];
        
        pq = new PriorityQueue<>((o1, o2) ->
                o1.val == o2.val ? o1.idx - o2.idx : o1.val - o2.val
        );
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
            pq.add(new node(sequence[i], i));
        }
        
        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            if(q == 2) {
                sb.append(getMinIdx()).append("\n");
                continue;
            } 
            int idx = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            sequence[idx] = val;
            pq.add(new node(val, idx));
        }
        System.out.println(sb);
    }
    
    static int getMinIdx() {
        node nd = pq.peek();
        while(nd.val != sequence[nd.idx]) {
            pq.poll();
            nd = pq.peek();
        }
        return nd.idx;
    }
}