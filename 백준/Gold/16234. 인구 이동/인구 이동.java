import java.io.*;
import java.util.*;

public class Main {
    static int n, l, r;
    static ArrayList<int[]> point;
    static boolean[][] visit;
    static int[][] graph;
    static int answer = 0;
    static int[] dy = {-1, 0, 1, 0}; // 북동남서
    static int[] dx = {0, 1, 0, -1}; // 북동남서
    
    static void bfs(int startY, int startX) {
        point.add(new int[]{startY, startX}); // 시작점 위치를 먼저 리스트에 저장
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startY, startX});
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                if(!(0 <= ny && ny <= n-1) || !(0 <= nx && nx <= n-1)) continue;
                if(visit[ny][nx]) continue;
                int difference = Math.abs(graph[ny][nx] - graph[cur[0]][cur[1]]);
                if(l <= difference && difference <= r) {
                    q.add(new int[]{ny, nx});
                    visit[ny][nx] = true;
                    point.add(new int[]{ny, nx});
                }
            }
        }
    }
    static void makeMove() {
        int c = point.size();
        int val = 0;
        for(int[] spot : point)
            val += graph[spot[0]][spot[1]];
        val /= c;
        for(int[] spot : point)
            graph[spot[0]][spot[1]] = val;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int answer = 0;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        visit = new boolean[n][n];
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        while(true) {
            boolean flag = false;
            visit = new boolean[n][n];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(!visit[i][j]) { // 비짓을 안했다 = 그 칸은 인구이동 가능한지 아직 체크를 안했다
                        point = new ArrayList<>();
                        visit[i][j] = true;
                        bfs(i, j); // 하나의 그룹으로 묶는다
                        if(point.size() != 1) {
                            flag = true;
                            makeMove(); // 인구이동
                        }
                    }
                }
            }
            if(!flag) { // 인구이동이 한 번도 일어나지 않았다면 종료
                bw.write(String.valueOf(answer));
                bw.flush();
                return;
            }
            answer++;
        }
    }
}