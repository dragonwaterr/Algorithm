import java.io.*;
import java.util.StringTokenizer;

public class Main {
    // 미친 로봇

    static final int[][] dt = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static int n;
    static double answer;
    static double[] pb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        answer = 0;
        n = Integer.parseInt(st.nextToken());

        pb = new double[4];
        pb[0] = Double.parseDouble(st.nextToken()) * 0.01;
        pb[1] = Double.parseDouble(st.nextToken()) * 0.01;
        pb[2] = Double.parseDouble(st.nextToken()) * 0.01;
        pb[3] = Double.parseDouble(st.nextToken()) * 0.01;

        boolean[][] visited = new boolean[29][29];
        visited[14][14] = true;
        dfs(0, 14, 14, 1, visited);
        System.out.println(answer);
    }

    static void dfs(int depth, int x, int y, double cpb, boolean[][] visited) {
        if(depth == n) {
            answer += cpb;
            return;
        }

        for (int i = 0; i < 4; i++) {
            if(pb[i] == 0) continue;

            int nx = x + dt[i][0];
            int ny = y + dt[i][1];
            if(!visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(depth + 1, nx, ny, cpb * pb[i], visited);
                visited[nx][ny] = false;
            }
        }
    }
}