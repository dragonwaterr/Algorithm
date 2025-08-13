import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 화산쇄설류

    static int[][] hmap, volcMap;
    static int maxHeight, minTime;
    static int m, n, v;

    static class Volc {
        int x, y, cnt;

        public Volc(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static class JaeSang {
        int x, y, cnt;

        public JaeSang(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        hmap = new int[m + 1][n + 1]; // 높이정보
        volcMap = new int[m + 1][n + 1]; // 0:땅 1:화산쇄설류 2: 화산

        st = new StringTokenizer(br.readLine());
        int jx = Integer.parseInt(st.nextToken());
        int jy = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                hmap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Volc> vq = new LinkedList<>();

        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());
            int vx = Integer.parseInt(st.nextToken());
            int vy = Integer.parseInt(st.nextToken());
            int cntdown = Integer.parseInt(st.nextToken());

            volcMap[vx][vy] = 2;
            vq.add(new Volc(vx, vy, cntdown));
        }

        maxHeight = hmap[jx][jy];
        minTime = 0;

        bfs(jx, jy, vq);

        sb.append(maxHeight).append(" ").append(minTime);
        System.out.println(sb);
    }

    static void bfs(int jx, int jy, Queue<Volc> vq) {
        Queue<JaeSang> jq = new LinkedList<>();
        jq.add(new JaeSang(jx, jy, 0));

        boolean[][] visited = new boolean[m + 1][n + 1];
        visited[jx][jy] = true;

        int[][] dt = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int dist = -1;
        while (!jq.isEmpty()) {

            // 화산쇄설류 이동
            int size = vq.size();
            int cnt = 0;
            while (cnt++ < size) {
                Volc vc = vq.poll();

                if (vc.cnt != 0) {
                    vc.cnt--;
                    vq.offer(new Volc(vc.x, vc.y, vc.cnt));
                    continue;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = vc.x + dt[i][0];
                    int ny = vc.y + dt[i][1];

                    if (nx == 0 || ny == 0 || nx == m + 1 || ny == n + 1) continue;
                    if (volcMap[nx][ny] == 1) continue;
                    volcMap[nx][ny] = 1;

                    vq.offer(new Volc(nx, ny, vc.cnt));
                }
            }

            // 재상 이동
            dist++;
            while (!jq.isEmpty() && jq.peek().cnt == dist) {
                JaeSang curJ = jq.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = curJ.x + dt[i][0];
                    int ny = curJ.y + dt[i][1];

                    if (nx == 0 || ny == 0 || nx == m + 1 || ny == n + 1) continue;
                    if (volcMap[nx][ny] != 0) continue;
                    if (visited[nx][ny]) continue;
                    visited[nx][ny] = true;

                    jq.offer(new JaeSang(nx, ny, curJ.cnt + 1));

                    if (hmap[nx][ny] > maxHeight) {
                        maxHeight = hmap[nx][ny];
                        minTime = curJ.cnt + 1;
                    }
                }
            }
        }
    }
}