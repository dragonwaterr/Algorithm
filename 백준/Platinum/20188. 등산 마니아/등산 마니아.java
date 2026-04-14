import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());

        Node[] nodes = new Node[N + 1];
        Node root = new Node(1);
        nodes[1] = root;

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            if (nodes[n1] == null) nodes[n1] = new Node(n1);
            if (nodes[n2] == null) nodes[n2] = new Node(n2);
            adj.get(n1).add(n2);
            adj.get(n2).add(n1);
        }

        dfs(root, -1, adj, nodes);

        long answer = (long)(N - 1) * N * (N - 1) / 2;
        for (int i = 2; i <= N; i++) {
            long rest = N - nodes[i].childs;
            answer -= rest * (rest - 1) / 2;
        }
        System.out.println(answer);
    }

    static class Node {
        int num;
        int childs = 1;

        Node(int num) {
            this.num = num;
        }
    }

    static int dfs(Node cur, int parentNum, ArrayList<ArrayList<Integer>> adj, Node[] nodes) {
        for (Integer i : adj.get(cur.num)) {
            if (i == parentNum) continue; 
            Node child = nodes[i];
            cur.childs += dfs(child, cur.num, adj, nodes);
        }
        return cur.childs;
    }
}