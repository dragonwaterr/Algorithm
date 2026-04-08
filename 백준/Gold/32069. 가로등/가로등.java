import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        long L = Long.parseLong(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int cnt = 0;
        
        HashSet<Long> checked = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Node> dq = new ArrayDeque<>();
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            long x = Long.parseLong(st.nextToken());
            cnt++;
            checked.add(x);
            dq.add(new Node(x, 0));
            sb.append(0).append("\n");
            
            if(cnt == K) {
                System.out.println(sb);
                return;
            }
        }
        
        L1: while(true) {
            Node cur = dq.remove();
            
            for(int i = 0; i < 2; i++) {
                long nx = cur.x + dx[i];
                if(0 > nx || nx > L || checked.contains(nx)) continue;
                
                int nb = cur.brightness + 1;
                cnt++;
                checked.add(nx);
                dq.add(new Node(nx, nb));
                sb.append(nb).append("\n");
                
                if(cnt == K) break L1;
            }
        }
            
        System.out.println(sb);
    }
    static int[] dx = {-1, 1};
    static class Node {
        long x;
        int brightness;
        Node(long x, int brightness) {
            this.x = x;
            this.brightness = brightness;
        }
    }
}