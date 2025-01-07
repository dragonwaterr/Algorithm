import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 수열과 쿼리 16
    static final int MAX = 1_000_000_001;
    static int firstLeaf;
    static Node[] segtree;

    static class Node {
        int val;
        int idx;
        public Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int treeSize = 2 * (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)));
        segtree = new Node[treeSize];
        segtree[0] = new Node(MAX, 0);
        firstLeaf = segtree.length / 2;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int val = Integer.parseInt(st.nextToken());
            segtree[firstLeaf - 1 + i] = new Node(val, firstLeaf - 1 + i);
        }
        for(int i = firstLeaf + n; i < treeSize; i++) {
            segtree[i] = segtree[0];
        }

        for(int i = firstLeaf - 1; i > 0; i--) {
            Node parent;
            Node lc = segtree[2 * i];
            Node rc = segtree[2 * i +1];
            if(lc.val < rc.val) parent = lc;
            else if(lc.val > rc.val) parent = rc;
            else if(lc.idx < rc.idx) parent = lc;
            else parent = rc;
            segtree[i] = parent;
        }

        int q = Integer.parseInt(br.readLine());
        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            if(type == 1) updateTree(firstLeaf + num1 - 1, num2);
            else sb.append(search(firstLeaf + num1 - 1, firstLeaf + num2 - 1)).append("\n");
        }
        br.close();
        System.out.println(sb);
    }

    static void updateTree(int idx, int num) {
        segtree[idx].val = num;
        while(idx > 1) {
            idx /= 2;
            Node lc = segtree[idx * 2];
            Node rc = segtree[idx * 2 + 1];
            if(lc.val < rc.val) {
                segtree[idx] = lc;
            } else if(lc.val == rc.val) {
                segtree[idx] = lc.idx <= rc.idx ? lc : rc;
            } else {
                segtree[idx] = rc;
            }
        }
    }

    static int search(int from, int to) {
        Node minNode = new Node(MAX, 0);
        
        while(from <= to) {
            if(from % 2 == 1) {
                if(minNode.val > segtree[from].val
                        || (minNode.val == segtree[from].val && minNode.idx > segtree[from].idx))
                    minNode = segtree[from];
                from++;
            }
            if(to % 2 == 0) {
                if(minNode.val > segtree[to].val
                        || (minNode.val == segtree[to].val && minNode.idx > segtree[to].idx))
                    minNode = segtree[to];
                to--;
            }
            from /= 2;
            to /= 2;
        }
        return minNode.idx - firstLeaf + 1;
    }
}