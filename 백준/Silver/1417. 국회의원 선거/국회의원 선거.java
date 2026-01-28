import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int dasom = Integer.parseInt(br.readLine());
        for (int i = 0; i < n-1; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int cnt = 0;
        while(!pq.isEmpty()) {
            if(dasom > pq.peek()) {
                break;
            }
            
            int cur = pq.poll();
            if(dasom <= cur) {
                cur--;
                dasom++;
                cnt++;
            }
            pq.offer(cur);
        }

        System.out.println(cnt);
    }
}
