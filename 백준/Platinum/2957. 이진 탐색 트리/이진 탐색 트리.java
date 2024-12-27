import java.io.BufferedReader;
import java.io.IOException;
import java.util.TreeSet;

public class Main {
    // 이진 탐색 트리
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] depth = new int[n+2];
        depth[0] = depth[n+1] = -1;

        TreeSet<Integer> tset = new TreeSet<>();
        tset.add(0);
        tset.add(n+1);

        long answer = 0;
        StringBuilder sb = new StringBuilder();
        while(n-- > 0) {
            int k = Integer.parseInt(br.readLine());
            depth[k] = Math.max(depth[tset.higher(k)], depth[tset.lower(k)]) + 1;
            answer += depth[k];
            sb.append(answer).append("\n");
            tset.add(k);
        }
        System.out.println(sb);
    }
}