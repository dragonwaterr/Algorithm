import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());

        Node[] nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) nodes[i] = new Node(i);

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            adj.get(n1).add(n2);
            adj.get(n2).add(n1);
        }

        iterativeDfs(R, adj, nodes);

        for (int i = 0; i < Q; i++) {
            int U = Integer.parseInt(br.readLine().trim());
            sb.append(nodes[U].childs).append('\n');
        }

        System.out.print(sb);
    }

    static class Node {
        int num;
        int childs = 1; // 자기 자신 포함 서브트리 크기

        Node(int num) {
            this.num = num;
        }
    }

    static void iterativeDfs(int root, ArrayList<ArrayList<Integer>> adj, Node[] nodes) {
        int[] parent = new int[nodes.length];
        Arrays.fill(parent, -1);

        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> visitOrder = new ArrayList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            visitOrder.add(cur);
            for (int next : adj.get(cur)) {
                if (next != parent[cur]) {
                    parent[next] = cur;
                    stack.push(next);
                }
            }
        }

        for (int i = visitOrder.size() - 1; i >= 0; i--) {
            int cur = visitOrder.get(i);
            if (parent[cur] != -1) {
                nodes[parent[cur]].childs += nodes[cur].childs;
            }
        }
    }
}