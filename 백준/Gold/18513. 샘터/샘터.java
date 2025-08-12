import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    // 샘터
    
    static class Pointer {
        int pos; 
        int dist; 
        boolean isLeft; 

        Pointer(int pos, int dist, boolean isLeft) {
            this.pos = pos;
            this.dist = dist;
            this.isLeft = isLeft;
        }
    }

    static long answer;
    static HashMap<Integer, Character> obs; // 좌표 : 집 OR 샘터
    static PriorityQueue<Pointer> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        obs = new HashMap<>();
        pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist); // 샘터와의 거리 오름차순 정렬

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int pos = Integer.parseInt(st.nextToken());
            obs.put(pos, 'S');

            if(obs.get(pos-1) == null) { // 왼쪽 포인터 생성
                pq.add(new Pointer(pos-1, 1, true));
            }
            if(obs.get(pos+1) == null) { // 오른쪽 포인터 생성
                pq.add(new Pointer(pos+1, 1, false));
            }
        }

        int cnt = 0;
        while(cnt < k) {
            Pointer cur = pq.poll();

            int dest = cur.pos;
            int dist = cur.dist;
            boolean dir = cur.isLeft;

            if(obs.get(dest) != null) {
                continue;
            }

            obs.put(dest, 'H');
            answer += dist;
            cnt++;

            int nextPos = dir ? dest-1 : dest+1;
            if(obs.get(nextPos) == null) {
                pq.add(new Pointer(nextPos, dist+1, dir));   
            }
        }

        System.out.println(answer);

    }
}