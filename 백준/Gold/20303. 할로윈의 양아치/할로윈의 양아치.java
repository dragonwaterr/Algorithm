import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 할로윈의 양아치
    static int find(int[] parent, int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
    }
    
    static void union(int[] parent, int[] weight, int[] sot, int a, int b) {
        int fa = find(parent, a); // a 노드가 속한 트리의 루트
        int fb = find(parent, b); // b 노드가 속한 트리의 루트

        // 같은 그래프에 있는 두 노드끼리 새로운 간선 생성
        // = 노드 수 변동 X
        if(fa == fb)
            return;

        // 트리 병합
        if(fa > fb) {
            int tmp = fb;
            fb = fa;
            fa = tmp;

            tmp = b;
            b = a;
            a = tmp;
        }
        
        // 더 작은 트리의 모든 자식노드가 큰 트리로 이동하고 작은 트리는 삭제
        parent[fb] = fa;
        sot[fa] += sot[fb];
        sot[fb] = 0;

        // + 루트에 가중치 누적
        weight[fa] += weight[fb]; 
        weight[fb] = 0; // 흡수되는 그래프의 루트는 값을 잃는다
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] parent = new int[n+1]; // 트리의 루트 저장
        int[] weight = new int[n+1]; // 각 노드의 가중치 저장
        int[] sot = new int[n+1]; // 인덱스가 루트인 트리가 가진 노드 수
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
            weight[i] = Integer.parseInt(st.nextToken());
            sot[i] = 1;
        }
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(parent, weight, sot, a, b); // 1. 트리와 각 트리에 속한 노드수를 저장한다
        }
        
        // 2. 1에서 구한 그래프를 set에 저장한다 (루트 번호와, 누적합, 구성노드의 숫자)
        int total = 0;
        Set<Integer> rootes = new HashSet<>();
        for(int i = 1; i <= n; i++) {
            if (weight[i] != 0) {
                rootes.add(i);
                total += sot[i];
            }
        }

        // 3. 모든 set의 누적합과 set의 숫자를 dp 배열로 만들어
        // 배낭 알고리즘 적용해 그래프의 노드 숫자의 합이 k-1을 넘지않는 max 구한다
        List<Integer> list = new ArrayList<>(rootes);
        list.add(0, 0); // 빈 요소 하나 넣기
        int[][] dp = new int[list.size()][k];

        // 이번 집합을 넣는다 vs 안넣는다
        for(int i = 1; i < list.size(); i++) {
            for(int j = 1; j < k; j++) {
                if(j < sot[list.get(i)]) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-sot[list.get(i)]] + weight[list.get(i)]);
            }
        }
        System.out.println(dp[list.size()-1][k-1]);
    }
}