import java.util.*;
import java.io.*;

public class Main {
    public static final int MAX = 987654321;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n+1][n+1];
        
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;    
                map[i][j] = MAX;
            }
        } 
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m1 = Integer.parseInt(st.nextToken());
            int m2 = Integer.parseInt(st.nextToken());
            if(m1 == -1 || m2 == -1) break;
            map[m1][m2] = map[m2][m1] = 1;
        }
        applyFloyd(map, n);
        
        ArrayList<Integer> candidates = new ArrayList<>();
        
        int[] result = makeResult(map, n, candidates);
        int ptr = result[1];
        
        StringBuilder sb = new StringBuilder();
        sb.append(result[0] + " ").append(ptr + "\n");
        for(int i = 0; i < ptr; i++) {
            sb.append(candidates.get(i) + " ");
        }
        
        System.out.println(sb.toString());
    }   
    
    static void applyFloyd(int[][] map, int n) {
        for(int i = 1; i <= n; i++) { // 경유
            for(int j = 1; j <= n; j++) { // 출발 
                for(int k = 1; k <= n; k++) { // 도착
                    if(j == k) continue;
                    map[j][k] = Math.min(map[j][i] + map[i][k], map[j][k]);
                    // map[k][j] = Math.min(map[k][j], map[j][k]);
                }
            }
        }
    }
    
    static int[] makeResult(int[][] map, int n, ArrayList<Integer> list) {
        int ptr = 0;
        int minPoint = 51;

        for(int i = 1; i <= n; i++) {
            int farthest = 0;
            for(int j = 1; j <= n; j++) {
                farthest = Math.max(farthest, map[i][j]); 
            }
            if(farthest < minPoint) {
                ptr = 0;
                list.add(ptr++, i);
                minPoint = farthest;
            } else if(farthest == minPoint) {
                list.add(ptr++, i);
            }
        }
        return new int[]{minPoint, ptr};
    }

}