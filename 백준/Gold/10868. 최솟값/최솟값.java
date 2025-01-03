import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 최솟값
    static final int MAX = 1_000_000_001;
    static StringBuilder sb;
    static int[] minSegtree;

    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int firstLeaf = (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)));
        int treeSize = 2 * firstLeaf;
        minSegtree = new int[treeSize];

        // 리프 입력
        for (int i = firstLeaf; i < firstLeaf + n; i++) {
            minSegtree[i] = Integer.parseInt(br.readLine());
        }
        for(int i = firstLeaf + n; i < treeSize; i++) {
            minSegtree[i] = MAX;
        }

        // 트리 완성
        for (int i = firstLeaf - 1; i > 0; i--) {
            minSegtree[i] = Math.min(minSegtree[2 * i], minSegtree[2 * i + 1]);
        }

        // 쿼리 처리
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if(from == to) {
                sb.append(minSegtree[from + firstLeaf - 1]).append("\n");
                continue;
            }
            answerQuery(from + firstLeaf - 1, to + firstLeaf - 1);
        }
        br.close();
        System.out.println(sb);
    }

    static void answerQuery(int from, int to) {
        int min = MAX;

        while (from <= to) {
            if (from % 2 == 1) {
                min = Math.min(min, minSegtree[from]);
                from++;
            }
            if (to % 2 == 0) {
                min = Math.min(min, minSegtree[to]);
                to--;
            }
            from /= 2;
            to /= 2;
        }
        sb.append(min).append("\n");
    }
}