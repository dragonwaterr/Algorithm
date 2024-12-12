import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    // 구간 합 구하기 (Top-down 방식)
    static int n, m, k;
    static long[] segtree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 처리: n = 데이터 개수, m = 수정 횟수, k = 구간 합 계산 횟수
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 세그먼트 트리 초기화
        int depth = (int) Math.ceil(Math.log(n) / Math.log(2)); // 트리 깊이 계산
        int fullLeafs = (int) Math.pow(2, depth); // 리프 노드 개수 (2의 배수)
        int treeSize = (fullLeafs * 2) - 1; // 포화 이진 트리의 전체 노드 수
        segtree = new long[treeSize];

        // 리프 노드 입력
        int firstLeaf = (treeSize + 1) / 2 - 1; // 첫 리프 노드의 시작 인덱스
        for (int i = firstLeaf; i < firstLeaf + n; i++) {
            segtree[i] = Long.parseLong(br.readLine());
        }

        // 내부 노드 값 계산 (리프에서 위로 올라가며 트리 완성)
        int firstNode = firstLeaf;
        while (firstNode > 0) {
            for (int i = firstNode; i <= 2 * firstNode; i += 2) {
                segtree[i / 2] = segtree[i] + segtree[i + 1]; // 부모 노드에 합 저장
            }
            firstNode /= 2; // 한 단계 위로 이동
        }

        // 쿼리 처리: 수정(1)과 구간 합 계산(2)
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) {
                // 값 수정
                int index = Integer.parseInt(st.nextToken()) - 1 + firstLeaf;
                long number = Long.parseLong(st.nextToken());
                updateNumber(index, number);
            } else {
                // 구간 합 계산
                int from = Integer.parseInt(st.nextToken()) - 1 + firstLeaf; // 닫힌 구간 시작점
                int to = Integer.parseInt(st.nextToken()) - 1 + firstLeaf;   // 닫힌 구간 끝점
                long result = getSum(0, firstLeaf, firstLeaf + fullLeafs - 1, from, to);
                sb.append(result).append("\n");
            }
        }

        // 결과 출력
        System.out.println(sb);
    }

    // 값 수정 메서드: index 위치의 값을 변경하고 부모 노드까지 갱신
    static void updateNumber(int index, long number) {
        long diff = number - segtree[index]; // 값의 변화량 계산
        segtree[index] = number; // 리프 노드 값 변경

        // 부모 노드 갱신
        while (index > 0) {
            int parent = (index % 2 == 0) ? (index - 1) / 2 : index / 2;
            segtree[parent] += diff; // 변화량 반영
            index = parent; // 부모로 이동
        }
    }

    /*
     * 구간 합 계산 메서드
     * @param node   현재 노드 인덱스
     * @param start  현재 노드가 담당하는 구간의 시작점
     * @param end    현재 노드가 담당하는 구간의 끝점
     * @param left   구간 합을 구하고자 하는 범위의 시작점
     * @param right  구간 합을 구하고자 하는 범위의 끝점
     * @return       지정된 구간의 합
     */
    static long getSum(int node, int start, int end, int left, int right) {
        // 1. 현재 구간이 구하고자 하는 범위에 완전히 포함된 경우
        if (left <= start && end <= right) return segtree[node];

        // 2. 현재 구간이 구하고자 하는 범위와 전혀 겹치지 않는 경우
        if (start > right || end < left) return 0;

        // 3. 현재 구간이 구하고자 하는 범위와 일부만 겹치는 경우
        int mid = (start + end) / 2; // 현재 구간을 두 부분으로 나누기
        long lsum = getSum(2 * node + 1, start, mid, left, right); // 왼쪽 자식에서 합 구하기
        long rsum = getSum(2 * node + 2, mid + 1, end, left, right); // 오른쪽 자식에서 합 구하기
        return lsum + rsum; // 왼쪽과 오른쪽 합을 더해서 반환
    }
}