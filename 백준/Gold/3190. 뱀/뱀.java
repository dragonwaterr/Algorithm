import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Queue<int[]> q = new LinkedList<>();
    static Queue<String[]> direction = new LinkedList<>();
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0}; // 상우하좌
    static int[] dc = {0, 1, 0, -1}; // 상우하좌
    static int dir = 1;
    static int time = 0;
    static int cntDir;
    static String sDir;
    static int n, k;


    static void snakeMove(int r, int c) {

        if (time == cntDir) { // 방향을 바꿀 시간이 되었다면
            if ("D".equals(sDir)) { // 오른쪽 90도 회전
                dir = (dir + 1) % 4; // 1->2->3->0
            } else if ("L".equals(sDir)) { // 왼쪽 90도 회전
                dir = (dir + 3) % 4; // 0->3->2->1->0
            }

            if (!direction.isEmpty()) { // 다음 전환할 방향을 세팅해둔다
                String[] x = direction.poll();
                int y = Integer.parseInt(x[0]);
                String z = x[1];
                cntDir = y;
                sDir = z;
            }
        }


        int nr = r + dr[dir];
        int nc = c + dc[dir];
        time += 1;

        //종료 조건
        if (nr < 0 || nc < 0 || nr >= n || nc >= n || visited[nr][nc]) {
            return;
        }

        //이동
        q.offer(new int[]{nr, nc}); // 머리를 집어넣고
        if (map[nr][nc] == 1) { // 사과
            map[nr][nc] = 0;
        } else { // 꼬리를 뺀다
            int[] v = q.poll();
            visited[v[0]][v[1]] = false; // 꼬리 자리는 다시 갈 수 있는 위치
        }
        visited[nr][nc] = true;
        snakeMove(nr, nc);
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[a][c] = 1;
        }

        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            StringTokenizer s1 = new StringTokenizer(br.readLine());
            String f = s1.nextToken();
            String sec = s1.nextToken();
            direction.offer(new String[]{f, sec});
        }

        if (!direction.isEmpty()) {
            String[] x = direction.poll();
            int y = Integer.parseInt(x[0]); // y 초에
            String z = x[1]; // z 방향으로 꺾는다
            cntDir = y;
            sDir = z;
        }
        q.offer(new int[]{0, 0});
        snakeMove(0, 0);
        System.out.println(time);
    }


}
