import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;

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
    static ArrayDeque<Pointer> dq;

    public static void main(String[] args) throws IOException {
        int n = read();
        int k = read();
        obs = new HashMap<>();
        dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            int pos = read();
            obs.put(pos, 'S');

            if(obs.get(pos-1) == null) { // 왼쪽 포인터 생성
                dq.add(new Pointer(pos - 1, 1, true));
            }
            if(obs.get(pos+1) == null) { // 오른쪽 포인터 생성
                dq.add(new Pointer(pos + 1, 1, false));
            }
        }

        int cnt = 0;
        while(cnt < k) {
            Pointer cur = dq.poll();

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
                dq.add(new Pointer(nextPos, dist + 1, dir));
            }
        }

        System.out.println(answer);

    }

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)
            n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return m ? ~n + 1 : n;
    }
}