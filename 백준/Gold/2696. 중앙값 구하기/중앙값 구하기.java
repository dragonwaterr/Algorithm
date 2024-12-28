import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    // 중앙값 구하기
    static PriorityQueue<Integer> minheap;
    static PriorityQueue<Integer> maxheap;
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0) {
            minheap = new PriorityQueue<>(); // middle 보다 큰 값만 add
            maxheap = new PriorityQueue<>((o1, o2) -> o2 - o1); // middle 보다 작은 값만 add
            int m = Integer.parseInt(br.readLine());
            solution(m);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static void solution(int m) throws IOException {
        int cnt = 1;
        int lines = m / 10;
        int middle = 0;
        sb.append((m+1) / 2);

        while (lines-- > -1) {
            if((cnt / 10) % 2 == 0) sb.append("\n");
            StringTokenizer st = new StringTokenizer(br.readLine());

            // tc 마다 처음 숫자 한 번만 수행
            if(lines == m / 10 - 1) {
                middle = Integer.parseInt(st.nextToken());
                sb.append(middle).append(" ");
            }

            while (st.hasMoreTokens()) {
                int cur = Integer.parseInt(st.nextToken());

                if(middle < cur) minheap.add(cur);
                else maxheap.add(cur);

                // 중앙값 추가
                if (++cnt % 2 == 1) {
                    middle = getMiddle(middle);
                    sb.append(middle).append(" ");
                }
            }
        }
    }

    // 중앙값 구하기 메소드
    static int getMiddle(int middle) {
        if(minheap.size() == maxheap.size()) {
            return middle;
        } else if(maxheap.size() > minheap.size()) {
            minheap.add(middle);
            return maxheap.poll();
        } else {
            maxheap.add(middle);
            return minheap.poll();
        }
    }
}