import java.io.IOException;

public class Main {
    // 수들의 합 7
    static long[] segtree;
    static int firstLeaf;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = read();
        int m = read();

        int treeSize = 2 * (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)));
        segtree = new long[treeSize];
        firstLeaf = segtree.length / 2;

        for (int i = 0; i < m; i++) {
            int qType = read();
            int n1 = read();
            int n2 = read();
            if(qType == 1) {
                modify(n1 + firstLeaf - 1, n2);
                continue;
            }
            if(n1 > n2) { // swap
                n1 = n1 ^ n2 ^ (n2 = n1);
            }
            sb.append(sum(n1 + firstLeaf - 1, n2 + firstLeaf - 1)).append("\n");
        }
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
    static int read() throws IOException {
        int d, o = System.in.read() & 15;
        while ((d = System.in.read()) > 32)
            o = (o << 3) + (o << 1) + (d & 15);

        return o;
    }
}