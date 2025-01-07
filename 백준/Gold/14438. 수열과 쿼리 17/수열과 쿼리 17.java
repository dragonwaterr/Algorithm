import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 수열과 쿼리 17
    static final int MAX = 1_000_000_001;
    static int firstLeaf;
    static int[] segtree;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int treeSize = 2 * (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)));
        segtree = new int[treeSize];
        segtree[0] = MAX;
        firstLeaf = segtree.length / 2;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = firstLeaf; i < firstLeaf + n; i++) {
            segtree[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = firstLeaf + n; i < treeSize; i++) {
            segtree[i] = MAX;
        }

        for(int i = firstLeaf - 1; i > 0; i--) {
            segtree[i] = Math.min(segtree[2 * i], segtree[2 * i + 1]);
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
        segtree[idx] = num;
        while(idx > 1) {
            idx /= 2;
            int lv = segtree[2 * idx];
            int rv = segtree[2 * idx + 1];
            segtree[idx] = Math.min(lv, rv);
        }
    }

    static int search(int from, int to) {
        int min = MAX;
        while(from <= to) {
            if(from % 2 == 1) min = Math.min(min, segtree[from++]);
            if(to % 2 == 0) min = Math.min(min, segtree[to--]);
            from /= 2;
            to /= 2;
        }
        return min;
    }
}