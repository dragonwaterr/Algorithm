import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 수열과 쿼리 37
    static int firstLeaf;
    static int[] segtree, sequence;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int treeSize = 2 * (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)));
        segtree = new int[treeSize];
        sequence = new int[n+1];
        firstLeaf = segtree.length / 2;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
            segtree[i + firstLeaf - 1] = sequence[i] % 2 == 1 ?  1 : 0;
        }

        for(int i = firstLeaf - 1; i > 0; i--) {
            segtree[i] = segtree[2 * i] + segtree[2 * i + 1];
        }

        int q = Integer.parseInt(br.readLine());
        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            if(type == 1) {
                updateTree(num1, num2);
            } else if(type == 2) {
                sb.append((num2 - num1 + 1) - search(firstLeaf + num1 - 1, firstLeaf + num2 - 1)).append("\n");
            } else {
                sb.append(search(firstLeaf + num1 - 1, firstLeaf + num2 - 1)).append("\n");
            }
        }
        br.close();
        System.out.println(sb);
    }

    static void updateTree(int idx, int num) {
        sequence[idx] = num;
        segtree[idx + firstLeaf - 1] = num % 2 == 1 ? 1 : 0;
        idx = idx + firstLeaf - 1;
        while(idx > 1) {
            idx /= 2;
            segtree[idx] = segtree[2 * idx] + segtree[2 * idx + 1];
        }
    }

    static int search(int from, int to) {
        int odds = 0;
        while(from <= to) {
            if(from % 2 == 1) odds += segtree[from++];
            if(to % 2 == 0) odds += segtree[to--];
            from /= 2;
            to /= 2;
        }
        return odds;
    }
}