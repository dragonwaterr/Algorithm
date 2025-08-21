import java.io.IOException;
import java.util.*;

public class Main {
    // 회사 문화 1
    
    static int nextInt() throws IOException {
        int sign = 1;
        int input;
        while (!Character.isDigit(input = System.in.read())) {
            if (input == '-') {
                sign = -1;
                input = System.in.read();
                break;
            }
        }
        int value = input & 15;
        while (Character.isDigit(input = System.in.read())) {
            value = value * 10 + (input & 15);
        }
        return sign * value;
    }

    public static void main(String[] args) throws IOException {
        int n, m;
        n = nextInt();
        m = nextInt();

        int[] praise = new int[n+1];
        ArrayList<Integer>[] subOrd = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            subOrd[i] = new ArrayList<>();
        }

        nextInt();
        for (int i = 2; i <= n; i++) {
            int p = nextInt();
            subOrd[p].add(i); 
        }

        for (int i = 0; i < m; i++) {
            int empNum = nextInt();
            int val = nextInt();
            praise[empNum] += val;
        }

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < subOrd[1].size(); i++) {
            int sub = (int)subOrd[1].get(i);
            q.add(sub);
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            int pr = praise[cur];
            if(!subOrd[cur].isEmpty()) {
                for (int i = 0; i < subOrd[cur].size(); i++) {
                    int sub =  (int)subOrd[cur].get(i);
                    praise[sub] += pr;
                    q.add(sub);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(praise[i]).append(" ");
        }
        System.out.println(sb);
    }
}
