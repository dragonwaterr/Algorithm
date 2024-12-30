import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    // 가운데를 말해요
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minheap = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());
        int mid = Integer.parseInt(br.readLine());
        sb.append(mid).append("\n");

        for(int i = 0; i < n-1; i++) {
            int cur = Integer.parseInt(br.readLine());

            if((i+2) % 2 == 0) {
                if(cur > mid) {
                    minheap.add(cur);
                } else {
                    maxheap.add(cur);
                    minheap.add(mid);
                    mid = maxheap.poll();
                }
            } else {
                if(cur > mid) {
                    minheap.add(cur);
                    maxheap.add(mid);
                    mid = minheap.poll();
                } else {
                    maxheap.add(cur);
                }
            }

            sb.append(mid).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}