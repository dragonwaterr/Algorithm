import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] pos = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(pos);
        
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        for(int i = 1; i <= N; i++) {
           pq.add(pos[i] - pos[i-1]);
        }
        
        int answer = 0;
        for(int i = 0; i < K; i++) {
            if(!pq.isEmpty()) {
                pq.remove();
            }
        }
        
        while(!pq.isEmpty()) {
            answer += pq.remove();    
        }
        System.out.println(answer);
    }
}
