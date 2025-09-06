import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    // 콘센트
    private static int m, sum, answer;
    private static PriorityQueue<Integer> pq;

    // 읽기 메소드
    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 47) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    // 초기화 메소드
    private static void init() throws IOException {
        pq = new PriorityQueue<>(Collections.reverseOrder());
        int n = read();
        m = read();
        sum = 0;
        answer = 0;
        for(int i = 0; i < n; i++) {
            int t = read();
            sum += t;
            pq.add(t);
        }
    }
    
    // 처리 메소드
    private static void solution() {
        
        if(m == 1) {
            answer = sum;
            return;
        }

        while(!pq.isEmpty()) {
            int max = pq.poll();
            answer += max;

            for(int i = 0; i < m-1; i++) {
                int temp = max;
                while(temp > 0) {
                    if(!pq.isEmpty()) {
                        int cur = pq.poll();
                        temp -= cur;
                    } else return;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(answer);
    }
}