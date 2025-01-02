import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MOD = 1_000_000_007;
    static int n, m, k;
    static long[] segtree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int depth = (int) Math.ceil(Math.log(n) / Math.log(2));
        int fullLeafs = (int) Math.pow(2, depth);
        int treeSize = (fullLeafs * 2) - 1;
        segtree = new long[treeSize];

        // 리프 입력부
        int firstLeaf = (treeSize + 1) / 2 - 1;
        for (int i = firstLeaf; i < firstLeaf + n; i++) {
            segtree[i] = Long.parseLong(br.readLine());
        }
        for (int i = firstLeaf + n; i < segtree.length; i++) {
            segtree[i] = 1;
        }

        // 트리 완성부
        int firstNode = firstLeaf;
        while (firstNode > 0) {
            for (int i = firstNode; i <= 2 * firstNode; i += 2) {
                segtree[i / 2] = (segtree[i] * segtree[i + 1]) % MOD;
            }
            firstNode /= 2;
        }

        // 1 : 값 수정, 2 : 합 구하기
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) == 1) {
                int index = Integer.parseInt(st.nextToken()) - 1 + firstLeaf;
                long number = Long.parseLong(st.nextToken());
                updateNumber(index, number);
                continue;
            }
            int from = Integer.parseInt(st.nextToken()) - 1 + firstLeaf;
            int to = Integer.parseInt(st.nextToken()) + firstLeaf;
            getProduct(from, to);
        }

        System.out.println(sb);
    }

    static void updateNumber(int index, long number) {
        segtree[index] = number;

        while (index > 0) {
            long lc, rc;
            int parent;
            int mod = index % 2;

            lc = segtree[index + mod - 1];
            rc = segtree[index + mod];
            parent = (index + mod - 2) / 2;
            
            segtree[parent] = (lc * rc) % MOD;
            index = parent;
        }
    }

    static void getProduct(int from, int to) {
        long product = 1;
        while (from < to) {
            if (from % 2 == 0) {
                product = (product * segtree[from]) % MOD;
                from++;
            }
            if (to % 2 == 0) {
                to--;
                product = (product * segtree[to]) % MOD;
            }
            from /= 2;
            to /= 2;
        }
        sb.append(product).append("\n");
    }
}