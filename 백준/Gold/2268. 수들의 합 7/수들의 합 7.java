import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 수들의 합 7
    static long[] segtree;
    static int firstLeaf;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int treeSize = 2 * (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)));
        segtree = new long[treeSize];
        firstLeaf = segtree.length / 2;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int qType = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            if(qType == 1) {
                modify(n1 + firstLeaf - 1, n2);
                continue;
            }
            if(n1 > n2) { // swap
                n1 = n1 ^ n2 ^ (n2 = n1);
            }
            sb.append(sum(n1 + firstLeaf - 1, n2 + firstLeaf - 1)).append("\n");
        }
        br.close();
        System.out.println(sb);
    }
    static void modify(int idx, int num) {
        long diff = num - segtree[idx];
        segtree[idx] = num;
        while(idx > 0) {
            idx /= 2;
            segtree[idx] += diff;
        }
    }
    static long sum(int from, int to) {
        long result = 0;
        while (from <= to) {
            if (from % 2 == 1) result += segtree[from++];
            if (to % 2 == 0) result += segtree[to--];
            from /= 2;
            to /= 2;
        }
        return result;
    }
}