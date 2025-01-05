import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 가계부(Hard)
    static long[] segtree;
    static int treeSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        treeSize = 2 * (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)));
        segtree = new long[treeSize];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int qType = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            if (qType == 2) {
                sb.append(getSum(n1 - 1 + treeSize / 2, n2 - 1 + treeSize / 2)).append("\n");
                continue;
            }
            updateTree(n1 - 1 + treeSize / 2, n2);
        }
        System.out.println(sb);
    }

    static void updateTree(int idx, long val) {
        while (idx > 0) {
            segtree[idx] += val;
            idx /= 2;
        }
    }

    static long getSum(int from, int to) {
        long sum = 0;
        while (from <= to) {
            if (from % 2 == 1) sum += segtree[from++];
            if (to % 2 == 0) sum += segtree[to--];
            from /= 2;
            to /= 2;
        }
        return sum;
    }
}