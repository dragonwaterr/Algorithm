import java.io.*;
import java.util.*;

class Main {
    static class Node {
        int pos, dist, outTo;
        Node(int pos, int dist, int outTo) {
            this.pos = pos;
            this.dist = dist;
            this.outTo = outTo;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        Node[] list = new Node[N+1];
        ArrayList<ArrayList<Node>> indegree = new ArrayList<>(N+1);
        for(int i = 0; i <= N; i++) {
            indegree.add(i, new ArrayList<>());
        }
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N; i++) {
            int to = Integer.parseInt(st.nextToken());
            Node node = new Node(i, -1, to);
            list[i] = node;
            
            if(list[to] == null) list[to] = new Node(to, -1, -1);
            if(to == N) node.dist = 1;
            indegree.get(to).add(node);
        }
        
        st.nextToken(); // N 에서 나가는 간선은 버리기
        if(list[N] == null) {
            list[N] = new Node(N, 0, -1);
        } else {
            list[N].pos = N;
            list[N].dist = 0;
        }
        
        ArrayDeque<Node> dq = new ArrayDeque<>();
        for(Node node : indegree.get(N)) {
            dq.add(node);
        }
        
        while(!dq.isEmpty()) {
            Node cur = dq.remove();
            cur.dist = list[cur.outTo].dist + 1;
            for(Node node : indegree.get(cur.pos)) {
                dq.add(node);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            sb.append(list[i].dist).append("\n");
        }
        System.out.println(sb);
    }
}