import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 거짓말
    static int[] parents;
    static int find(int x) {
        if(parents[x] == x) return x; // 루트까지 왔다면
        else if(parents[x] == 0) return 0;
        return parents[x] = find(parents[x]);
    }
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a < b) parents[b] = a;
        else parents[a] = b;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;
        int n = Integer.parseInt(st.nextToken()); // 사람 수
        int m = Integer.parseInt(st.nextToken()); // 파티 수

        parents = new int[n+1];
        for(int i = 1; i <= n; i++) parents[i] = i;

        st = new StringTokenizer(br.readLine()); // 진실맨들
        int a = Integer.parseInt(st.nextToken()); // 아는 사람 수
        for(int i = 0; i < a; i++) { // 진실맨들은 모두 0 저장
            int b = Integer.parseInt(st.nextToken());
            parents[b] = 0;
        }

        ArrayList<int[]> party = new ArrayList<>(m); // 파티 정보 입력

        for(int i = 0; i < m; i++) { // 파티 입력
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()); // 파티 참여자 수
            party.add(new int[d]);

            int lv = Integer.parseInt(st.nextToken());
            party.get(i)[0] = lv;

            if(d == 1) continue;

            for(int j = 0; j < d-1; j++) {
                int rv = Integer.parseInt(st.nextToken());
                party.get(i)[j+1] = rv;
                union(lv, rv);
                lv = rv;
            }
        }

        // 거짓말 가능 파티 수 세기
        for(int i = 0; i < m; i++) {
            boolean flag = true;
            for(int j = 0; j < party.get(i).length; j++) {
                if(find(party.get(i)[j]) == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag) answer++;
        }
        System.out.println(answer);
    }
}