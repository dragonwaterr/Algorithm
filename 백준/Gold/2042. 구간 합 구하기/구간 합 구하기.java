import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
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


        // int depth = (int)Math.ceil(Math.sqrt(n)); 
        int depth = (int)Math.ceil(Math.log(n) / Math.log(2));
        int fullLeafs = (int)Math.pow(2, depth); // 최대 가질 수 있는 리프 수
        int treeSize = (fullLeafs * 2) - 1; // 포화 이진트리의 총 노드 수
        segtree = new long[treeSize];

        // 리프 입력부
        int firstLeaf = (treeSize + 1)/2 - 1;
        for(int i = firstLeaf; i < firstLeaf + n; i++) {
            segtree[i] = Long.parseLong(br.readLine());
        }

        // 트리 완성부
        int firstNode = firstLeaf;
        while(firstNode > 0) {
            // 이번 depth 에 존재할 수 있는 모든 노드 수만큼 순회
            for(int i = firstNode; i <= 2 * firstNode; i+=2) {
                segtree[i/2] = segtree[i] + segtree[i+1];
            }
            firstNode /= 2; // 1 depth 위의 첫 노드
        }

        // 1 : 값 수정, 2 : 합 구하기
        for(int i = 0; i < m+k; i++) {
            st = new StringTokenizer(br.readLine());
            if(Integer.parseInt(st.nextToken()) == 1) {
                int index = Integer.parseInt(st.nextToken()) - 1 + firstLeaf;
                long number = Long.parseLong(st.nextToken());
                updateNumber(index, number);
                continue;
            }
            int from = Integer.parseInt(st.nextToken()) - 1 + firstLeaf;
            int to = Integer.parseInt(st.nextToken()) + firstLeaf;
            getSum(from, to);
        }

        System.out.println(sb);
    }

    // index 에 해당하는 값을 number 로 바꾸고 트리를 갱신
    static void updateNumber(int index, long number) {
        long diff = number - segtree[index];
        segtree[index] = number;

        while(index > 0) {
            int parent = index % 2 == 0 ? (index-1)/2 : index/2;
            segtree[parent] += diff;
            index = parent;
        }
    }

    // from ~ to 까지의 합을 구해 sb.append 
    static void getSum(int from, int to) {
        long sum = 0;
        while (from < to) {
            // from이 오른쪽 자식 노드이면 현재 값을 더하고 부모로 이동
            if (from % 2 == 0) {
                sum += segtree[from];
                from++;
            }
            // to가 오른쪽 자식 노드가 아니면 현재 값을 더하고 부모로 이동
            if (to % 2 == 0) {
                to--;
                sum += segtree[to];
            }
            // 부모로 이동
            from /= 2;
            to /= 2;
        }
        sb.append(sum).append("\n");
    }
}