import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static final int WALL = 1;
    static final int NIGHT = -2;
    static final int NOWAY = 987654321;
    static int[][] graph;
    static int n, m, k;

    static class node {
        int x, y, isNight, days, crash;
        public node(int x, int y, int isNight, int days, int crash) {
            this.x = x;
            this.y = y;
            this.isNight = isNight;
            this.days = days;
            this.crash = crash;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new int[n+1][m+1];
        for(int i = 1; i <= n; i++) {
            String input = br.readLine();
            for(int j = 0; j < input.length(); j++) {
                graph[i][j+1] = input.charAt(j)-'0';
            }
        }
        
        if(n == 1 && m == 1) {
            System.out.println(1);
            return;
        }
        
        System.out.println(bfs());
    }

    static int bfs() {
        int answer = NOWAY;
        int[][] dt = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        boolean[][][] visit = new boolean[n+1][m+1][k+1];
        ArrayDeque<node> q = new ArrayDeque<>();
        q.add(new node(1, 1, 2, 1, 0));

        boolean waiting = false;
        while(!q.isEmpty()) {
            node cur = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx =  cur.x + dt[i][0];
                int ny =  cur.y + dt[i][1];

                if(nx < 1 || nx > n || ny < 1 || ny > m) continue;
                if(cur.crash == k && graph[nx][ny] == WALL) continue;
                if(cur.isNight == NIGHT && graph[nx][ny] == WALL) {
                    waiting = true;
                    continue;
                }
                if(visit[nx][ny][cur.crash]) continue;
                visit[nx][ny][cur.crash] = true;

                if(nx == n && ny == m) {
                    answer = Math.min(answer, cur.days + 1);
                    continue;
                }

                q.add(graph[nx][ny] == WALL
                        ? new node(nx, ny, -1 * cur.isNight, cur.days + 1, cur.crash + 1)
                        : new node(nx, ny, -1 * cur.isNight, cur.days + 1, cur.crash)
                );

            }
            if(waiting) {
                q.add(new node(cur.x, cur.y, -1 * cur.isNight, cur.days+1, cur.crash));
                waiting = false;
            }
        }

        if(answer == NOWAY) return -1;
        return answer;
    }
}