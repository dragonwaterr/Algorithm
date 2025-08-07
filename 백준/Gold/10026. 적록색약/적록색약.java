import java.io.*;
import java.util.*;

public class Main {
    // 적록색약
    static int cnt = 0;
    static int cnt_for_saekyak = 0;

    static String[][] graph;

    static boolean[][] visited;
    static boolean[][] visited_for_saekyak;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static void bfs(int N) {
        Queue<int[]> q = new LinkedList();
        Queue<int[]> q2 = new LinkedList();
        
        // 적록색약 X 사람용
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {

                if(visited[i][j]) continue; // 이미 숫자센 구역이면 넘어가
                q.add(new int[] {i, j}); // 처음 넣은 위치부터 한 구역계산시작
                visited[i][j] = true;
                cnt++;
                
                while(!q.isEmpty()) {
                    int[] pos = q.poll();

                    // 상하좌우 순서로 비교중~
                    for(int k=0; k<4; k++) {
                        int ny = pos[0] + dy[k];
                        int nx = pos[1] + dx[k];

                        if(!(0 <= nx && nx < N && 0 <= ny && ny < N)) continue; // indexOutofbounds 방지
                        if(!(graph[pos[0]][pos[1]].equals(graph[ny][nx]))) continue;    // 다른 색이면 넘어가-
                        if(visited[ny][nx]) continue;

                        q.add(new int[] {ny, nx}); 
                        visited[ny][nx] = true;
                    }
                }
            }
        }
        
        // 적록색약 O 사람용
        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {

                q2.add(new int[] {i, j});
                if(visited_for_saekyak[i][j]) continue;

                visited_for_saekyak[i][j] = true;
                cnt_for_saekyak++;

                while(!q2.isEmpty()) {
                    int[] pos_sy = q2.poll();

                    for(int k=0; k<4; k++) {
                        int ny_sy = pos_sy[0] + dy[k];
                        int nx_sy = pos_sy[1] + dx[k];

                        if(!(0 <= nx_sy && nx_sy < N && 0 <= ny_sy && ny_sy < N)) continue;
                        if(graph[pos_sy[0]][pos_sy[1]].equals("B")) {
                            if(!graph[ny_sy][nx_sy].equals("B")) continue;
                        } else {
                            if(graph[ny_sy][nx_sy].equals("B")) continue;
                        }
                        if(visited_for_saekyak[ny_sy][nx_sy]) continue;

                        q2.add(new int[] {ny_sy, nx_sy});
                        visited_for_saekyak[ny_sy][nx_sy] = true;
                    }
                }
            }
        }
        System.out.println(cnt+" "+cnt_for_saekyak);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        
        graph = new String[N][N];
        visited = new boolean[N][N];
        visited_for_saekyak = new boolean[N][N];

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<N; j++) {
                graph[i][j] = str.charAt(j)+"";
            }
        }
        br.close();
        bfs(N);
    }
}