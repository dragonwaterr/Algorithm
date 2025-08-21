import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    // 회사 문화 1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, m;
        int[] praise;
        ArrayList[] subOrd;

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        praise = new int[n+1];
        subOrd = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            subOrd[i] = new ArrayList<Integer>();
        }

        st = new StringTokenizer(br.readLine());
        st.nextToken(); // -1 버리기
        for (int i = 2; i <= n; i++) {
            int p = Integer.parseInt(st.nextToken());
            subOrd[p].add(i); // p 가 직속상사 번호, subOrd 는 후배번호를 저장
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int empNum = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            praise[empNum] += val; // 똑같은 직원을 여러번 칭찬 가능 ㄷ
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];

        for (int i = 0; i < subOrd[1].size(); i++) {
            int sub = (int)subOrd[1].get(i);
            q.add(sub);
            visited[sub] = true;
        }

        // 사장의 직속후배(들) 부터 시작
        while(!q.isEmpty()) {
            int cur = q.poll();
            // 이 사람은, 자기의 후배에게 칭찬을 누적해주고 후배를 큐에 넣기
            int pr = praise[cur];
            if(!subOrd[cur].isEmpty()) {
                for (int i = 0; i < subOrd[cur].size(); i++) {
                    int sub =  (int)subOrd[cur].get(i);
                    praise[sub] += pr;
                    if(visited[sub]) continue;
                    visited[sub] = true;
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
