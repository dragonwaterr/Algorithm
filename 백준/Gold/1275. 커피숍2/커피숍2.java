import java.io.*;
import java.util.StringTokenizer;

public class Main {
    // 커피숍2
    static long[] segtree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 숫자 개수
        int q = Integer.parseInt(st.nextToken()); // 쿼리 숫자
        
        int firstLeaf = 1 << (int) Math.ceil(Math.log(n) / Math.log(2));
        int treeSize = 2 * firstLeaf;
        segtree = new long[treeSize];

        // n 개의 숫자 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            segtree[firstLeaf + i] = Long.parseLong(st.nextToken());
        }
    
        // 부모 노드 구성
        for (int i = firstLeaf - 1; i > 0; i--) {
            segtree[i] = segtree[2 * i] + segtree[2 * i + 1];
        }

        // 합과 수정 쿼리 입력
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());
            long num = Long.parseLong(st.nextToken());

            if(from > to) from = from ^ to ^ (to = from);
            sb.append(getSum(from + firstLeaf - 1, to + firstLeaf - 1)).append("\n");
            update(idx + firstLeaf - 1, num);
        }
        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void update(int idx, long num) {
        long diff = num - (int) segtree[idx];
        segtree[idx] = num;
        while (idx > 1) {
            idx /= 2;
            segtree[idx] += diff;
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