import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 카드 정렬하기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // Min Heap

        for(int i = 0; i < n; i++)
            pq.add(Integer.parseInt(br.readLine()));

        if(pq.size() == 1) {
            System.out.println(0);
            return;
        }

        int answer = 0;
        while(pq.size() > 1) {
            int cur = pq.poll() + pq.poll();
            answer += cur;
            pq.add(cur);
        }

        System.out.println(answer);
    }
}